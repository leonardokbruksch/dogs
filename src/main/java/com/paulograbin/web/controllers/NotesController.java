package com.paulograbin.web.controllers;

import com.google.gson.Gson;
import com.paulograbin.domain.notes.Create.CreateNoteRequest;
import com.paulograbin.domain.notes.Create.CreateNoteResponse;
import com.paulograbin.domain.notes.Create.CreateNoteUseCase;
import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesDAO;
import com.paulograbin.domain.notes.Read.ReadNotesUseCase;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping("/notes")
public class NotesController {

    @Resource
    NotesDAO dao;
    private Gson converter = new Gson();


    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody String save(CreateNoteRequest request) {
        CreateNoteResponse response = new CreateNoteResponse();
        new CreateNoteUseCase(dao, request, response).execute();

        return converter.toJson(response);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String list() {
        Collection<Note> notes = new ArrayList<>();
        new ReadNotesUseCase(dao, notes).execute();

        return converter.toJson(notes);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable("id") int id) {
        System.out.println("Deletando " + id);

        return "deleting...";
    }
}
