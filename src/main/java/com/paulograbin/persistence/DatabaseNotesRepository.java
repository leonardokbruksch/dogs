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
    @Transactional
    public void delete(Integer idToDelete) {
        Note n = getById(idToDelete); // must test if already deleted
        n.setDeleted();

        update(n);
    }

    @Override
    public void deleteAll() {
//        manager.
    }

    @Override
    public Note getById(Integer id) {
        return manager.find(Note.class, id); // must deal with entity not found
    }

    @Override
    @Transactional
    public void update(Note entity) {
        manager.merge(entity);
    }
}