package com.example.Keycloak.service.impl;

import com.example.Keycloak.DTO.TaskDTO;
import com.example.Keycloak.mapper.TaskMapper;
import com.example.Keycloak.model.Task;
import com.example.Keycloak.model.User;
import com.example.Keycloak.repository.TaskRepository;
import com.example.Keycloak.repository.UserRepository;
import com.example.Keycloak.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    @Override
    public List<TaskDTO> getTasksByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow();

        Task task = new Task();
        task.setUser(user);

        return taskRepository.findAll().stream()
                .map(taskMapper::toDTO)
                .toList();
    }

    @Override
    public TaskDTO getTasksByIdAndUserId(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + taskId));

        task.setUser(user);

        return taskMapper.toDTO(task);
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        return taskMapper.toDTO(taskRepository.save(taskMapper.toEntity(taskDTO)));
    }

    @Override
    public TaskDTO updateTask(Long taskId, TaskDTO updatedTaskDTO, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + taskId));

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));

        task.setUser(user);
        task.setId(updatedTaskDTO.id());
        task.setUpdatedAt(updatedTaskDTO.updatedAt());
        task.setTitle(updatedTaskDTO.title());
        task.setDescription(updatedTaskDTO.description());
        task.setCreatedAt(updatedTaskDTO.createdAt());

        return taskMapper.toDTO(taskRepository.save(task));
    }

    @Override
    public void deleteTask(Long taskId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + taskId));

        if (user.getTasks().isEmpty()) {
            throw new RuntimeException("User has no tasks.");
        }

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));

        taskRepository.delete(task);
    }
}
