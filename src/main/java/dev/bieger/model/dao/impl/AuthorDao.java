package dev.bieger.model.dao.impl;

import dev.bieger.db.DbConnection;
import dev.bieger.model.dao.IAuthorDao;
import dev.bieger.model.entity.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthorDao implements IAuthorDao {

    private final Connection conn;

    public AuthorDao() {
        this.conn = DbConnection.getConnection();
    }

    @Override
    public void insertAuthor(Author author) {
        String sql = "INSERT INTO author (name) VALUES (?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, author.getName());

            int rowsInserted = stmt.executeUpdate();
            if(rowsInserted > 0) {
                System.out.println("Um novo autor foi inserido com sucesso!");
            }

            stmt.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAuthor(Long id, Author author) {
        String sql = "UPDATE author SET name=? WHERE id=?";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, author.getName());
            stmt.setLong(2, id);

            int rowsInserted = stmt.executeUpdate();
            if(rowsInserted > 0) {
                System.out.println("Autor atualizado com sucesso!");
            }

            stmt.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void selectAllAuthor() {
        String sql = "SELECT * FROM author";

        try{
            Statement statement = conn.createStatement();
            ResultSet stmt = statement.executeQuery(sql);

            System.out.println("\n\nLista de autores:\n");
            while(stmt.next()) {
                String id = stmt.getString(1);
                String nome = stmt.getString(2);

                String output = "ID %s: %s";
                System.out.println(String.format(output, id, nome));
            }
            System.out.println("\n");
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void selectByIdAuthor(Long findId) {
        String sql = "SELECT * FROM author WHERE id=?";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, findId);
            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);

                String output = "ID %s: %s";
                System.out.println(String.format(output, id, name));
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAuthor(Long id) {
        String sql = "DELETE FROM author WHERE id=?";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);

            int deletedRows = stmt.executeUpdate();
            if(deletedRows > 0) {
                System.out.println("Autor deletado com sucesso!");
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
