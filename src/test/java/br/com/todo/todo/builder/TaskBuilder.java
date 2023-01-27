package br.com.todo.todo.builder;


import br.com.todo.todo.entity.Task;
import br.com.todo.todo.enuns.Priority;
import br.com.todo.todo.enuns.Status;

import java.util.ArrayList;
import java.util.List;

public class TaskBuilder {
    private Task task;
    private TaskBuilder(){}

    public static TaskBuilder createOne(){
        TaskBuilder builder = new TaskBuilder();
        builder.task = new Task();
        builder.task.setId(314L);
        builder.task.setTitle("");
        builder.task.setSubtitle("");
        builder.task.setStatus(Status.PENDING);
        builder.task.setDescription("");
        builder.task.setPriority(Priority.MEDIUM);
        return builder;
    }

    public TaskBuilder withId(Long id){
        task.setId(id);
        return this;
    }

    public TaskBuilder withTitle(String title){
        task.setTitle(title);
        return this;
    }

    public TaskBuilder withStatus(Status status){
        task.setStatus(status);
        return this;
    }

    public TaskBuilder withPriority(Priority priority){
        task.setPriority(priority);
        return this;
    }

    public Task builder(){
        return task;
    }

    public static List<Task> createList(int size){
        List<Task> listTask = new ArrayList<Task>();
        for(Long i = 0L; i < size; i++){
            Task task = new Task();
            task.setId(i);
            task.setTitle("");
            task.setSubtitle("");
            task.setStatus(Status.PENDING);
            task.setDescription("");
            task.setPriority(Priority.MEDIUM);
            listTask.add(task);
        }
        return listTask;
    }


}
