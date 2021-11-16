package com.example.demo.controller;

import com.example.demo.dto.TaskDto;
import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskController {
    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/getTasks")
    public List<TaskDto> getAllTask() {
        List<TaskDto> lstTaskDto = new ArrayList<>();
        List<Task> lstTask = taskRepository.findAll();

        for (Task task : lstTask) {
            TaskDto taskDto = new TaskDto();
            BeanUtils.copyProperties(task, taskDto);
            lstTaskDto.add(taskDto);
        }

        return lstTaskDto;
    }
}
