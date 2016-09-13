package com.paulograbin.domain.notes.Read;

import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesRepository;

import java.util.Collection;


public class ReadNotesUseCase {

    private final NotesRepository repository;
    private final ReadNotesResponse response;

    public ReadNotesUseCase(NotesRepository repository, ReadNotesResponse response) {
        this.repository = repository;
        this.response = response;
    }

    public void execute() {
        repository.list().forEach(response::insertNote);
    }
}
