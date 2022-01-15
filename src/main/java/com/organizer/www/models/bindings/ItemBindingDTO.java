package com.organizer.www.models.bindings;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ItemBindingDTO {
    @NotNull(message = "Item name can not be empty or null")
    @Size(min = 2, max = 250, message = "Item name should be between 2 and 250 characters")
    private String name;

    public String getName() {
        return this.name;
    }

    public ItemBindingDTO setName(String name) {
        this.name = name;
        return this;
    }
}
