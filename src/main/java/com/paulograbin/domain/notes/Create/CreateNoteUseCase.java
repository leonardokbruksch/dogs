package com.paulograbin.domain.notes.Create;

import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesRepository;
import com.paulograbin.domain.texts.Text;


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

        note.addText(new Text(null, request.text));

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
