package com.leonardobruksch.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by I848568 on 23/05/2017.
 */
@Controller
public class AdminCockpitController {

    @RequestMapping("/admin")
    public String homePage(HttpServletRequest request, Model model) {
        if ((Boolean) request.getSession().getAttribute("isAdminUser")){
            model.addAttribute("isAdminUser", true);
            return "adminCockpit";
        }
        return "redirect:home";
    }
}
