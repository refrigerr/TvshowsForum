package com.forum.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class WebUser {

    @NotNull(message = "is required")
    @Size(min = 3, message = "minimum size is 3")
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "is in incorrect format!")
    private String email;

    @NotNull(message = "is required")
    @Size(min = 3, message = "minimum size is 3")
    private String password;

    @NotNull(message = "is required")
    @Size(min = 3, message = "minimum size is 3")
    private String userName;

    public WebUser() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
