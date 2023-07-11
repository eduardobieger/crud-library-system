package dev.bieger.model.dao.impl;

import dev.bieger.db.DbConnection;
import dev.bieger.model.dao.IBookDao;
import dev.bieger.model.entity.Book;

import java.sql.*;

public class BookDao implements IBookDao {

    private final Connection conn;

    public BookDao() {
        this.conn = DbConnection.getConnection();
    }

    @Override
    public void insertBook(Book book) {
        String sql = "INSERT INTO book (title, author_id, genre_id) VALUES (?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, book.getTitle());
            stmt.setLong(2, book.getAuthorId());
            stmt.setLong(3, book.getGenreId());

            int rowsInserted = stmt.executeUpdate();
            if(rowsInserted > 0) {
                System.out.println("Livro adicionado com sucesso!");
            }

            stmt.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateBook(Long id, Book book) {
        String sql = "UPDATE book SET title=?, author_id=?, genre_id=? WHERE id=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, book.getTitle());
            stmt.setLong(2, book.getAuthorId());
            stmt.setLong(3, book.getGenreId());
            stmt.setLong(4, id);

            int rowsInserted = stmt.executeUpdate();
            if(rowsInserted > 0) {
                System.out.println("Livro atualizado com sucesso!");
            }

            stmt.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void selectAllBook() {
        String sql = "SELECT * FROM book";

        try {
            Statement statement = conn.createStatement();
            ResultSet stmt = statement.executeQuery(sql);

            System.out.println("\n\nLista de livros:\n");
            while(stmt.next()) {
                String id = stmt.getString(1);
                String title = stmt.getString(2);
                String authorId = stmt.getString(3);
                String genreId = stmt.getString(4);

                String output = "ID %s: %s - %s - %s";
                System.out.println(String.format(output, id, title, authorId, genreId));
            }
            System.out.println("\n");

            stmt.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void selectByIdBook(Long findId) {
        String sql = "SELECT * FROM book WHERE id=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, findId);
            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()) {
                String id = resultSet.getString(1);
                String title = resultSet.getString(2);
                String authorId = resultSet.getString(3);
                String genreId = resultSet.getString(4);

                String output = "ID %s: %s - %s - %s";
                System.out.println(String.format(output, id, title, authorId, genreId));
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBook(Long id) {
        String sql = "DELETE FROM book WHERE id=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);

            int rowsDeleted = stmt.executeUpdate();
            if(rowsDeleted > 0) {
                System.out.println("Livro deletado com sucesso!+");
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
