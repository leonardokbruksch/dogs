package com.paulograbin.domain.notes.Delete;

import com.paulograbin.domain.notes.NotesDAO;
import com.paulograbin.domain.EntityNotFoundException;

public class DeleteNoteUseCase {

    private final NotesDAO notesDAO;
    private final DeleteNoteRequest request;
    private final DeleteNoteResponse response;


    public DeleteNoteUseCase(NotesDAO notesDAO, DeleteNoteRequest request, DeleteNoteResponse response) {
        this.notesDAO = notesDAO;
        this.request = request;
        this.response = response;
    }

    public void execute() {
        if (isRequestValid())
            deleteNote();
        else
            returnErrors();
    }

    private void deleteNote() {
        try {
            notesDAO.delete(request.idToDelete);
        } catch (EntityNotFoundException e) {
            response.entityNotFound = true;

            return;
        }

        response.success = true;
    }

    private void returnErrors() {
        response.invalidId = true;
    }

    private boolean isRequestValid() {
        return (request.idToDelete != null) ? true : false;
    }
}
