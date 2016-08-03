package com.paulograbin.domain.notes.Create;

import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesDAO;


public class CreateNoteUseCase {

    private final NotesDAO dao;
    private final CreateNoteRequest request;
    private final CreateNoteResponse response;


    public CreateNoteUseCase(NotesDAO dao, CreateNoteRequest request, CreateNoteResponse response) {
        this.dao = dao;
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

        dao.save(note);
        response.success = true;
    }

    private void returnErrors() {
        response.noText = true;
    }

    private boolean isRequestValid() {
        return request.text != null && request.text.length() > 0;
    }
}
