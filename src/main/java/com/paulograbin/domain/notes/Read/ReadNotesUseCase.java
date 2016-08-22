package com.paulograbin.domain.notes.Read;

import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesRepositoriy;

import java.util.Collection;


public class ReadNotesUseCase {

    private final NotesRepositoriy repository;
    private final Collection<Note> response;

    public ReadNotesUseCase(NotesRepositoriy repository, Collection<Note> response) {
        this.repository = repository;
        this.response = response;
    }

    public void execute() {
        response.addAll(repository.list());
    }
}
