package com.gmail.petrikov05.app.service.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.gmail.petrikov05.app.repository.constant.RoleEnum;

public class UserDTO {

    private Long id;
    @NotNull(message = "The field cannot be empty")
    @Size(min = 1, max = 50, message = "The field size cannot be more 50")
    private String username;
    @NotNull(message = "The field cannot be empty")
    @Size(min = 1, max = 70, message = "The field size cannot be more 70")
    private String password;
    @NotNull(message = "The field cannot be empty")
    private RoleEnum role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
