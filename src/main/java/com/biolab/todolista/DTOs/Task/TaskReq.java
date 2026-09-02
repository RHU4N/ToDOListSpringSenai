package com.biolab.todolista.DTOs.Task;

import com.biolab.todolista.entities.Category;
import com.biolab.todolista.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.biolab.todolista.entities.Priority;

//Definindo getters and setters e construtores
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskReq {
    //Informações que precisam ser enviadas por request
    private String taskName;
    private String taskDescription;
    private Priority priority;
    private Boolean isChecked;
    private String category; // envia string pra pegar descrição de categoria e pegar as infors via findByDesc
    private Long idUser; //envia só o id de user e pega dados via FindById
}
