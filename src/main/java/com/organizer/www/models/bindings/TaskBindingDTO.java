package com.organizer.www.models.bindings;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TaskBindingDTO {
    @NotNull(message = "Task name can not be null")
    @Size(min = 2, max = 250, message = "Task name should be between 2 and 250 symbols")
    private String name;

    public String getName() {
        return this.name;
    }

    public TaskBindingDTO setName(String name) {
        this.name = name;
        return this;
    }
}
