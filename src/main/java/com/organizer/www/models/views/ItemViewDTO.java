package com.organizer.www.models.views;

public class ItemViewDTO {
    private Long id;
    private String name;

    public Long getId() {
        return this.id;
    }

    public ItemViewDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public ItemViewDTO setName(String name) {
        this.name = name;
        return this;
    }
}
