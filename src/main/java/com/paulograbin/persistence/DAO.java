package com.paulograbin.persistence;

import com.paulograbin.domain.notes.Note;

import java.util.Collection;


public interface DAO<T> {

    void save(T entity);

    Collection<T> list();
}
