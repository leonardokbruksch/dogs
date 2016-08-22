package com.paulograbin.domain.notes.Delete;

import com.paulograbin.domain.notes.NotesRepositoriy;
import com.paulograbin.domain.EntityNotFoundException;

public class DeleteNoteUseCase {

    private final NotesRepositoriy repository;
    private final DeleteNoteRequest request;
    private final DeleteNoteResponse response;


    public DeleteNoteUseCase(NotesRepositoriy repository, DeleteNoteRequest request, DeleteNoteResponse response) {
        this.repository = repository;
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
            repository.delete(request.idToDelete);
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
