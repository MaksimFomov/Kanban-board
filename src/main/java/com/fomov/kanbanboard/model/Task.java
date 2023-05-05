package com.fomov.kanbanboard.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "estimate")
    private String estimate;

    @Column(name = "priority", nullable = false)
    private String priority;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "status_id", nullable = false)
    private TaskStatus status;

    @Column(name = "sprint", nullable = false)
    private String sprint;

    @Column(name = "date_of_created", nullable = false)
    private LocalDateTime dateOfCreated;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH}
    )
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public Task() {
    }

    public Task(int id, String name, String description, String estimate, String priority, TaskStatus status, String sprint, LocalDateTime dateOfCreated, User user, Project project) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.estimate = estimate;
        this.priority = priority;
        this.status = status;
        this.sprint = sprint;
        this.dateOfCreated = dateOfCreated;
        this.user = user;
        this.project = project;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEstimate() {
        return estimate;
    }

    public void setEstimate(String estimate) {
        this.estimate = estimate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getSprint() {
        return sprint;
    }

    public void setSprint(String sprint) {
        this.sprint = sprint;
    }

    public LocalDateTime getDateOfCreated() {
        return dateOfCreated;
    }

    public void setDateOfCreated(LocalDateTime dateOfCreated) {
        this.dateOfCreated = dateOfCreated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(name, task.name) && Objects.equals(description, task.description) && Objects.equals(estimate, task.estimate) && Objects.equals(priority, task.priority) && Objects.equals(status, task.status) && Objects.equals(sprint, task.sprint) && Objects.equals(dateOfCreated, task.dateOfCreated) && Objects.equals(user, task.user) && Objects.equals(project, task.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, estimate, priority, status, sprint, dateOfCreated, user, project);
    }
}
