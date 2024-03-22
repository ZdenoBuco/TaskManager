package org.taskmanager.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class TaskManagerException extends RuntimeException{
    private int statusCode;
    public TaskManagerException(String message) {
        super(message);
    }
    public TaskManagerException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

}
