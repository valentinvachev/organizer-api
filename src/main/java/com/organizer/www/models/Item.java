package com.organizer.www.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "items")
public class Item extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public String getName() {
        return this.name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public Item setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
