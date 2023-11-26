package com.example.todo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private Boolean is_completed;
    private Boolean is_postponed;
    private Boolean is_active;

    @Override
    public String toString() {
        return "jobEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", is_completed=" + is_completed +
                ", is_postponed=" + is_postponed +
                ", is_active=" + is_active +
                '}';
    }
}
