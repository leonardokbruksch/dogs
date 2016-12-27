package com.paulograbin.domain.notes.Read;

import com.paulograbin.domain.notes.Create.CreateNoteRequest;
import com.paulograbin.domain.notes.Create.CreateNoteResponse;
import com.paulograbin.domain.notes.Create.CreateNoteUseCase;
import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesRepository;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ReadNoteUseCaseTest {

    @Inject
    private NotesRepository repository;
    private ReadNotesResponse response;

    @Before
    public void setUp() throws Exception {
        response = new ReadNotesResponse();
    }

    @Test
    public void WithNoNotes__mustReturnEmptyCollection() {
        whenReadingAllNotes();
        thenSizeMustBe(0);
    }

    @Test
    public void WithOneNote__mustReturnOne() {
        givenANote("Olá Marilene");
        whenReadingAllNotes();
        thenSizeMustBe(1);
    }

    @Test
    public void withMoreThanOneNote__mustReturnInReverseCreationOrder() throws Exception {
        givenANote("First note");
        Thread.sleep(10L); // Sleep in order to make sure Notes are created in two separated moments
        givenANote("Second note");

        whenReadingAllNotes();

        ArrayList<Note> notes = new ArrayList<>(response.notes);
        assertEquals(notes.get(0).getText(), "Second note");
        assertEquals(notes.get(1).getText(), "First note");
    }

    private void givenANote(String text) {
        CreateNoteRequest request = new CreateNoteRequest();
        request.setText(text);

        CreateNoteResponse res = new CreateNoteResponse();
        new CreateNoteUseCase(repository, request, res).execute();
    }

    private void whenReadingAllNotes() {
        new ReadNotesUseCase(repository, response).execute();
    }

    private void thenSizeMustBe(int expected) {
        assertEquals(expected, response.getItemsCount());
    }
}