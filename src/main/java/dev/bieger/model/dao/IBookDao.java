package dev.bieger.model.dao;

import dev.bieger.model.entity.Book;

public interface IBookDao {

    void insertBook(Book book);
    void updateBook(Long id, Book book);
    void selectAllBook();
    void selectByIdBook(Long findId);
    void deleteBook(Long id);

}
