package com.paulograbin.web.controllers;

import com.google.gson.Gson;
import com.paulograbin.domain.notes.NotesRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= RANDOM_PORT)
public class NotesControllerTest extends ControllerTest {

    private Gson gson = new Gson();

    @Autowired
    private NotesRepository notesRepository;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
//        notesRepository.deleteAll();
    }

    @Test
    public void readNotes() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/notes"))
                .andExpect(status().isOk());

        System.out.println(resultActions);
    }

    @Test
    public void createNote() throws Exception {
        long numberOfNotesBefore = notesRepository.count();

        mockMvc.perform(post("/notes")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("Text", LocalDateTime.now().toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

        assertEquals(numberOfNotesBefore+1, notesRepository.count());
    }
}
