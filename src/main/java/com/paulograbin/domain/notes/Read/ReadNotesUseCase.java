package com.paulograbin.domain.notes.Read;

import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesRepository;

import java.util.Comparator;
import java.util.stream.Collectors;


public class ReadNotesUseCase {

    private final NotesRepository repository;
    private final ReadNotesResponse response;

    public ReadNotesUseCase(NotesRepository repository, ReadNotesResponse response) {
        this.repository = repository;
        this.response = response;
    }

    public void execute() {
        repository.getAll().forEach(response::insertItem);

        sortNotesByCreationDate();
    }

    private void sortNotesByCreationDate() {
        Comparator<Note> cmp = Comparator.comparing(Note::getCreationDate).thenComparing(Note::getId);

        response.notes = response.notes.stream().sorted(cmp.reversed()).collect(Collectors.toList());
    }
}
