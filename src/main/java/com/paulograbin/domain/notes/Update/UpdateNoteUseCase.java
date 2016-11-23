package com.paulograbin.domain.notes.Update;


import com.paulograbin.domain.DateTimeFactory;
import com.paulograbin.domain.EntityNotFoundException;
import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesRepository;
import com.paulograbin.domain.texts.Text;

public class UpdateNoteUseCase {

    private final NotesRepository repository;
    private final UpdateNoteRequest request;
    private final UpdateNoteResponse response;

    public UpdateNoteUseCase(NotesRepository repository, UpdateNoteRequest request, UpdateNoteResponse response) {
        this.repository = repository;
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
        try {
            Note note = repository.getById(request.id);

            note.setText(new Text(null, request.text));
            note.setLastChangedDate(new DateTimeFactory().getCurrentUTCTime());

            repository.update(note);
            response.success = true;
        } catch (EntityNotFoundException e) {
            response.entityNotFound = true;
        }
    }
}
