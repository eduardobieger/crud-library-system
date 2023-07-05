package dev.bieger.model.dao.impl;

import dev.bieger.db.DbConnection;
import dev.bieger.model.dao.IGenreDao;
import dev.bieger.model.entity.Genre;

import java.sql.*;

public class GenreDao implements IGenreDao {

    private final Connection conn;

    public GenreDao() {
        this.conn = DbConnection.getConnection();
    }

    @Override
    public void insertGenre(Genre genre) {
        String sql = "INSERT INTO genre (name) VALUES (?)";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, genre.getName());

            int rowsInserted = stmt.executeUpdate();
            if(rowsInserted > 0) {
                System.out.println("Um novo gênero foi inserido com sucesso!");
            }

            stmt.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateGenre(Long id, Genre genre) {
        String sql = "UPDATE genre SET name=? WHERE id=?";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, genre.getName());
            stmt.setLong(2, id);

            int rowsInserted = stmt.executeUpdate();
            if(rowsInserted > 0) {
                System.out.println("Gênero atualizado com sucesso!");
            }

            stmt.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void selectAllGenre() {
        String sql = "SELECT * FROM genre";

        try{
            Statement statement = conn.createStatement();
            ResultSet stmt = statement.executeQuery(sql);

            System.out.println("\n\nLista de gêneros:\n");
            while(stmt.next()) {
                String id = stmt.getString(1);
                String name = stmt.getString(2);

                String output = "ID %s: %s";
                System.out.println(String.format(output, id, name));
            }
            System.out.println("\n");
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void selectByIdGenre(Long findId) {
        String sql = "SELECT * FROM genre WHERE id=?";

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
    public void deleteGenre(Long id) {
        String sql = "DELETE FROM genre WHERE id=?";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);

            int deletedRows = stmt.executeUpdate();
            if(deletedRows > 0) {
                System.out.println("Gênero deletado com sucesso!");
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
