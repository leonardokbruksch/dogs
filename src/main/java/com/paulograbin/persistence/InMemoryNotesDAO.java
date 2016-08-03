package com.paulograbin.persistence;

import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesDAO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Repository
public class InMemoryNotesDAO implements NotesDAO {

    private ArrayList<Note> notes;

    public InMemoryNotesDAO() {
        notes = new ArrayList<>();
    }

    @Override
    public void save(Note entity) {
        System.out.println("Inserindo " + entity.toString());
        notes.add(entity);
    }

    @Override
    public List<Note> list() {
        if(notes.isEmpty())
            return Collections.EMPTY_LIST;

        return notes;
    }
}
