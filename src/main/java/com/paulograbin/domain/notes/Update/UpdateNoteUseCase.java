package com.paulograbin.domain.notes.Update;


import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesDAO;

public class UpdateNoteUseCase {

    private final NotesDAO dao;
    private final UpdateNoteRequest request;
    private final UpdateNoteResponse response;

    public UpdateNoteUseCase(NotesDAO dao, UpdateNoteRequest request, UpdateNoteResponse response) {
        this.dao = dao;
        this.request = request;
        this.response = response;
    }

    public void execute() {
        if(isRequestValid()) {
            update();
        } else {
            sendErrors();
        }
    }

    private boolean isRequestValid() {
        return request.text != null && request.text.length() > 0 && request.id != null;
    }

    private void sendErrors() {
        response.invalidText = request.text == null || request.text.length() < 1;
        response.invalidId = request.id == null;
    }

    private void update() {
        Note n = new Note(request.id, request.text);

        try {
            dao.update(n);
            response.success = true;
        } catch (RuntimeException e) {
            response.entityNotFound = true;
        }
    }
}
