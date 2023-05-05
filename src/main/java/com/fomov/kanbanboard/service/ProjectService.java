package com.fomov.kanbanboard.service;

import com.fomov.kanbanboard.model.Project;
import com.fomov.kanbanboard.model.User;
import com.fomov.kanbanboard.repository.ProjectRepository;
import com.fomov.kanbanboard.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
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
}
