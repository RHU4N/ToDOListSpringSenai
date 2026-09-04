package com.biolab.todolista.controllers;

import com.biolab.todolista.DTOs.Task.TaskReq;
import com.biolab.todolista.entities.Task;
import com.biolab.todolista.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//define controller e endpoint
@RestController
@RequestMapping("task")
public class TaskController {
    //importa service
    private  final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    //metodo post que puxa request para criar retornando 201
    @PostMapping
    public ResponseEntity<?> addTask(@RequestBody @Valid TaskReq taskReq){
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.saveTask(taskReq));
    }

    //metodo get que retorna lista e 200
    @GetMapping
    public ResponseEntity<?> getTask(){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTasks());
    }

    //metodo get por id que retorna dado e 200
    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getTaskById(id));
    }

    //puxa tasks por user
    @GetMapping("/user")
    public ResponseEntity<?> getTaskByUser(@RequestParam long userid){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getTaskByIdUser(userid));
    }


    //atualiza com id e request volta 200
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody @Valid TaskReq taskReq){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTask(id, taskReq));
    }

    //deleta retorna 200git
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.ok("Deletou");
    }






}
