package com.paulograbin.domain;

import java.util.Collection;


public interface Repository<T> {

    void save(T entity);

    Collection<T> list();

    void delete(int idToDelete);

    T getById(Integer id);

    void update(T entity);
}