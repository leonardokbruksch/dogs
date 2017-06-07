package com.leonardobruksch.domain.notes.Read;

import com.leonardobruksch.domain.notes.Note;
import com.leonardobruksch.domain.notes.ResponseWrapper;

import java.util.ArrayList;
import java.util.Collection;


public class ReadNotesResponse extends ResponseWrapper {

    Collection<Note> notes;
    Iterable<Note> i;


    public ReadNotesResponse() {
        notes = new ArrayList<>();
    }


    public void insertItem(Note note) {
        notes.add(note);
    }

    @Override
    public int getItemsCount() {
        return notes.size();
    }
}
