package com.leonardobruksch.web.controllers;

import com.leonardobruksch.domain.dogs.Dogs;
import com.leonardobruksch.domain.dogs.DogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by I848568 on 07/06/2017.
 */
@Controller
public class DogsForAdoptionController {

    @Autowired
    private DogsRepository repository;

    @RequestMapping("/dogsForAdoption")
    public String dogsForAdoption(HttpServletResponse response, Model model, HttpSession session) throws IOException {
        final Object loggedUser = session.getAttribute("loggedUser");
        model.addAttribute("loggedUser", loggedUser);

        if (session.getAttribute("filteredDogs") != null){
            model.addAttribute("dogs", session.getAttribute("filteredDogs"));
        } else {
            model.addAttribute("dogs", repository.findAll());
        }
        return "dogsForAdoption";
    }

    @PostMapping("/filterDogByAge")
    public String filterDogsByAge(@RequestParam int ageFrom, @RequestParam int ageTo, Model model, HttpSession session) throws IOException {
        final Object loggedUser = session.getAttribute("loggedUser");
        model.addAttribute("loggedUser", loggedUser);
        Iterable<Dogs> dogs = repository.findAll();
        List<Dogs> dogsList = new ArrayList<>();

        for(Dogs dog : dogs ){
            int age = Integer.valueOf(dog.getAge());
            if(age > ageTo || age < ageFrom){

            } else {
                dogsList.add(dog);
            }
        }

        session.setAttribute("filteredDogs", dogsList);
        model.addAttribute("dogs", dogsList);
        return "dogsForAdoption";
    }
}
