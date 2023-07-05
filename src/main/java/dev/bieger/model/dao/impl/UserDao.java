package dev.bieger.model.dao.impl;

import dev.bieger.db.DbConnection;
import dev.bieger.model.dao.IUserDao;
import dev.bieger.model.entity.User;

import java.sql.*;

public class UserDao implements IUserDao {

    private final Connection conn;

    public UserDao(Connection conn) {
        this.conn = DbConnection.getConnection();
    }

    @Override
    public void insertUser(User user) {
        String sql = "INSERT INTO user (name, address, email, phone_number) VALUES (?, ?, ?, ?)";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getAddress());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhoneNumber());

            int rowsInserted = stmt.executeUpdate();
            if(rowsInserted > 0) {
                System.out.println("Usuário adicionado com sucesso!");
            }

            stmt.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUser(Long id, User user) {
        String sql = "UPDATE user SET name=?, address=?, email=?, phone_number=? WHERE id=?";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getAddress());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhoneNumber());
            stmt.setLong(5, id);

            int rowsInserted = stmt.executeUpdate();
            if(rowsInserted > 0) {
                System.out.println("Usuário atualizado com sucesso!");
            }

            stmt.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void selectAllUser() {
        String sql = "SELECT * FROM user";

        try{
            Statement statement = conn.createStatement();
            ResultSet stmt = statement.executeQuery(sql);

            System.out.println("\n\nLista de usuários:\n");
            while(stmt.next()) {
                String id = stmt.getString(1);
                String name = stmt.getString(2);
                String address = stmt.getString(3);
                String email = stmt.getString(4);
                String phoneNumber = stmt.getString(5);

                String output = "ID %s: %s - %s - %s - %s";
                System.out.println(String.format(output, id, name, address, email, phoneNumber));
            }
            System.out.println("\n");

            stmt.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void selectByIdUser(Long findId) {
        String sql = "SELECT * FROM user WHERE id=?";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, findId);
            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                String email = resultSet.getString(4);
                String phoneNumber = resultSet.getString(5);

                String output = "ID %s: %s - %s - %s - %s";
                System.out.println(String.format(output, id, name, address, email, phoneNumber));
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(Long id) {
        String sql = "DELETE FROM user WHERE id=?";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);

            int rowsDeleted = stmt.executeUpdate();
            if(rowsDeleted > 0) {
                System.out.println("Usuário deletado com sucesso!");
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
