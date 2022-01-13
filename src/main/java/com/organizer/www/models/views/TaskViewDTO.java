package com.organizer.www.models.views;

public class TaskViewDTO {
    private Long id;
    private String name;

    public Long getId() {
        return this.id;
    }

    public TaskViewDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public TaskViewDTO setName(String name) {
        this.name = name;
        return this;
    }
}
