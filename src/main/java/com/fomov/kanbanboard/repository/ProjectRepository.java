package com.fomov.kanbanboard.repository;

import com.fomov.kanbanboard.model.Project;
import com.fomov.kanbanboard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Project findByName(String name);
    Project findById(int id);
    List<Project> findByUsers(User user);
}
