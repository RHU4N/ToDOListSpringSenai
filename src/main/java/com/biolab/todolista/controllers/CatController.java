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

@RestController
@RequestMapping("category")
public class CatController {
    private final CategoryService categoryService;

    public CatController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<?> addCat(@RequestBody @Valid CatReq catReq){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.saveCategory(catReq));
    }

    @GetMapping
    public ResponseEntity<?> getCat(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCatId(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategoryById(id));
    }

    @GetMapping("/desc")
    public ResponseEntity<?> getCatByDesc(@RequestParam String desc){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategoryByDesc(desc));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCat(@PathVariable long id, @RequestBody @Valid CatReq catReq){
        if (categoryService.getCategoryById(id) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }else {
            return  ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategory(id,catReq));
        }
    }

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
