package com.paulograbin.web.controllers;


import com.google.gson.Gson;
import com.paulograbin.OEditorApplication;
import com.paulograbin.domain.notes.Note;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OEditorApplication.class)
@WebAppConfiguration
public class NotesControllerTest {

    private MockMvc mockMvc;
    private Gson gson = new Gson();


    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(context).build();
    }

    @Test
    public void readNotes() throws Exception {
        mockMvc.perform(get("/notes")).andExpect(status().isOk());
    }

    @Test
    public void createNote() throws Exception {
        Note note = new Note();
        note.setId(1);
        note.setText("Nota de teste");

        String noteJson = gson.toJson(note);

        mockMvc.perform(post("/notes")
            .contentType(MediaType.APPLICATION_JSON)
                .content(noteJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }
}
