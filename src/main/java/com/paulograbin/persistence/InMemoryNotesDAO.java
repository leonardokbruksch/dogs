package com.paulograbin.persistence;

import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesDAO;
import com.paulograbin.domain.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Repository
public class InMemoryNotesDAO implements NotesDAO {

    private Map<Integer, Note> notes;
    private int lastId;

    public InMemoryNotesDAO() {
        lastId = 0;
        notes = new HashMap<>();
    }

    @Override
    public void save(Note entity) {
        if(entity.getId() == null) {
            entity.setId(getNextId());
        }

        notes.put(entity.getId(), entity);
        System.out.println("Inserting note " + entity);
    }

    private int getNextId() {
        return lastId++;
    }

    @Override
    public Collection<Note> list() {
        return notes.values();
    }

    @Override
    public void delete(int idToDelete) {
        Note n = getById(idToDelete);

        n.setDeleted();
    }

    @Override
    public Note getById(Integer id) {
        Note n = notes.get(id);

        if (n != null)
            return n;
        else
            throw new EntityNotFoundException();
    }

    @Override
    public void update(Note e) {
        Note saved = getById(e.getId());

        saved.setText(e.getText());
        saved.setLastChangedDate(LocalDateTime.now());
    }
}
