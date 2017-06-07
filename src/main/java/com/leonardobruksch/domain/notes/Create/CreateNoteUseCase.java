package com.leonardobruksch.domain.notes.Create;

import com.leonardobruksch.domain.notes.Note;
import com.leonardobruksch.domain.notes.NotesRepository;


public class CreateNoteUseCase {

    private final NotesRepository repository;
    private final CreateNoteRequest request;
    private final CreateNoteResponse response;


    public CreateNoteUseCase(NotesRepository repository, CreateNoteRequest request, CreateNoteResponse response) {
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
        response.entity = note;
    }

    private void returnErrors() {
        response.noText = true;
    }

    private boolean isRequestValid() {
        return request.text != null && request.text.length() > 0;
    }
}
