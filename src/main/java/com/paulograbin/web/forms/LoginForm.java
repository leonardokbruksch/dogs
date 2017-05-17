package com.paulograbin.web.forms;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by I848568 on 17/05/2017.
 */
public class LoginForm {

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    private String username;

    @NotNull
    private String password;

    @Override
    public String toString() {
        return "LoginForm{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
