package com.leonardobruksch.web.controllers;

import com.leonardobruksch.domain.users.UserRepository;
import com.leonardobruksch.web.forms.LoginForm;
import com.leonardobruksch.web.forms.RegisterForm;
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
public class LoginController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/loginForm")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm, @ModelAttribute("registerForm") RegisterForm registerForm) {
        return "loginForm";
    }

    @PostMapping("/doLogin")
    public String doLogin(@Valid LoginForm loginForm, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()){
            return "loginForm";
        }

        if(loginForm.getEmail().equals("admin@admin.com")){
            session.setAttribute("isAdminUser", true);
            session.setAttribute("loggedUser", "admin@admin.com");
            return "redirect:admin";
        }

        if (repository.findByEmail(loginForm.getEmail()) != null && repository.findByPassword(loginForm.getPassword()) != null){
            session.setAttribute("loggedUser", repository.findByEmail(loginForm.getEmail()));
            return "redirect:home";
        }

        return "loginForm";
    }

}
