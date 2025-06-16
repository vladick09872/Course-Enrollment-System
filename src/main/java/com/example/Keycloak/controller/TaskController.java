package com.example.Keycloak.controller;

import com.example.Keycloak.DTO.TaskDTO;
import com.example.Keycloak.DTO.UserDTO;
import com.example.Keycloak.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.ws.rs.PATCH;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @Operation(summary = "Create task", description = "Add a new task to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task successfully created",
                    content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "404", description = "Invalid request data",
                    content = @Content(schema = @Schema()))
    })

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TaskDTO> create(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.createTask(taskDTO));
    }

    @Operation(summary = "Get Task by ID and user ID", description = "Retrieve a users details using their task ID and user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task found",
                    content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "404", description = "Task not found",
                    content = @Content(schema = @Schema()))
    })

    @GetMapping("/{taskId}/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<TaskDTO> getTasksByIdAndUserId(@PathVariable Long taskId, @PathVariable Long userId) {
        return ResponseEntity.ok(taskService.getTasksByIdAndUserId(taskId, userId));
    }

    @Operation(summary = "Update a user", description = "Update an existing users details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully",
                    content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(schema = @Schema()))
    })

    @PutMapping("/update/{taskId}/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long taskId, @PathVariable Long userId, @RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.updateTask(taskId, taskDTO, userId));
    }

    @Operation(summary = "Delete user by ID", description = "Deleting existing user details by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found",
                    content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(schema = @Schema()))
    })

    @DeleteMapping("/delete/{taskId}/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deleteTasK(@PathVariable Long taskId, @PathVariable Long userId) {
        taskService.deleteTask(taskId, taskId);

        return ResponseEntity.status(200)
                .build();
    }
}
