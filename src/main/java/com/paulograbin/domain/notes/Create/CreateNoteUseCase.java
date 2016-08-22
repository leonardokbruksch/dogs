package com.paulograbin.domain.notes.Create;

import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesRepositoriy;


public class CreateNoteUseCase {

    private final NotesRepositoriy repository;
    private final CreateNoteRequest request;
    private final CreateNoteResponse response;


    public CreateNoteUseCase(NotesRepositoriy repository, CreateNoteRequest request, CreateNoteResponse response) {
        this.repository = repository;
        this.request = request;
        this.response = response;
    }

    public void execute() {
        if(isRequestValid())
            create();
        else
            returnErrors();
    }

    private void create() {
        Note note = new Note();

        note.setText(request.text);

        repository.save(note);
        response.success = true;
    }

    private void returnErrors() {
        response.noText = true;
    }

    private boolean isRequestValid() {
        return request.text != null && request.text.length() > 0;
    }
}
