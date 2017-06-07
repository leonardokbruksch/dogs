package com.leonardobruksch.web.controllers;

import com.leonardobruksch.domain.users.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by I848568 on 16/05/2017.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public void home(HttpServletResponse response) throws IOException {
        response.sendRedirect("/loginForm");
    }

    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        request.getSession().removeAttribute("loggedUser");
        request.getSession().removeAttribute("isAdminUser");
        response.sendRedirect("/loginForm");
    }

    @RequestMapping("/home")
    public String homePage(HttpServletRequest request, HttpServletResponse response, Model model) {
        User user = (User) request.getSession().getAttribute("loggedUser");
        model.addAttribute("loggedUser", user);
        return "home";
    }

}
