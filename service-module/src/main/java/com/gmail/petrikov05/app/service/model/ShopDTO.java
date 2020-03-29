package com.gmail.petrikov05.app.service.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ShopDTO {

    private Long id;
    @NotNull(message = "The field cannot be empty")
    @Size(min = 1, max = 100, message = "The field size cannot be more 50")
    private String name;
    @NotNull(message = "The field cannot be empty")
    @Size(min = 1, max = 255, message = "The field size cannot be more 255")
    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
