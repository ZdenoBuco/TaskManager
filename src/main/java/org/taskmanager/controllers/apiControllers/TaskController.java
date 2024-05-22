package org.taskmanager.controllers.apiControllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taskmanager.models.InDTOs.AuthIdentityInDTO;
import org.taskmanager.models.InDTOs.TaskInDTO;
import org.taskmanager.models.OutDTOs.TaskOutDTO;
import org.taskmanager.servicies.TaskService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("")
    public ResponseEntity<TaskOutDTO> createTask(@RequestBody TaskInDTO task, AuthIdentityInDTO authIdentityInDTO) {
            return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(task, authIdentityInDTO));
    }

    @PutMapping("")
    public ResponseEntity<TaskOutDTO> updateTask(@RequestBody TaskInDTO task, AuthIdentityInDTO authIdentityInDTO) {
            return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTask(task, authIdentityInDTO));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable UUID taskId, AuthIdentityInDTO authIdentityInDTO) {
            taskService.deleteTask(taskId, authIdentityInDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskOutDTO> getTask(@PathVariable UUID taskId, AuthIdentityInDTO authIdentityInDTO) {
            return ResponseEntity.status(HttpStatus.OK).body(taskService.getTaskById(taskId, authIdentityInDTO));
    }

    @GetMapping("")
    public ResponseEntity<List<TaskOutDTO>> getTasks(AuthIdentityInDTO authIdentityInDTO) {
            return ResponseEntity.status(HttpStatus.OK).body(taskService.getTasks(authIdentityInDTO));
    }
}
