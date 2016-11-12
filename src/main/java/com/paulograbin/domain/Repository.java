package com.paulograbin.domain;


public interface Repository<T> {

    long count();

    boolean exists(Integer id);

    void save(T entity);

    Iterable<T> getAll();

    void delete(Integer idToDelete);

    T getById(Integer id);

    void update(T entity);
}
