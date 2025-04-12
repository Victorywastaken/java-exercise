package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Entity
@Data
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private boolean completed;

    @PrePersist
    @PreUpdate
    public void prePersist() {
        title = title.trim();
        description = description.trim();

        if (description != null && description.toLowerCase().contains("done")) {
            completed = true;
        }

        if (title != null && description != null &&
            !title.isEmpty() && !description.isEmpty() &&
            title.toLowerCase().charAt(0) == description.toLowerCase().charAt(0)) {
            String temp = title;
            title = description;
            description = temp;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Todo) {
            Todo other = (Todo) obj;
            return this.id != null && this.id.equals(other.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
