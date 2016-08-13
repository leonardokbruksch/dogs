package com.paulograbin.persistence;

import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesDAO;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
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
        System.out.println("Inserindo " + entity.toString());
    }

    private int getNextId() {
        return lastId++;
    }

    @Override
    public Collection<Note> list() {
        if(notes.isEmpty())
            return Collections.EMPTY_LIST;

        return notes.values();
    }
}
