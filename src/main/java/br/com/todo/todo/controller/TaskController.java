package br.com.todo.todo.controller;

import br.com.todo.todo.dto.TaskDto;
import br.com.todo.todo.dto.UpdateTaskDto;
import br.com.todo.todo.enuns.Status;
import br.com.todo.todo.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/v1/task", name="Tasks Controllers")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping(value="/{id}", produces={MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Find task by id", description = "Find task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application",
                            schema = @Schema(implementation = TaskDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content)
    })
    private ResponseEntity<TaskDto> findById(@PathVariable(value="id") Long id) {
        TaskDto taskDto = taskService.findById(id);

        return ResponseEntity.ok().body(taskDto);
    }

    @GetMapping(produces={MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "List all task", description = "List all task, status is optional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application",
                            schema = @Schema(implementation = TaskDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content)
    })
    private ResponseEntity<Page<TaskDto>> findById(Status status,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size) {

        Page<TaskDto> pageDto = taskService.findTask(status, page, size);

        return ResponseEntity.ok().body(pageDto);
    }

    @PostMapping(produces={MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Create Task", description = "Add new Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = { @Content(mediaType = "application",
                            schema = @Schema(implementation = TaskDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Data Integrity Exception",
                    content = @Content),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity",
                    content = @Content)
    })
    public ResponseEntity<TaskDto> register(@Valid @RequestBody TaskDto TaskDto){
        TaskDto userDto = taskService.create(TaskDto);

        String urlUser = "/api/v1/task/" + userDto.getId();

        return ResponseEntity.created(URI.create(urlUser)).body(userDto);
    }

    @PutMapping(value="/{id}",produces={MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Update Task", description = "Update Task by updateTaskDto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application",
                            schema = @Schema(implementation = UpdateTaskDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Data Integrity Exception",
                    content = @Content),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity",
                    content = @Content)
    })
    public ResponseEntity<TaskDto> update(@PathVariable(value="id") Long id,
                                          @Valid @RequestBody UpdateTaskDto updateTaskDto){
        TaskDto userDto = taskService.update(id, updateTaskDto);

        return ResponseEntity.ok().body(userDto);
    }

    @PutMapping(value="/complete/{id}", produces={MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Change task's status", description = "Change a task's status to completed")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Data Integrity Exception",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity",
                    content = @Content)
    })
    public ResponseEntity<TaskDto> chageStatusTask(@PathVariable(value="id") Long id) {
        taskService.chageStatusTask(id, Status.COMPLETED);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value="/{id}", produces={MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Remove task", description = "Remove task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content)
    })
    private ResponseEntity<TaskDto> removeById(@PathVariable(value="id") Long id) {
        taskService.removeById(id);

        return ResponseEntity.noContent().build();
    }

}
