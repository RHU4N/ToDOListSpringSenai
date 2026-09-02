package com.biolab.todolista.services;

import com.biolab.todolista.DTOs.Category.CatReq;
import com.biolab.todolista.DTOs.Category.CatRes;
import com.biolab.todolista.entities.Category;
import com.biolab.todolista.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//definindo service
@Service
public class CategoryService {
    //puxando repository para inversão de dependencias
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //metodo de criação de categorias que devolve catResponse e consome catRequest
    public CatRes saveCategory(CatReq catReq) {
        //instancia categoria para pegar os dados
        Category category = new Category();
        category.setDescription(catReq.getDesc());

        //salva no banco
        categoryRepository.save(category);

        //gera response para return
        CatRes catRes = new CatRes();
        catRes.setId(category.getId());
        catRes.setDesc(category.getDescription());

        return catRes;
    }

    //Função q retorna lista com todas as categorias
    public List<CatRes> getAllCategories() {
        List<Category> categories = categoryRepository.findAll(); //pega lista do tipo categoria
        List<CatRes> catRes = new ArrayList<>(); //faz uma lista de catResponse
        for (Category category : categories) { //enche a lista de catResponse com os dados da lista de category
            CatRes catRes1 = new CatRes();
            catRes1.setId(category.getId());
            catRes1.setDesc(category.getDescription());
            catRes.add(catRes1);
        }
        return catRes;
    }

    //Função q retorna o dado conforme o id
    public CatRes getCategoryById(long id) {
        //pega o dado do id
        Category c = categoryRepository.findById(id).orElseThrow();
        //transforma em catResponse para o return
        CatRes catRes = new CatRes();
        catRes.setId(c.getId());
        catRes.setDesc(c.getDescription());
        return catRes;
    }

    //Função q retorna o dado conforme o desc
    public CatRes getCategoryByDesc(String desc) {
        //pega o dado do desc
        Category c = categoryRepository.getCategoryByDescription(desc);
        //converte para catResponse para o return
        CatRes catRes = new CatRes();
        catRes.setId(c.getId());
        catRes.setDesc(c.getDescription());
        return catRes;
    }

    //metodo para alterar categoria, recebe id e CatRequest e retorna catResponse
    public CatRes updateCategory(long id,CatReq catReq) {
        //pega/verificar se aquele dado by id existe
        Category category = categoryRepository.findById(id).orElseThrow();

        //pega e altera o dado para atualizar no banco
        category.setDescription(catReq.getDesc());
        categoryRepository.save(category);//salva no banco
        //converte para response para return
        CatRes catRes = new CatRes();
        catRes.setId(category.getId());
        catRes.setDesc(category.getDescription());
        return catRes;
    }

    //deleta com id
    public void deleteCategoryById(long id) {
        //verifica se dado existe se sim deleta se não exception
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        }else {
            throw new IllegalArgumentException();
        }
    }
}
