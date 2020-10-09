package edu.ap.projectteambisfits;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.ap.projectteambisfits.category.Category;
import edu.ap.projectteambisfits.task.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class TaskServiceImplTest {

    @Mock
    TaskRepository taskRepo;
    @InjectMocks
    TaskServiceImpl taskService;

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    public void shouldGetAllTasks() throws Exception {
        Category category = new Category("Test");
        when(taskRepo.findAll()).thenReturn(
            Stream.of(new Task("Test", "Test", "Test", "Test", category, true, "")).collect(Collectors.toList()));
            assertEquals(1, taskService.findAll().size());
        }
        
    @Test
    public void shouldCreateTask() throws Exception {    
        Category category = new Category("Test");
        Task task = new Task("Test", "Test", "Test", "Test", category, true, "");
        when(taskRepo.save(task)).thenReturn(task);
        assertEquals(task, taskService.saveTask(task));
    }

    @Test
    public void shouldDeleteTaskById() throws Exception {
        Category category = new Category("Test");
        Task task = new Task("Test", "Test", "Test", "Test", category, true, "");
        when(taskRepo.save(task)).thenReturn(task);
        assertEquals(task, taskService.saveTask(task));
        taskService.deleteTask(task.getId());
        assertNotEquals(task, taskRepo.findById(task.getId()));
    }
}

