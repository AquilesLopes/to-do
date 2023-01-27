package br.com.todo.todo.entity;

import br.com.todo.todo.dto.TaskDto;
import br.com.todo.todo.enuns.Priority;
import br.com.todo.todo.enuns.Status;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 30)
    private String title;

    @Column(length = 70)
    private String subtitle;

    @Column(length = 512)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private Priority priority;

    private Date created;

    private Date updated;

    public Task() {
        super();
    }

    public Task(TaskDto taskDto) {
        this.id = taskDto.getId();
        this.title = taskDto.getTitle();
        this.subtitle = taskDto.getSubtitle();
        this.description = taskDto.getDescription();
        this.priority = taskDto.getPriority();
        this.status = taskDto.getStatus();
        this.created = taskDto.getCreated();
        this.updated = taskDto.getUpdated();
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
        if (!(o instanceof Task task)) return false;

        return getId() != null ? getId().equals(task.getId()) : task.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", priority=" + priority +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
