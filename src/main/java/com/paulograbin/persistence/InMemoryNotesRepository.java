package com.paulograbin.persistence;

import com.paulograbin.domain.DateTimeFactory;
import com.paulograbin.domain.EntityNotFoundException;
import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryNotesRepository implements NotesRepository {

    private final Map<Integer, Note> notes;
    private int lastId;

    public InMemoryNotesRepository() {
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
        saved.setLastChangedDate(new DateTimeFactory().getCurrentUTCTime());
    }
}
