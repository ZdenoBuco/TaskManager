package org.taskmanager.servicies;

import org.taskmanager.models.DTOs.TaskDTO;
import org.taskmanager.models.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    Task createTask(TaskDTO task);
    Task updateTask(TaskDTO task);
    void deleteTask(UUID taskId);
    List<Task> getTasks();
    Task getTaskById(UUID id);
}
