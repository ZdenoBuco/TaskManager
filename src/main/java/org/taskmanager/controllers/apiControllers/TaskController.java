package org.taskmanager.controllers.apiControllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taskmanager.exceptions.TaskManagerException;
import org.taskmanager.models.DTOs.TaskDTO;
import org.taskmanager.servicies.TaskService;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("")
    public ResponseEntity<?> createTask(@RequestBody TaskDTO task) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(task));
        } catch (TaskManagerException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }

    @PutMapping("")
    public ResponseEntity<?> updateTask(@RequestBody TaskDTO task) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTask(task));
        } catch (TaskManagerException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable UUID taskId) {
        try {
            taskService.deleteTask(taskId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (TaskManagerException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<?> getTask(@PathVariable UUID taskId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(taskService.getTaskById(taskId));
        } catch (TaskManagerException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getTasks() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(taskService.getTasks());
        } catch (TaskManagerException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }
}
