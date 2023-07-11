package dev.bieger.model.dao.impl;

import dev.bieger.db.DbConnection;
import dev.bieger.model.dao.IBookCopyDao;
import dev.bieger.model.entity.BookCopy;

import java.sql.*;

public class BookCopyDao implements IBookCopyDao {

    private final Connection conn;

    public BookCopyDao() {
        this.conn = DbConnection.getConnection();
    }

    @Override
    public void insertBookCopy(BookCopy bookCopy) {
        String sql = "INSERT INTO book_copy (isbn, book_id, publisher_id, year, status) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, bookCopy.getIsbn());
            stmt.setLong(2, bookCopy.getBookId());
            stmt.setLong(3, bookCopy.getPublisherId());
            stmt.setInt(4, bookCopy.getYear());
            stmt.setInt(5, bookCopy.getStatus());

            int rowsInserted = stmt.executeUpdate();
            if(rowsInserted > 0) {
                System.out.println("Exemplar adicionado com sucesso!");
            }

            stmt.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateBookCopy(String isbn, BookCopy bookCopy) {
        String sql = "UPDATE book_copy SET book_id=?, publisher_id=?, year=?, status=? WHERE isbn=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, bookCopy.getIsbn());
            stmt.setLong(2, bookCopy.getBookId());
            stmt.setLong(3, bookCopy.getPublisherId());
            stmt.setInt(4, bookCopy.getYear());
            stmt.setInt(5, bookCopy.getStatus());

            int rowsInserted = stmt.executeUpdate();
            if(rowsInserted > 0) {
                System.out.println("Exemplar atualizado com sucesso!");
            }

            stmt.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateStatusBookCopy(String isbn, int status) {
        String sql = "UPDATE book_copy SET status=? WHERE isbn=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, status);
            stmt.setString(2, isbn);

            stmt.executeUpdate();

            stmt.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void selectAllBookCopy() {
        String sql = "SELECT * FROM book_copy";

        try {
            Statement statement = conn.createStatement();
            ResultSet stmt = statement.executeQuery(sql);

            System.out.println("\n\nLista de exemplares:\n");
            while(stmt.next()) {
                String isbn = stmt.getString(1);
                String bookId = stmt.getString(2);
                String publisherId = stmt.getString(3);
                String year = stmt.getString(4);
                String status = stmt.getString(5);

                String output = "ISBN %s: %s - %s - %s - %s";
                System.out.println(String.format(output, isbn, bookId, publisherId, year, status));
            }
            System.out.println("\n");

            stmt.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void selectByIsbnBookCopy(String findIsbn) {
        String sql = "SELECT * FROM book_copy WHERE isbn=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, findIsbn);
            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()) {
                String isbn = resultSet.getString(1);
                String bookId = resultSet.getString(2);
                String publisherId = resultSet.getString(3);
                String year = resultSet.getString(4);
                String status = resultSet.getString(5);

                String output = "ISBN %s: %s - %s - %s - %s";
                System.out.println(String.format(output, isbn, bookId, publisherId, year, status));
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int selectByIsbnStatus(String findIsbn) {
        String sql = "SELECT * FROM book_copy WHERE isbn=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, findIsbn);
            ResultSet resultSet = stmt.executeQuery();

            resultSet.next();
            int status = resultSet.getInt(1);
            return status;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBookCopy(String isbn) {
        String sql = "DELETE FROM book_copy WHERE isbn=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, isbn);

            int rowsDeleted = stmt.executeUpdate();
            if(rowsDeleted > 0) {
                System.out.println("Exemplar deletado com sucesso!");
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
