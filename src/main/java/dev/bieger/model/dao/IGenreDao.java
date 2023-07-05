package dev.bieger.model.dao;

import dev.bieger.model.entity.Genre;

public interface IGenreDao {

    void insertGenre(Genre genre);
    void updateGenre(Long id, Genre genre);
    void selectAllGenre();
    void selectByIdGenre(Long findId);
    void deleteGenre(Long id);

}
