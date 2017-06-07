package com.leonardobruksch.web.forms;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull
    private String email;

    @NotNull
    private String password;

    @Override
    public String toString() {
        return "LoginForm{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
