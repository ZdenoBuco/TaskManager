package org.taskmanager.servicies;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.taskmanager.enums.Status;
import org.taskmanager.exceptions.TaskManagerException;
import org.taskmanager.models.AppUser;
import org.taskmanager.models.InDTOs.TaskInDTO;
import org.taskmanager.models.OutDTOs.TaskOutDTO;
import org.taskmanager.models.Task;
import org.taskmanager.repositories.AppUserRepository;
import org.taskmanager.repositories.TaskRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final AppUserRepository appUserRepository;
    private String userEmail;
    private AppUser user;
    private Boolean isAdmin;

    @Override
    public TaskOutDTO createTask(TaskInDTO taskDto) {
        loadSignedAppUser();
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

        return new TaskOutDTO(taskRepository.save(newTask));
    }

    @Override
    public TaskOutDTO updateTask(TaskInDTO taskDto) {
        loadSignedAppUser();
        Task task = taskRepository.findById(taskDto.getId()).orElseThrow(() -> new TaskManagerException("Task not found.", 404));
        taskOwnerCheck(task);
        task.setPriority(taskDto.getPriority());
        task.setStatus(taskDto.getStatus());
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setDueDate(taskDto.getDueDate());
        task.setLastUpdated(LocalDateTime.now());

        return new TaskOutDTO(taskRepository.save(task));
    }

    @Override
    @Transactional
    public void deleteTask(UUID taskId) {
        loadSignedAppUser();
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskManagerException("Task not found.", 404));
        taskOwnerCheck(task);
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<TaskOutDTO> getTasks() {
        loadSignedAppUser();
        if (isAdmin) {
            return taskRepository.findAll().stream().map(TaskOutDTO::new).collect(Collectors.toList());
        }
        return taskRepository.findAllByOwner_Email(userEmail).stream().map(TaskOutDTO::new).collect(Collectors.toList());
    }

    @Override
    public TaskOutDTO getTaskById(UUID id) {
        loadSignedAppUser();
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskManagerException("Task not found.", 404));
        taskOwnerCheck(task);
        return new TaskOutDTO(task);
    }

    private void taskOwnerCheck(Task task) {
        if (!userEmail.equals(task.getOwner().getEmail()) && !isAdmin) {
            throw new TaskManagerException("You are not authorized to modify or access this task.", 403);
        }
    }

    private void loadSignedAppUser() {
        userEmail = getContext().getAuthentication().getName();
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        isAdmin = authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ADMIN"));
        user = appUserRepository.findAppUserByEmail(userEmail).get();
    }
}
