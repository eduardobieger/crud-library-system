package dev.bieger.model.dao;

import dev.bieger.model.entity.BookEdition;

public interface IBookEditionDao {

    void insertBookCopy(BookEdition bookCopy);
    void updateBookCopy(String isbn, BookEdition bookCopy);
    void updateStatusBookCopy(String isbn, int status);
    void selectAllBookCopy();
    void selectByIsbnBookCopy(String findIsbn);
    int selectByIsbnStatus(String findIsbn);
    void deleteBookCopy(String isbn);

}
