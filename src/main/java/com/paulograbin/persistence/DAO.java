package com.paulograbin.persistence;

import java.util.Collection;


public interface DAO<T> {

    void save(T entity);

    Collection<T> list();

    void delete(int idToDelete);

    T getById(Integer id);
}
