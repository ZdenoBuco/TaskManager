package org.taskmanager.servicies;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.taskmanager.enums.Status;
import org.taskmanager.exceptions.TaskManagerException;
import org.taskmanager.models.AppUser;
import org.taskmanager.models.DTOs.TaskDTO;
import org.taskmanager.models.Task;
import org.taskmanager.repositories.AppUserRepository;
import org.taskmanager.repositories.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final AppUserRepository appUserRepository;
    private String userEmail;

    @Override
    public Task createTask(TaskDTO taskDto) {
        userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser user = appUserRepository.findAppUserByEmail(userEmail).get();

        Task newTask = Task.builder()
                .owner(user)
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
        userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Task> taskOptional = taskRepository.findById(taskDto.getId());
        Task task = taskOptional.orElseThrow(() -> new TaskManagerException("Task not found.", 404));
        taskOwnerCheck(task);
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
        userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskManagerException("Task not found.", 404));
        taskOwnerCheck(task);
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<Task> getTasks() {
        userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return taskRepository.findAllByOwner_Email(userEmail);
    }

    @Override
    public Task getTaskById(UUID id) {
        userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskManagerException("Task not found.", 404));
        taskOwnerCheck(task);
        return task;
    }

    private void taskOwnerCheck(Task task) {
        if (!userEmail.equals(task.getOwner().getEmail())) {
            throw new TaskManagerException("You are not authorized to modify or access this task.", 403);
        }
    }
}
