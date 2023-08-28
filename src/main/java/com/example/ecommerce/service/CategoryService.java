package com.example.ecommerce.service;

import com.example.ecommerce.DTO.mapper.Mapper;
import com.example.ecommerce.DTO.request.CategoryRequestDTO;
import com.example.ecommerce.DTO.response.CategoryResponseDTO;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public Category getIdCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() ->
                new IllegalArgumentException("could not find category with id: " + categoryId));
    }
    public CategoryResponseDTO createCategory (CategoryRequestDTO categoryRequestDTO){
        Category category = new Category();
        category.setName(categoryRequestDTO.getName());
        category.setContent(categoryRequestDTO.getContent());
        categoryRepository.save(category);
        return Mapper.categoryTocategoryResponseDTO(category);
    }
    public CategoryResponseDTO getCategoryById (Long id){
        return Mapper.categoryTocategoryResponseDTO(this.getIdCategory(id));
    }
    public List<CategoryResponseDTO> getAllCategories(){
        List<Category> categories = categoryRepository.findAll().stream().toList();
        List<CategoryResponseDTO> categoryResponseDTOS = new ArrayList<>();
        for (Category category : categories){
            categoryResponseDTOS.add(Mapper.categoryTocategoryResponseDTO(category));
        }
        return categoryResponseDTOS;
    }
    public CategoryResponseDTO updateCategory (Long id, CategoryRequestDTO categoryRequestDTO){
        Category category = this.getIdCategory(id);
        category.setName(categoryRequestDTO.getName());
        category.setContent(categoryRequestDTO.getContent());
        return Mapper.categoryTocategoryResponseDTO(category);
    }
    public void deleteCategory (Long id){
        categoryRepository.deleteById(this.getIdCategory(id).getId());
    }

}
