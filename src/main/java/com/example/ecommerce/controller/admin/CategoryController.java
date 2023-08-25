package com.example.ecommerce.controller.admin;

import com.example.ecommerce.DTO.request.CategoryRequestDTO;
import com.example.ecommerce.DTO.response.CategoryResponseDTO;
import com.example.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories(){
        List<CategoryResponseDTO> categoryResponseDTOList = categoryService.getAllCategories();
        if (categoryResponseDTOList != null) {
            return new ResponseEntity<>(categoryResponseDTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategory(@PathVariable Long id){
        CategoryResponseDTO categoryResponseDTO = categoryService.getCategoryById(id);
        if (categoryResponseDTO != null) {
            return new ResponseEntity<>(categoryResponseDTO,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO){
        CategoryResponseDTO createdCategory = categoryService.createCategory(categoryRequestDTO);
        if (createdCategory != null) {
            return new ResponseEntity<>(createdCategory,HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDTO categoryRequestDTO){
        CategoryResponseDTO updateCategory = categoryService.updateCategory(id,categoryRequestDTO);
        if (updateCategory != null) {
            return new ResponseEntity<>(updateCategory, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

