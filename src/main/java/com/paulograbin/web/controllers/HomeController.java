package com.paulograbin.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by I848568 on 16/05/2017.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "loginForm";
    }

    @RequestMapping("/home")
    public String homePage() {
        return "home";
    }

}
