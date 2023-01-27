package br.com.todo.todo.dto;

import br.com.todo.todo.entity.Link;
import br.com.todo.todo.entity.Task;
import br.com.todo.todo.enuns.Priority;
import br.com.todo.todo.enuns.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TaskDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "is mandatory")
    @Size(min = 5, max = 30, message = "must contain between 5 and 30 characters")
    private String title;

    @NotBlank(message = "is mandatory")
    @Size(min = 10, max = 70, message = "must contain between 10 and 70 characters")
    private String subtitle;

    @NotBlank(message = "is mandatory")
    @Size(min = 20, max = 512, message = "must contain between 20 and 512 characters")
    private String description;

    private Status status;

    private Priority priority;

    private Date created;

    private Date updated;

    private List<Link> links;

    public TaskDto() {
        super();
    }

    public TaskDto(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.subtitle = task.getSubtitle();
        this.description = task.getDescription();
        this.priority = task.getPriority();
        this.status = task.getStatus();
        this.created = task.getCreated();
        this.updated = task.getUpdated();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskDto task)) return false;

        return getId() != null ? getId().equals(task.getId()) : task.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TaskDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", priority=" + priority +
                ", created=" + created +
                ", updated=" + updated +
                ", links=" + links +
                '}';
    }
}
