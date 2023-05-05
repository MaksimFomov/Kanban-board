package com.fomov.kanbanboard.service;

import com.fomov.kanbanboard.enums.TaskStatus;
import com.fomov.kanbanboard.model.Project;
import com.fomov.kanbanboard.model.Task;
import com.fomov.kanbanboard.model.User;
import com.fomov.kanbanboard.repository.ProjectRepository;
import com.fomov.kanbanboard.repository.TaskRepository;
import com.fomov.kanbanboard.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Transactional
    public boolean createProject(Project project, List<Long> userIds) {
        if(projectRepository.findByName(project.getName()) != null) return false;

        List<User> users = new ArrayList<>();
        for (Long id : userIds) {
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                users.add(user.get());
            }
        }
        project.setDateOfCreated(LocalDateTime.now());
        project.setUsers(users);
        projectRepository.save(project);

        return true;
    }

    @Transactional
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Transactional
    public Project getProjectById(int id) {
        return projectRepository.findById(id);
    }

    @Transactional
    public Map<String, List<Task>> getTasksByStatus() {
        Map<String, List<Task>> tasksByStatus = new HashMap<>();
        for (TaskStatus status : TaskStatus.values()) {
            List<Task> tasks = taskRepository.findByStatus(status);
            tasksByStatus.put(status.toString(), tasks);
        }
        return tasksByStatus;
    }
}
