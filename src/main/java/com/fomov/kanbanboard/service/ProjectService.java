package com.fomov.kanbanboard.service;

import com.fomov.kanbanboard.model.Project;
import com.fomov.kanbanboard.model.User;
import com.fomov.kanbanboard.repository.ProjectRepository;
import com.fomov.kanbanboard.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public boolean createProject(Project project, List<Long> userIds) {
        if(projectRepository.findByName(project.getName()) != null) return false;

        Set<User> users = new HashSet<>();
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

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}
