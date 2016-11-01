package com.paulograbin.web.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping(value = "/ip")
public class IpController {

    @RequestMapping()
    public void printIp(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.getWriter().write("Remote addr: " + req.getRemoteAddr() + "\n");
        res.getWriter().write("Remote host: " + req.getRemoteHost() + "\n");

        res.getWriter().write("Local addr: " + req.getLocalAddr() + "\n");
        res.getWriter().write("Local name: " + req.getLocalName() + "\n");
    }

}
