package com.paulograbin.web.controllers;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/notes")
public class NotesController {
    private Gson converter;

    public NotesController() {
        this.converter = new Gson();
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String list() {
        return converter.toJson("hello");
    }

}
