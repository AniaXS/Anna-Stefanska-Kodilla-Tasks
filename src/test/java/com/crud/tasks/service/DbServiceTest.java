package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {
    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    public void shouldFetchAllTasks() {
        //Given
        Task task1 = new Task(1L, "Tasks", "Create tasks");
        Task task2 = new Task(2L, "Tests", "Create tests");
        Task task3 = new Task(3L, "Lists", "Create lists");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        when(repository.findAll()).thenReturn(tasks);
        //When
        List allTasks = dbService.getAllTasks();
        //Then
        assertEquals(3, allTasks.size());
    }

    @Test
    public void shouldFetchEmptyTaskList() {
        //Given
        when(dbService.getAllTasks()).thenReturn(new ArrayList<>());
        //When
        List allTasks = dbService.getAllTasks();
        //Then
        assertEquals(0, allTasks.size());
    }

    @Test
    public void shouldFetchTask() {
        //Given
        Task task = new Task(1L, "Tasks", "Create tasks");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        when(repository.findById(1L)).thenReturn(Optional.of(task));
        //When
        Task result = dbService.getTask(1L).get();
        //Then
        assertEquals(1L, result.getId(), 0);
        assertEquals("Tasks", result.getTitle());
        assertEquals("Create tasks", result.getContent());
    }

    @Test
    public void shouldSaveTask() {
        //Given
        Task task = new Task(1L, "Tasks", "Create tasks");
        Task updatedTask = new Task(12L, "Updated Tasks", "Create new tasks");
        when(repository.save(task)).thenReturn(updatedTask);
        //When
        Task result = dbService.saveTask(task);
        //Then
        assertEquals(12L, result.getId(), 0);
        assertEquals("Updated Tasks", result.getTitle());
        assertEquals("Create new tasks", result.getContent());
    }

    @Test
    public void shouldDeleteTask() {
        //When
        dbService.deleteTask(12L);
        //Then
        Mockito.verify(repository, times(1)).delete(12L);
    }
}