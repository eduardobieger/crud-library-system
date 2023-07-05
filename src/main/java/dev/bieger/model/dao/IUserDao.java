package dev.bieger.model.dao;

import dev.bieger.model.entity.User;

public interface IUserDao {

    void insertUser(User user);
    void updateUser(Long id, User user);
    void selectAllUser();
    void selectByIdUser(Long findId);
    void deleteUser(Long id);

}
