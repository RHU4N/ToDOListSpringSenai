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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

//declara service
@Service
public class TaskService {
    //importa repositories para inversão de dependencia
    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    //salva task retorna response pegando request
    public TaskRes saveTask(TaskReq taskReq){
        Task task = new Task();//instancia Task
        //pega valor de category pela desc
        Optional<Category> c = Optional.ofNullable(categoryRepository.getCategoryByDescription(taskReq.getCategory()));
        if (c.isEmpty()){//caso não tenha cria aquele valor de desc
            Category category = new Category();
            category.setDescription(taskReq.getCategory());
            categoryRepository.save(category);
            task.setCategory(category);
        }else {//caso tenha só coloca em task
            task.setCategory(c.get());
        }
        //pega valor de user pelo id
        User u = userRepository.findById(taskReq.getIdUser()).orElseThrow();
        //preenche os dados
        task.setTitle(taskReq.getTaskName());
        task.setDescription(taskReq.getTaskDescription());
        task.setPriority(taskReq.getPriority());
        task.setIsChecked(taskReq.getIsChecked());
        task.setUser(u);
        taskRepository.save(task);//salva no banco
        //converte para return
        TaskRes taskRes = new TaskRes();
        taskRes.setTaskId(task.getId());
        taskRes.setTaskName(task.getTitle());
        taskRes.setTaskDescription(task.getDescription());
        taskRes.setPriority(task.getPriority());
        taskRes.setIsChecked(task.getIsChecked());
        //pega só o dado que cabe no Response
        taskRes.setIdUser(task.getUser().getId());
        //pega só o dado que cabe no Response
        taskRes.setCategory(task.getCategory().getDescription());
        return taskRes;
    }

    //pega todos os dados e devolve lista igual todos os outros services
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
            taskRes1.setIdUser(task.getUser().getId());
            taskRes1.setCategory(task.getCategory().getDescription());
            taskRes.add(taskRes1);
        }
        return taskRes;
    }

    //pega dado por id e devolve igual os outros services
    public TaskRes getTaskById(long id){
        Task task = taskRepository.findById(id).orElseThrow();
        TaskRes taskRes = new TaskRes();
        taskRes.setTaskId(task.getId());
        taskRes.setTaskName(task.getTitle());
        taskRes.setTaskDescription(task.getDescription());
        taskRes.setPriority(task.getPriority());
        taskRes.setIsChecked(task.getIsChecked());
        taskRes.setIdUser(task.getUser().getId());
        taskRes.setCategory(task.getCategory().getDescription());
        return taskRes;
    }

    public List<TaskRes> getTaskByIdUser(long idUser){
        List<Task> userTasks = taskRepository.findByUserId(idUser);
        List<TaskRes> response = new ArrayList<>();
        for (Task userTask : userTasks) {
            TaskRes taskRes1 = new TaskRes();
            taskRes1.setTaskId(userTask.getId());
            taskRes1.setTaskName(userTask.getTitle());
            taskRes1.setTaskDescription(userTask.getDescription());
            taskRes1.setPriority(userTask.getPriority());
            taskRes1.setIsChecked(userTask.getIsChecked());
            taskRes1.setIdUser(userTask.getUser().getId());
            taskRes1.setCategory(userTask.getCategory().getDescription());
            response.add(taskRes1);
        }
        return response;

    }

    //altera igual outros services
    public TaskRes updateTask(long id,TaskReq taskReq){
        Task task = taskRepository.findById(id).orElseThrow(); //pega a task por id
        Category c = categoryRepository.getCategoryByDescription(taskReq.getCategory()); //pega categoria por desc
        User u = userRepository.findById(taskReq.getIdUser()).orElseThrow();//pega user por id
        task.setTitle(taskReq.getTaskName());
        task.setDescription(taskReq.getTaskDescription());
        task.setPriority(taskReq.getPriority());
        task.setIsChecked(taskReq.getIsChecked());
        task.setUser(u);
        task.setCategory(c);
        taskRepository.save(task);//salva as alterações
        TaskRes taskRes = new TaskRes(); //converte para return
        taskRes.setTaskId(task.getId());
        taskRes.setTaskName(task.getTitle());
        taskRes.setTaskDescription(task.getDescription());
        taskRes.setPriority(task.getPriority());
        taskRes.setIsChecked(task.getIsChecked());
        taskRes.setIdUser(task.getUser().getId());
        taskRes.setCategory(task.getCategory().getDescription());
        return taskRes;
    }

    //deleta
    public void deleteTask(long id){
        taskRepository.deleteById(id);
    }
}
