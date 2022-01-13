package com.organizer.www.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public String getName() {
        return this.name;
    }

    public Task setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public Task setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
