package com.fomov.kanbanboard.service;

import com.fomov.kanbanboard.model.Task;
import com.fomov.kanbanboard.repository.ProjectRepository;
import com.fomov.kanbanboard.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    @Transactional
    public boolean createTask(Task task, int projectId) {
        if(taskRepository.findByName(task.getName()) != null) return false;

        task.setProject(projectRepository.findById(projectId));
        task.setDateOfCreated(LocalDateTime.now());
        taskRepository.save(task);

        return true;
    }

    @Transactional
    public List<Task> getTasksByProjectId(int id) {
        return taskRepository.findTasksByProjectId(id);
    }
}
