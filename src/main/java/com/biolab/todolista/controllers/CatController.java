package com.biolab.todolista.controllers;

import com.biolab.todolista.DTOs.Category.CatReq;
import com.biolab.todolista.DTOs.Category.CatRes;
import com.biolab.todolista.entities.Category;
import com.biolab.todolista.services.CategoryService;
import com.biolab.todolista.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

//define controler e qual é o endpoint
@RestController
@RequestMapping("category")
public class CatController {
    //puxa service
    private final CategoryService categoryService;

    public CatController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //metodo post que valida os dados do Request e envia para a função save retornando status 201
    @PostMapping
    public ResponseEntity<?> addCat(@RequestBody @Valid CatReq catReq){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.saveCategory(catReq));
    }

    //metodo get que volta lista com 200
    @GetMapping
    public ResponseEntity<?> getCat(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategories());
    }

    //metodo get que pede id que volta dado com 200
    @GetMapping("/{id}")
    public ResponseEntity<?> getCatId(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategoryById(id));
    }

    //metodo get que pede ?desc= que volta dado com 200
    @GetMapping("/desc")
    public ResponseEntity<?> getCatByDesc(@RequestParam String desc){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategoryByDesc(desc));
    }

    //metodo put que pede id verifica se o dado existe se não 404 se sim 200 e atualiza
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCat(@PathVariable long id, @RequestBody @Valid CatReq catReq){
        if (categoryService.getCategoryById(id) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }else {
            return  ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategory(id,catReq));
        }
    }

    //deleta por id  verifica valor se tiver 200 se não 404
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCat(@PathVariable long id){
        if (categoryService.getCategoryById(id) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }else {
            categoryService.deleteCategoryById(id);
            return ResponseEntity.ok("deletado");
        }
    }
}
