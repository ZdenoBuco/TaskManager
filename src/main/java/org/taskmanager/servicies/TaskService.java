package org.taskmanager.servicies;

import org.springframework.stereotype.Service;
import org.taskmanager.models.DTOs.TaskDTO;
import org.taskmanager.models.Task;

import java.util.List;
import java.util.UUID;

@Service
public interface TaskService {
    Task createTask(TaskDTO task);
    Task updateTask(TaskDTO task);
    void deleteTask(TaskDTO task);
    List<Task> getTasks();
    Task getTaskById(UUID id);
}
