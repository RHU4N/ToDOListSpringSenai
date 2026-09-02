package com.biolab.todolista.repositories;

import com.biolab.todolista.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Repository com inversão de dependancias
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    //função para pegar category a partir de descrição
    Category getCategoryByDescription(String desc);
}
