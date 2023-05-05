package com.fomov.kanbanboard.repository;

import com.fomov.kanbanboard.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Project findByName(String name);
    Project findById(int id);
}
