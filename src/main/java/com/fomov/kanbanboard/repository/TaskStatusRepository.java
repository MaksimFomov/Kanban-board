package com.fomov.kanbanboard.repository;

import com.fomov.kanbanboard.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Integer> {
}
