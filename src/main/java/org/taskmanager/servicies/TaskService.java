package org.taskmanager.servicies;

import org.taskmanager.models.InDTOs.AuthIdentityInDTO;
import org.taskmanager.models.InDTOs.TaskInDTO;
import org.taskmanager.models.OutDTOs.TaskOutDTO;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    TaskOutDTO createTask(TaskInDTO task, AuthIdentityInDTO authIdentityInDTO);
    TaskOutDTO updateTask(TaskInDTO task, AuthIdentityInDTO authIdentityInDTO);
    void deleteTask(UUID taskId, AuthIdentityInDTO authIdentityInDTO);
    List<TaskOutDTO> getTasks(AuthIdentityInDTO authIdentityInDTO);
    TaskOutDTO getTaskById(UUID id, AuthIdentityInDTO authIdentityInDTO);
}
