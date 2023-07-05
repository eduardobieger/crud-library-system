package dev.bieger.model.dao;

import dev.bieger.model.entity.Publisher;

public interface IPublisherDao {

    void insertPublisher(Publisher publisher);
    void updatePublisher(Long id, Publisher publisher);
    void selectAllPublisher();
    void selectByIdPublisher(Long findId);
    void deletePublisher(Long id);

}
