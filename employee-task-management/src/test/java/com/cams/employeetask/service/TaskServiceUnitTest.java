package com.cams.employeetask.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.cams.employeetask.dto.TaskRequestDTO;
import com.cams.employeetask.entity.Task;
import com.cams.employeetask.entity.TaskStatus;
import com.cams.employeetask.repository.TaskRepository;
import com.cams.employeetask.service.impl.TaskServiceImpl;

public class TaskServiceUnitTest {

    private TaskRepository taskRepository;
    private EmployeeService employeeService;
    private TaskServiceImpl taskService;

    @BeforeEach
    void setup() {
        taskRepository = Mockito.mock(TaskRepository.class);
        employeeService = Mockito.mock(EmployeeService.class);
        taskService = new TaskServiceImpl(taskRepository, employeeService);
    }

    @Test
    void whenCreateTask_thenSaved() {
        Task t = new Task("T1","desc");
        t.setId(1L);
        when(taskRepository.save(any(Task.class))).thenReturn(t);

        TaskRequestDTO dto = new TaskRequestDTO("T1","desc");
        var res = taskService.createTask(dto);
        assertNotNull(res);
        assertEquals(1L, res.getId());
        assertEquals(TaskStatus.PENDING, res.getStatus());
    }
}
