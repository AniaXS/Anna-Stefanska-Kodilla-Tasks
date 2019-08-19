package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTest {
    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void shouldMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L,"Mapper testing", "Create tests for all mappers");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(taskDto.getId(), task.getId());
        assertEquals(taskDto.getTitle(), task.getTitle());
        assertEquals(taskDto.getContent(), task.getContent());
    }

    @Test
    public void shouldMapToTaskEmptyTaskDto() {
        //Given
        TaskDto taskDto = new TaskDto();
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(taskDto.getId(), task.getId());
        assertEquals(taskDto.getTitle(), task.getTitle());
        assertEquals(taskDto.getContent(), task.getContent());
    }

    @Test
    public void shouldMapToTaskDto() {
        //Given
        Task task = new Task(4L, "New plan", "Create a new learning plan");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(task.getId(), taskDto.getId());
        assertEquals(task.getTitle(), taskDto.getTitle());
        assertEquals(task.getContent(), taskDto.getContent());
    }

    @Test
    public void shouldMapToTaskDtoEmptyTask() {
        //Given
        Task task = new Task();
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(task.getId(), taskDto.getId());
        assertEquals(task.getTitle(), taskDto.getTitle());
        assertEquals(task.getContent(), taskDto.getContent());
    }

    @Test
    public void shouldMapToTaskDtoList() {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L,"Shopping", "Buy coffee"));
        tasks.add(new Task(2L, "Paying bills", "Pay rent"));
        tasks.add(new Task(3L, "Car", "Refuel"));
        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(tasks);
        //Then
        assertEquals(tasks.size(), taskDtos.size());
        assertEquals(tasks.get(0).getId(), taskDtos.get(0).getId());
        assertEquals(tasks.get(1).getTitle(), taskDtos.get(1).getTitle());
        assertEquals(tasks.get(2).getContent(), taskDtos.get(2).getContent());
    }

    @Test
    public void shouldMapToTaskDtoListEmptyTask() {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(tasks);
        //Then
        assertEquals(tasks.size(), taskDtos.size());
        assertEquals(tasks.get(0).getId(), taskDtos.get(0).getId());
        assertEquals(tasks.get(0).getTitle(), taskDtos.get(0).getTitle());
        assertEquals(tasks.get(0).getContent(), taskDtos.get(0).getContent());
    }
}