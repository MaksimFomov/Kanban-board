package com.fomov.kanbanboard.service;

import com.fomov.kanbanboard.model.TaskStatus;
import com.fomov.kanbanboard.repository.TaskStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskStatusService {
    private final TaskStatusRepository taskStatusRepository;

    public TaskStatusService(TaskStatusRepository taskStatusRepository) {
        this.taskStatusRepository = taskStatusRepository;
    }

    public List<TaskStatus> getAllStatuses() {
        return taskStatusRepository.findAll();
    }
}
