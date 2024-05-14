package org.taskmanager.servicies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.taskmanager.enums.Status;
import org.taskmanager.exceptions.TaskManagerException;
import org.taskmanager.models.DTOs.TaskDTO;
import org.taskmanager.models.Task;
import org.taskmanager.repositories.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public Task createTask(TaskDTO taskDto) {
        Task newTask = Task.builder()
                .priority(taskDto.getPriority())
                .status(Status.PENDING)
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .createdAt(LocalDateTime.now())
                .dueDate(taskDto.getDueDate())
                .lastUpdated(LocalDateTime.now())
                .build();

        return taskRepository.save(newTask);
    }

    @Override
    public Task updateTask(TaskDTO taskDto) {
        Optional<Task> taskOptional = taskRepository.findById(taskDto.getId());
        Task task = taskOptional.orElseThrow(() -> new TaskManagerException("Task not found.", 404));
        task.setPriority(taskDto.getPriority());
        task.setStatus(taskDto.getStatus());
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setDueDate(taskDto.getDueDate());
        task.setLastUpdated(LocalDateTime.now());

        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(UUID taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(UUID id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskManagerException("Task not found.", 404));
    }
}
