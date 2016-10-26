package com.paulograbin.persistence;


import com.paulograbin.domain.notes.Entity;
import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;

@Repository
public class DatabaseNotesRepository implements NotesRepository {

    @PersistenceContext
    private EntityManager manager;


    @Override
    @Transactional
    public void save(Note entity) {
        manager.persist(entity);
    }

    @Override
    public Collection<Note> list() {
        return manager.createQuery("SELECT e FROM Note e").getResultList();
    }

    @Override
    public void delete(Integer idToDelete) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Note getById(Integer id) {
        return null;
    }

    @Override
    public void update(Note entity) {

    }
}
