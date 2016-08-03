package com.paulograbin;

import com.paulograbin.web.controllers.NotesController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by paulograbin on 31/07/16.
 */
public class NotesControllerTest {

    private MockMvc mock;

    @Before
    public void setUp() throws Exception {
        mock = MockMvcBuilders.standaloneSetup(NotesController.class).build();
    }

    @Test
    public void test() throws Exception {
        URL url = new URL("http://localhost:3000/notes");

        URLConnection con = url.openConnection();
        HttpURLConnection conn = (HttpURLConnection) con;

        BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        StringBuilder responseBuilder = new StringBuilder();
        String line;

        while((line = bf.readLine()) != null) {
            responseBuilder.append(line);
            responseBuilder.append('\r');
        }

        System.out.println(responseBuilder.toString());

        Assert.assertEquals("hello", responseBuilder.toString());

    }
}
