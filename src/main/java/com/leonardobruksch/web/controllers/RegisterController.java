package com.leonardobruksch.web.controllers;

import com.leonardobruksch.domain.users.User;
import com.leonardobruksch.domain.users.UserRepository;
import com.leonardobruksch.web.forms.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by I848568 on 16/05/2017.
 */
@Controller
public class RegisterController {

    @Autowired
    private UserRepository repository;

    @PostMapping("/register")
    public String doRegister(@Valid RegisterForm registerForm, BindingResult bindingResult, HttpSession session, Model model) {
        if (bindingResult.hasErrors() || repository.findByEmail(registerForm.getEmail()) != null ){
            model.addAttribute("registrationError", "Error upon registration.");
            return "loginForm";
        }

        User newUser = new User(registerForm.getFirstName(), registerForm.getEmail(), registerForm.getPassword());
        repository.save(newUser);
        session.setAttribute("loggedUser", newUser);

        return "redirect:dogsForAdoption";
    }
}
