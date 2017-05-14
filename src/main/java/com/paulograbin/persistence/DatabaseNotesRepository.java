package com.paulograbin.persistence;

import com.paulograbin.domain.EntityNotFoundException;
import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesRepository;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collection;


@Repository
public class DatabaseNotesRepository implements NotesRepository {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public long count() {
        String query = "SELECT count(n) FROM Note n";
        Query q = manager.createQuery(query);

        return (long) q.getSingleResult();
    }

    @Override
    public boolean exists(Integer id) {
        return manager.contains(getById(id));
    }

    @Override
    @Transactional
    public void save(Note entity) {
        manager.persist(entity);
    }

    @Override
    public Collection<Note> getAll() {
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
    public Note getById(Integer id) {
        Note noteFound = manager.find(Note.class, id);

        if(noteFound == null)
            throw new EntityNotFoundException();

        return noteFound;
    }

    @Override
    @Transactional
    public void update(Note entity) {
        manager.merge(entity);
    }

    @Override
    @Transactional
    public void deleteAll() {
        int deletedRows = manager.createQuery("UPDATE Note n SET deleted = true WHERE deleted = false").executeUpdate();

        System.out.println(deletedRows + " notes deleted");
    }
}