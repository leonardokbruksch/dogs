package com.paulograbin.web.controllers;

import com.paulograbin.domain.users.User;
import com.paulograbin.domain.users.UserRepository;
import com.paulograbin.web.forms.LoginForm;
import com.paulograbin.web.forms.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @GetMapping("/registerForm")
    public String registerForm(@ModelAttribute("registerForm") RegisterForm registerForm) {
        return "RegisterForm";
    }

    @PostMapping("/register")
    public String doRegister(@Valid RegisterForm registerForm, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()){
            return "registerForm";
        }

        if( repository.findByUsername(registerForm.getUsername()) != null ){
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the username provided");
        }

        User newUser = new User(registerForm.getFirstName(), registerForm.getUsername(), registerForm.getPassword());
        repository.save(newUser);
        session.setAttribute("loggedUser", newUser);

        return "redirect:home";
    }
}
