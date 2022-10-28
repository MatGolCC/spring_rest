package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
public class TaskController {
    private final TaskService taskService;

    // DI
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

//    @PostMapping("/task")
//    public Task addTask(
//            @RequestParam("taskId") int taskId,
//            @RequestParam("taskName") String taskName) {
//        return taskService.addTask(taskId, taskName);
//    }

    @PostMapping("/task")
    public Task addTask(
            @RequestParam("taskName") String taskName,
            @RequestParam("taskDeadline") String taskDeadline
    ) {
        return taskService.addTask(taskName, LocalDateTime.parse(taskDeadline));
    }

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @PutMapping("/task/{taskId}")
    public void updateTask(
            @PathVariable("taskId") int taskId,
            @RequestParam("taskName") String taskName
    ) {
        taskService.updateTaskName(taskId, taskName);
    }

    @DeleteMapping("/tasks/{taskId}")
    public boolean deleteTask(@PathVariable("taskId") int taskId) {
        return taskService.deleteTaskById(taskId);
    }

}
