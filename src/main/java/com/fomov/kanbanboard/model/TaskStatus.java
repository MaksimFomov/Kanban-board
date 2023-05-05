package com.fomov.kanbanboard.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "task_status")
public class TaskStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(mappedBy = "status",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH}
    )
    private Task task;

    public TaskStatus() {
    }

    public TaskStatus(int id, String name, Task task) {
        this.id = id;
        this.name = name;
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskStatus that = (TaskStatus) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(task, that.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, task);
    }
}
