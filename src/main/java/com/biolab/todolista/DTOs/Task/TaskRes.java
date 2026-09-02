package com.biolab.todolista.DTOs.Task;

import com.biolab.todolista.entities.Category;
import com.biolab.todolista.entities.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.biolab.todolista.entities.Priority;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRes {
    private long taskId;
    private String taskName;
    private String taskDescription;
    private Priority priority;
    private Boolean isChecked;
    private Category Category;
    private User User;


}
