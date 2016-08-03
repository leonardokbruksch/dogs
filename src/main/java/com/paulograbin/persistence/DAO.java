package com.paulograbin.persistence;

import com.paulograbin.domain.notes.Note;

import java.util.List;


public interface DAO<T> {

    void save(T entity);

    List<Note> list();
}
