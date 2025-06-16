package com.example.Keycloak.service;

import com.example.Keycloak.DTO.TaskDTO;

import java.util.List;

public interface TaskService {
    List<TaskDTO> getTasksByUserId(Long userId);

    TaskDTO getTasksByIdAndUserId(Long taskId, Long userId);

    TaskDTO createTask(TaskDTO taskDTO);

    TaskDTO updateTask(Long taskId, TaskDTO updatedTaskDTO, Long userId);

    void deleteTask(Long taskId, Long userId);

}
