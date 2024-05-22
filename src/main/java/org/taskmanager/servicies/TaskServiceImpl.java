package org.taskmanager.servicies;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.taskmanager.enums.Status;
import org.taskmanager.exceptions.TaskManagerException;
import org.taskmanager.models.InDTOs.AuthIdentityInDTO;
import org.taskmanager.models.InDTOs.TaskInDTO;
import org.taskmanager.models.OutDTOs.TaskOutDTO;
import org.taskmanager.models.Task;
import org.taskmanager.repositories.AppUserRepository;
import org.taskmanager.repositories.TaskRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final AppUserRepository appUserRepository;

    @Override
    public TaskOutDTO createTask(TaskInDTO taskDto, AuthIdentityInDTO authIdentityInDTO) {
        Task newTask = Task.builder()
                .owner(appUserRepository.findAppUserByEmail(authIdentityInDTO.getUserEmail())
                        .orElseThrow(() -> new TaskManagerException("User does not exist or it was deleted", 404)))
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
    public TaskOutDTO updateTask(TaskInDTO taskDto, AuthIdentityInDTO authIdentityInDTO) {
        Task task = taskRepository.findById(taskDto.getId()).orElseThrow(() -> new TaskManagerException("Task not found.", 404));
        taskOwnerCheck(task, authIdentityInDTO);
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
    public void deleteTask(UUID taskId, AuthIdentityInDTO authIdentityInDTO) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskManagerException("Task not found.", 404));
        taskOwnerCheck(task, authIdentityInDTO);
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<TaskOutDTO> getTasks(AuthIdentityInDTO authIdentityInDTO) {
        if (authIdentityInDTO.getIsAdmin()) {
            return taskRepository.findAll()
                    .stream()
                    .map(TaskOutDTO::new)
                    .collect(Collectors.toList());
        }
        return taskRepository.findAllByOwner_Email(authIdentityInDTO.getUserEmail())
                .stream()
                .map(TaskOutDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public TaskOutDTO getTaskById(UUID id, AuthIdentityInDTO authIdentityInDTO) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskManagerException("Task not found.", 404));
        taskOwnerCheck(task, authIdentityInDTO);
        return new TaskOutDTO(task);
    }

    private void taskOwnerCheck(Task task, AuthIdentityInDTO authIdentityInDTO) {
        if (!authIdentityInDTO.getUserEmail().equals(task.getOwner().getEmail())
                && !authIdentityInDTO.getIsAdmin()) {
            throw new TaskManagerException("You are not authorized to modify or access this task.", 403);
        }
    }
}
