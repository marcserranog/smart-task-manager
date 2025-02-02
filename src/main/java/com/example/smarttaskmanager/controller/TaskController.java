package com.example.smarttaskmanager.controller;

import com.example.smarttaskmanager.model.Task;
import com.example.smarttaskmanager.model.User;
import com.example.smarttaskmanager.service.TaskService;
import com.example.smarttaskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    @PostMapping("/{userId}")
    public ResponseEntity<Task> createTask(@PathVariable Long userId, @RequestBody Task task) {
        User user = userService.getUserById(userId).orElseThrow();
        return ResponseEntity.ok(taskService.createTask(task, user));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getTasksByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(taskService.getTasksByUser(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
