package dev.bieger.model.dao.impl;

import dev.bieger.db.DbConnection;
import dev.bieger.model.dao.IPublisherDao;
import dev.bieger.model.entity.Publisher;

import java.sql.*;

public class PublisherDao implements IPublisherDao {

    private final Connection conn;

    public PublisherDao() {
        this.conn = DbConnection.getConnection();
    }

    @Override
    public void insertPublisher(Publisher publisher) {
        String sql = "INSERT INTO publisher (name, location) VALUES (?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, publisher.getName());
            stmt.setString(2, publisher.getLocation());

            int rowsInserted = stmt.executeUpdate();
            if(rowsInserted > 0) {
                System.out.println("Editora adicionada com sucesso!");
            }

            stmt.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePublisher(Long id, Publisher publisher) {
        String sql = "UPDATE publisher SET name=?, location=? WHERE id=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, publisher.getName());
            stmt.setString(2, publisher.getLocation());

            int rowsInserted = stmt.executeUpdate();
            if(rowsInserted > 0) {
                System.out.println("Editora atualizada com sucesso!");
            }

            stmt.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void selectAllPublisher() {
        String sql = "SELECT * FROM publisher";

        try {
            Statement statement = conn.createStatement();
            ResultSet stmt = statement.executeQuery(sql);

            System.out.println("\n\nLista de editoras:\n");
            while(stmt.next()) {
                String id = stmt.getString(1);
                String name = stmt.getString(2);
                String location = stmt.getString(3);

                String output = "ID %s: %s - %s";
                System.out.println(String.format(output, id, name, location));
            }
            System.out.println("\n");

            stmt.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void selectByIdPublisher(Long findId) {
        String sql = "SELECT * FROM publisher WHERE id=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, findId);
            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String location = resultSet.getString(3);

                String output = "ID %s: %s - %s";
                System.out.println(String.format(output, id, name, location));
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePublisher(Long id) {
        String sql = "DELETE FROM publisher WHERE id=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);

            int rowsDeleted = stmt.executeUpdate();
            if(rowsDeleted > 0) {
                System.out.println("Editora deletada com sucesso!");
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
