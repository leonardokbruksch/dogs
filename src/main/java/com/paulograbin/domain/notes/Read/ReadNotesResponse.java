package com.paulograbin.domain.notes.Read;

import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.ResponseWrapper;

import java.util.ArrayList;
import java.util.Collection;


public class ReadNotesResponse extends ResponseWrapper {

    Collection<Note> notes;

    public ReadNotesResponse() {
        notes = new ArrayList<>();
    }

    public void insertNote(Note note) {
        notes.add(note);
    }

    public int getResponseSize() {
        return notes.size();
    }

    @Override
    public int getItemsCount() {
        return notes.size();
    }
}
