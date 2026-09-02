package com.biolab.todolista.services;

import com.biolab.todolista.DTOs.Task.TaskReq;
import com.biolab.todolista.DTOs.Task.TaskRes;
import com.biolab.todolista.entities.Category;
import com.biolab.todolista.entities.Task;
import com.biolab.todolista.entities.User;
import com.biolab.todolista.repositories.CategoryRepository;
import com.biolab.todolista.repositories.TaskRepository;
import com.biolab.todolista.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public TaskRes saveTask(TaskReq taskReq){
        Task task = new Task();
        Optional<Category> c = Optional.ofNullable(categoryRepository.getCategoryByDescription(taskReq.getCategory()));
        if (c.isEmpty()){
            Category category = new Category();
            category.setDescription(taskReq.getCategory());
            categoryRepository.save(category);
            task.setCategory(category);
        }else {
            task.setCategory(c.get());
        }
        User u = userRepository.findById(taskReq.getIdUser()).orElseThrow();
        task.setTitle(taskReq.getTaskName());
        task.setDescription(taskReq.getTaskDescription());
        task.setPriority(taskReq.getPriority());
        task.setIsChecked(taskReq.getIsChecked());
        task.setUser(u);
        taskRepository.save(task);
        TaskRes taskRes = new TaskRes();
        taskRes.setTaskId(task.getId());
        taskRes.setTaskName(task.getTitle());
        taskRes.setTaskDescription(task.getDescription());
        taskRes.setPriority(task.getPriority());
        taskRes.setIsChecked(task.getIsChecked());
        taskRes.setUser(task.getUser());
        taskRes.setCategory(task.getCategory());
        return taskRes;
    }

    public List<TaskRes> getAllTasks(){
        List<Task> tasks = taskRepository.findAll();
        List<TaskRes> taskRes = new ArrayList<>();
        for (Task task : tasks) {
            TaskRes taskRes1 = new TaskRes();
            taskRes1.setTaskId(task.getId());
            taskRes1.setTaskName(task.getTitle());
            taskRes1.setTaskDescription(task.getDescription());
            taskRes1.setPriority(task.getPriority());
            taskRes1.setIsChecked(task.getIsChecked());
            taskRes1.setUser(task.getUser());
            taskRes1.setCategory(task.getCategory());
            taskRes.add(taskRes1);
        }
        return taskRes;
    }

    public TaskRes getTaskById(long id){
        Task task = taskRepository.findById(id).orElseThrow();
        TaskRes taskRes = new TaskRes();
        taskRes.setTaskId(task.getId());
        taskRes.setTaskName(task.getTitle());
        taskRes.setTaskDescription(task.getDescription());
        taskRes.setPriority(task.getPriority());
        taskRes.setIsChecked(task.getIsChecked());
        taskRes.setUser(task.getUser());
        taskRes.setCategory(task.getCategory());
        return taskRes;
    }

    public TaskRes updateTask(long id,TaskReq taskReq){
        Task task = taskRepository.findById(id).orElseThrow();
        Category c = categoryRepository.getCategoryByDescription(taskReq.getCategory());
        User u = userRepository.findById(taskReq.getIdUser()).orElseThrow();
        task.setTitle(taskReq.getTaskName());
        task.setDescription(taskReq.getTaskDescription());
        task.setPriority(taskReq.getPriority());
        task.setIsChecked(taskReq.getIsChecked());
        task.setUser(u);
        task.setCategory(c);
        taskRepository.save(task);
        TaskRes taskRes = new TaskRes();
        taskRes.setTaskId(task.getId());
        taskRes.setTaskName(task.getTitle());
        taskRes.setTaskDescription(task.getDescription());
        taskRes.setPriority(task.getPriority());
        taskRes.setIsChecked(task.getIsChecked());
        taskRes.setUser(task.getUser());
        taskRes.setCategory(task.getCategory());
        return taskRes;
    }

    public void deleteTask(long id){
        taskRepository.deleteById(id);
    }
}
