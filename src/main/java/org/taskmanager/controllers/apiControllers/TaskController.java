package org.taskmanager.controllers.apiControllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taskmanager.models.DTOs.TaskDTO;
import org.taskmanager.models.Task;
import org.taskmanager.servicies.TaskService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("")
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO task) {
            return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(task));
    }

    @PutMapping("")
    public ResponseEntity<Task> updateTask(@RequestBody TaskDTO task) {
            return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTask(task));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Task> deleteTask(@PathVariable UUID taskId) {
            taskService.deleteTask(taskId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable UUID taskId) {
            return ResponseEntity.status(HttpStatus.OK).body(taskService.getTaskById(taskId));
    }

    @GetMapping("")
    public ResponseEntity<List<Task>> getTasks() {
            return ResponseEntity.status(HttpStatus.OK).body(taskService.getTasks());
    }
}
