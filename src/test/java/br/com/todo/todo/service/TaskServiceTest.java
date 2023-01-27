package br.com.todo.todo.service;

import br.com.todo.todo.builder.TaskBuilder;
import br.com.todo.todo.dto.TaskDto;
import br.com.todo.todo.entity.Task;
import br.com.todo.todo.enuns.Status;
import br.com.todo.todo.repository.TaskRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TaskServiceTest {
    @InjectMocks
    TaskService taskService;

    @Mock
    TaskRepository taskRepo;

    @BeforeAll
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    void init() {}

    @Test
    void shouldReturnTaskDtoById() {
        Task taskMock = TaskBuilder.createOne().builder();
        Optional<Task> optional = Optional.of(taskMock);

        Mockito.doReturn(optional).when(taskRepo).findById(taskMock.getId());

        TaskDto taskDto = taskService.findById(taskMock.getId());

        Assertions.assertEquals(taskMock.getId(), taskDto.getId());
        Assertions.assertEquals(taskMock.getTitle(), taskDto.getTitle());
    }

    @Test
    void shouldReturnTaskDtoPage() {
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        List<Task> listTaskMock = TaskBuilder.createList(size);
        Page<Task> pageTaskMock = new PageImpl<>(listTaskMock);

        Mockito.doReturn(pageTaskMock).when(taskRepo).findAll(pageable);

        Page<TaskDto> pageCaepiBD = taskService.findTask(Status.NO_STATUS, page, size);

        Assertions.assertEquals(pageTaskMock.getTotalElements(), pageCaepiBD.getTotalElements(), "Should return a Page<CaepiDTO> with 10 tasks");
    }

}
