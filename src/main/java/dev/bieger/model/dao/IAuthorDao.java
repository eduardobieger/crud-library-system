package dev.bieger.model.dao;

import dev.bieger.model.entity.Author;

public interface IAuthorDao {

    void insertAuthor(Author author);
    void updateAuthor(Long id, Author author);
    void selectAllAuthor();
    void selectByIdAuthor(Long findId);
    void deleteAuthor(Long id);

}
