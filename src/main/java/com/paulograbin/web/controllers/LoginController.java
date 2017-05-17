package com.paulograbin.web.controllers;

import com.paulograbin.domain.users.User;
import com.paulograbin.domain.users.UserRepository;
import com.paulograbin.web.forms.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by I848568 on 16/05/2017.
 */
@Controller
public class LoginController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/loginForm")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm) {
        return "loginForm";
    }

    @PostMapping("/doLogin")
    public String doLogin(@Valid LoginForm loginForm, BindingResult bindingResult, HttpSession session) {
        //repository.save(new User("leonardo", "bruksch", "leo@hotmail.com" , "123456"));
        if (bindingResult.hasErrors()){
            return "loginForm";
        }

        //User user = new User();
        //session.setAttribute("loggedUser", user);
        return "redirect:home";
    }

}
