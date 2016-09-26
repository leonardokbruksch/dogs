package com.paulograbin.domain.notes.Read;

import com.paulograbin.domain.notes.Note;

import java.util.ArrayList;
import java.util.Collection;

public class ReadNotesResponse {

    Collection<Note> notes;

    public ReadNotesResponse() {
        notes = new ArrayList<>();
    }


    public void insertNote(Note note) {
        notes.add(note);
    }

    public int size() {
        return notes.size();
    }
}
