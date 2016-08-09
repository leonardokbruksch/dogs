package com.paulograbin.persistence;

import java.util.Map;


public interface DAO<T> {

    void save(T entity);

    Map<Integer, T> list();
}
