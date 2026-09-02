package com.biolab.todolista.controllers;

import com.biolab.todolista.DTOs.Task.TaskReq;
import com.biolab.todolista.entities.Task;
import com.biolab.todolista.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("task")
public class TaskController {
    private  final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<?> addTask(@RequestBody @Valid TaskReq taskReq){
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.saveTask(taskReq));
    }

    @GetMapping
    public ResponseEntity<?> getTask(){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getTaskById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody @Valid TaskReq taskReq){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTask(id, taskReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.ok("Deletou");
    }






}
