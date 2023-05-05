package com.fomov.kanbanboard.repository;

import com.fomov.kanbanboard.model.Task;
import com.fomov.kanbanboard.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    Task findByName(String name);
    List<Task> findByStatusAndProjectId(TaskStatus status, int projectId);
}
