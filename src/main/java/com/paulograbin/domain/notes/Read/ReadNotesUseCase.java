package com.paulograbin.domain.notes.Read;

import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesDAO;

import java.util.Collection;
import java.util.Map;


public class ReadNotesUseCase {

    private final NotesDAO dao;
    private final Map<Integer, Note> response;

    public ReadNotesUseCase(NotesDAO dao, Map<Integer, Note> response) {
        this.dao = dao;
        this.response = response;
    }

    public void execute() {
        response.putAll(dao.list());
    }
}
