package com.example.ecommerce.service;

import com.example.ecommerce.DTO.CategoryDTO;
import com.example.ecommerce.mapper.MapToDto;
import com.example.ecommerce.mapper.MapToEntity;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(MapToDto::mapToCategoryDTO).collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(Long id) {
        return MapToDto.mapToCategoryDTO(Objects.requireNonNull(categoryRepository.findById(id).orElse(null)));
    }

    public CategoryDTO createCategory(CategoryDTO category) {
        categoryRepository.save(MapToEntity.mapToCategory(category));
        return category;
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO updatedCategory) {
        CategoryDTO category = MapToDto.mapToCategoryDTO(Objects.requireNonNull(categoryRepository.findById(id).orElse(null)));
        if (category != null) {
            category.setName(updatedCategory.getName());
            categoryRepository.save(MapToEntity.mapToCategory(category));
            return category;
        }
        return null;
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
