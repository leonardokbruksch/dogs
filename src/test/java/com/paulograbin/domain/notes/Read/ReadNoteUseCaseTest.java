package com.paulograbin.domain.notes.Read;

import com.paulograbin.domain.notes.Create.CreateNoteRequest;
import com.paulograbin.domain.notes.Create.CreateNoteResponse;
import com.paulograbin.domain.notes.Create.CreateNoteUseCase;
import com.paulograbin.domain.notes.NotesRepository;
import com.paulograbin.persistence.InMemoryNotesRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReadNoteUseCaseTest {

    private NotesRepository repository;
    private ReadNotesResponse response;

    @Before
    public void setUp() throws Exception {
        repository = new InMemoryNotesRepository();
        response = new ReadNotesResponse();
    }

    @Test
    public void WithNoNotes__mustReturnEmptyCollection() {
        whenReadingAllNotes();
        thenSizeMustBe(0);
    }

    private void whenReadingAllNotes() {
        new ReadNotesUseCase(repository, response).execute();
    }

    @Test
    public void WithOneNote__mustReturnOne() {
        givenANote("Olá Marilene");
        whenReadingAllNotes();
        thenSizeMustBe(1);
    }

    private void thenSizeMustBe(int expected) {
        assertEquals(expected, response.getItemsCount());
    }

    private void givenANote(String text) {
        CreateNoteRequest request = new CreateNoteRequest();
        request.setText(text);

        CreateNoteResponse res = new CreateNoteResponse();
        new CreateNoteUseCase(repository, request, res).execute();
    }
}