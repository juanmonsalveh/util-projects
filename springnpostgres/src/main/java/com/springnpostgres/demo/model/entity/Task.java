package com.springnpostgres.demo.model.entity;

import com.springnpostgres.demo.core.TaskStatus;
import com.springnpostgres.demo.core.dto.TaskDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import static com.springnpostgres.demo.core.TaskStatus.CREATED;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private TaskStatus status = CREATED;

    public Task(String title) {
        this.title = title;
    }

    private Task() {}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTaskStatus(TaskStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public TaskDto toDto() {
        return new TaskDto(String.valueOf(id), title, description, status.name());
    }
}