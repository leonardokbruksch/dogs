package com.paulograbin.persistence;

import com.paulograbin.domain.DateTimeFactory;
import com.paulograbin.domain.EntityNotFoundException;
import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


//@Repository
public class InMemoryNotesRepository implements NotesRepository {

    private final Map<Integer, Note> notes;
    private int lastId;

    public InMemoryNotesRepository() {
        lastId = 0;
        notes = new HashMap<>();
    }

    @Override
    public long count() {
        return notes.size();
    }

    @Override
    public boolean exists(Integer id) {
        return notes.containsKey(id);
    }

    @Override
    public void save(Note entity) {
        if(entity.getId() == null) {
            entity.setId(getNextId());
        }

        notes.put(entity.getId(), entity);
        System.out.println("Inserting note " + entity);
    }

    @Override
    public Iterable<Note> getAll() {
        return notes.values();
    }

    private int getNextId() {
        return lastId++;
    }

    @Override
    public void delete(Integer idToDelete) {
        Note n = getById(idToDelete);

        n.setDeleted();
    }

    @Override
    public void deleteAll() {
        notes.clear();
    }

    @Override
    public Note getById(Integer id) {
        if(id == null)
            throw new IllegalArgumentException();

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
        saved.setLastChangedDate(new DateTimeFactory().getCurrentUTCTime());
    }
}
