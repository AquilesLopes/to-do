package br.com.todo.todo.service;

import br.com.todo.todo.dto.TaskDto;
import br.com.todo.todo.entity.Link;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UrlMapperService {

    public static void mapperTaskDto(TaskDto taskDto){
        Link linkRemove = new Link();
        linkRemove.setUrl("/api/v1/task/" + taskDto.getId());
        linkRemove.setMethod("DELETE");
        linkRemove.setDescription("Remove a task");

        Link linkChange = new Link();
        linkChange.setUrl("/api/v1/task/" + taskDto.getId());
        linkChange.setMethod("PUT");
        linkChange.setDescription("Change a task");

        Link linkComplete = new Link();
        linkComplete.setUrl("/api/v1/task/complete/" + taskDto.getId());
        linkComplete.setMethod("PUT");
        linkComplete.setDescription("Complete a task");

        taskDto.setLinks(Arrays.asList(linkRemove, linkChange, linkComplete));
    }
}
