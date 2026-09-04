package com.biolab.todolista.repositories;

import com.biolab.todolista.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Repository com inversão de dependancias
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(long id);
}
