package com.paulograbin.domain.notes.Read;

import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesDAO;

import java.util.Collection;


public class ReadNotesUseCase {

    private final NotesDAO dao;
    private final Collection<Note> response;

    public ReadNotesUseCase(NotesDAO dao, Collection<Note> response) {
        this.dao = dao;
        this.response = response;
    }

    public void execute() {
        response.addAll(dao.list());
    }
}
