package org.taskmanager.servicies;

import org.taskmanager.models.InDTOs.TaskInDTO;
import org.taskmanager.models.OutDTOs.TaskOutDTO;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    TaskOutDTO createTask(TaskInDTO task);
    TaskOutDTO updateTask(TaskInDTO task);
    void deleteTask(UUID taskId);
    List<TaskOutDTO> getTasks();
    TaskOutDTO getTaskById(UUID id);
}
