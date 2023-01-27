package br.com.todo.todo.service;

import br.com.todo.todo.dto.TaskDto;
import br.com.todo.todo.dto.UpdateTaskDto;
import br.com.todo.todo.entity.Task;
import br.com.todo.todo.enuns.Status;
import br.com.todo.todo.exception.DataIntegrityException;
import br.com.todo.todo.exception.ObjectNotFoundException;
import br.com.todo.todo.exception.UnprocessableEntity;
import br.com.todo.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository repoTask;

    private UrlMapperService urlMapperService;

    @Autowired
    public TaskService(TaskRepository repoTask, UrlMapperService urlMapperService){
        this.repoTask = repoTask;
        this.urlMapperService = urlMapperService;
    }

    public TaskDto findById(Long id) {
        Optional<Task> optional = repoTask.findById(id);

        if(optional.isEmpty() || optional.get().getId() == 0){
            throw new ObjectNotFoundException("Task not found!");
        }

        TaskDto taskDto = new TaskDto(optional.get());
        urlMapperService.mapperTaskDto(taskDto);

        return taskDto;
    }

    public TaskDto create(TaskDto taskDto) {
        Task task = new Task(taskDto);

        task.setId(null);
        task.setCreated(new Date());
        task.setUpdated(new Date());

        Task newTask = repoTask.save(task);

        TaskDto newTaskDto = new TaskDto(newTask);
        urlMapperService.mapperTaskDto(newTaskDto);

        return newTaskDto;
    }

    public TaskDto update(Long idTask, UpdateTaskDto updateTaskDto) {
        Optional<Task> optional = repoTask.findById(idTask);

        if(optional.isEmpty() || optional.get().getId() == 0){
            throw new ObjectNotFoundException("Task not found!");
        }

        Task task = optional.get();
        task.setDescription(updateTaskDto.getDescription());
        task.setPriority(updateTaskDto.getPriority());
        task.setUpdated(new Date());

        task = repoTask.save(task);

        TaskDto taskDto = new TaskDto(task);
        urlMapperService.mapperTaskDto(taskDto);

        return taskDto;
    }

    public Page<TaskDto> findTask(Status status, int pageNumber, int pageSize) {
        if(pageSize > 20){
            throw new DataIntegrityException("The maximum value for variable 'size' is 20");
        }else if(pageSize < 3){
            throw new DataIntegrityException("The minimum value for variable 'size' is 3");
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        Page<Task> page;

        if(status == null){
            page = repoTask.findAll(pageable);
        }else {
            page = repoTask.findByStatus(status, pageable);
        }

        Page<TaskDto> pageDto = page.map(this::convertTastToTaskDto);

        return pageDto;
    }

    public void removeById(Long id) {
        Optional<Task> optional = repoTask.findById(id);

        if(optional.isEmpty() || optional.get().getId() == 0){
            throw new ObjectNotFoundException("Task not found!");
        }

        repoTask.delete(optional.get());
    }

    public void chageStatusTask(Long id, Status status) {
        Optional<Task> optional = repoTask.findById(id);

        if(optional.isEmpty() || optional.get().getId() == 0){
            throw new ObjectNotFoundException("Task not found!");
        }else if (optional.get().getStatus() == status){
            throw new UnprocessableEntity(String.format("Task already has '%s'!", status));
        }

        optional.get().setStatus(Status.COMPLETED);
        optional.get().setUpdated(new Date());

        repoTask.save(optional.get());
    }

    private TaskDto convertTastToTaskDto(Task task){
        TaskDto taskDto = new TaskDto(task);
        urlMapperService.mapperTaskDto(taskDto);
        return taskDto;
    }

}
