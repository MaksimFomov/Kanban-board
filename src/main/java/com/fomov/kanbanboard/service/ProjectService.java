package com.fomov.kanbanboard.service;

import com.fomov.kanbanboard.model.Project;
import com.fomov.kanbanboard.model.Task;
import com.fomov.kanbanboard.model.TaskStatus;
import com.fomov.kanbanboard.model.User;
import com.fomov.kanbanboard.repository.ProjectRepository;
import com.fomov.kanbanboard.repository.TaskRepository;
import com.fomov.kanbanboard.repository.TaskStatusRepository;
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
    private final TaskStatusRepository taskStatusRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository, TaskRepository taskRepository, TaskStatusRepository taskStatusRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.taskStatusRepository = taskStatusRepository;
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
    public Map<String, List<Task>> getTasksByStatus(int projectId) {
        Map<String, List<Task>> tasksByStatus = new HashMap<>();
        for (TaskStatus status: taskStatusRepository.findAll()) {
            List<Task> tasks = taskRepository.findByStatusAndProjectId(status, projectId);
            tasksByStatus.put(status.getName(), tasks);
        }
        return tasksByStatus;
    }

}
