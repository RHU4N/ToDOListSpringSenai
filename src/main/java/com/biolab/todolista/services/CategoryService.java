package com.biolab.todolista.services;

import com.biolab.todolista.DTOs.Category.CatReq;
import com.biolab.todolista.DTOs.Category.CatRes;
import com.biolab.todolista.entities.Category;
import com.biolab.todolista.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CatRes saveCategory(CatReq catReq) {
        Category category = new Category();
        category.setDescription(catReq.getDesc());

        categoryRepository.save(category);

        CatRes catRes = new CatRes();
        catRes.setId(category.getId());
        catRes.setDesc(category.getDescription());

        return catRes;
    }

    public List<CatRes> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CatRes> catRes = new ArrayList<>();
        for (Category category : categories) {
            CatRes catRes1 = new CatRes();
            catRes1.setId(category.getId());
            catRes1.setDesc(category.getDescription());
            catRes.add(catRes1);
        }
        return catRes;
    }

    public CatRes getCategoryById(long id) {
        Category c = categoryRepository.findById(id).orElseThrow();
        CatRes catRes = new CatRes();
        catRes.setId(c.getId());
        catRes.setDesc(c.getDescription());
        return catRes;
    }

    public CatRes getCategoryByDesc(String desc) {
        Category c = categoryRepository.getCategoryByDescription(desc);
        CatRes catRes = new CatRes();
        catRes.setId(c.getId());
        catRes.setDesc(c.getDescription());
        return catRes;
    }

    public CatRes updateCategory(long id,CatReq catReq) {
        Category category = categoryRepository.findById(id).orElseThrow();

        category.setDescription(catReq.getDesc());
        categoryRepository.save(category);
        CatRes catRes = new CatRes();
        catRes.setId(category.getId());
        catRes.setDesc(category.getDescription());
        return catRes;
    }

    public void deleteCategoryById(long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        }else {
            throw new IllegalArgumentException();
        }
    }
}
