package br.com.todo.todo.dto;

import br.com.todo.todo.entity.Link;
import br.com.todo.todo.entity.Task;
import br.com.todo.todo.enuns.Priority;
import br.com.todo.todo.enuns.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UpdateTaskDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "is mandatory")
    @Size(min = 20, max = 512, message = "must contain between 20 and 512 characters")
    private String description;

    private Priority priority;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
