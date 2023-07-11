package dev.bieger.model.dao;

import dev.bieger.model.entity.BookCopy;

public interface IBookCopyDao {

    void insertBookCopy(BookCopy bookCopy);
    void updateBookCopy(String isbn, BookCopy bookCopy);
    void updateStatusBookCopy(String isbn, int status);
    void selectAllBookCopy();
    void selectByIsbnBookCopy(String findIsbn);
    int selectByIsbnStatus(String findIsbn);
    void deleteBookCopy(String isbn);

}
