package com.malavika.taskmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    void setupSecurityContext() {
        Authentication authentication = new UsernamePasswordAuthenticationToken("testuser", null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void testGetAllTasks() {
        User fakeUser = new User();
        fakeUser.setId(1L);
        fakeUser.setUsername("testuser");

        Task task1 = new Task();
        task1.setId(1L);
        task1.setTitle("Task One");

        Task task2 = new Task();
        task2.setId(2L);
        task2.setTitle("Task Two");

        List<Task> fakeTaskList = Arrays.asList(task1, task2);

        when(taskRepository.findByUser(fakeUser)).thenReturn(fakeTaskList);

        List<Task> result = taskController.getAllTasks();

        assertEquals(2, result.size());
    }
    @Test
    void testCreateTask() {
        User fakeUser = new User();
        fakeUser.setId(1L);
        fakeUser.setUsername("testuser");

        Task newTask = new Task();
        newTask.setTitle("New Task");

        when(userRepository.findByUsername("testuser")).thenReturn(java.util.Optional.of(fakeUser));
        when(taskRepository.save(newTask)).thenReturn(newTask);

        Task result = taskController.createTask(newTask);

        assertEquals("New Task", result.getTitle());
        assertEquals(fakeUser, result.getUser());
        verify(taskRepository).save(newTask);
    }
    @Test
    void testDeleteTask_TaskNotFound() {
        when(taskRepository.existsById(99L)).thenReturn(false);

        assertThrows(TaskNotFoundException.class, () -> {
            taskController.deleteTask(99L);
        });
    }
}