package com.leonardobruksch.web.controllers;

import com.leonardobruksch.domain.dogs.DogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by I848568 on 07/06/2017.
 */
@Controller
public class DogsForAdoptionController {

    @Autowired
    private DogsRepository repository;

    @RequestMapping("/dogsForAdoption")
    public String home(HttpServletResponse response, Model model) throws IOException {
        model.addAttribute("dogs", repository.findAll());
        return "dogsForAdoption";
    }
}
