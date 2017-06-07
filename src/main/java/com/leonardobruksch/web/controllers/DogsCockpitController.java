package com.leonardobruksch.web.controllers;

import com.leonardobruksch.domain.dogs.Dogs;
import com.leonardobruksch.domain.dogs.DogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by I848568 on 23/05/2017.
 */
@Controller
public class DogsCockpitController {

    @Autowired
    private DogsRepository repository;

    @GetMapping("/dogsCockpit")
    public String dogsCockpit(Model model){
        model.addAttribute("dogs", repository.findAll());
        return "dogsCockpit";
    }

    @PostMapping("/deleteDog")
    @ResponseBody
    public void deleteDog(@RequestParam Long dogId){
        repository.delete(dogId);
    }

    @PostMapping("/editDog")
    @ResponseBody
    public void editDog(@RequestParam Long dogId, @RequestParam String dogName, @RequestParam String dogAge){
        Dogs dog = new Dogs(dogAge, dogName);
        dog.setId(dogId);
        repository.save(dog);
    }
}
