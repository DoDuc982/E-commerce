package com.example.ecommerce.service;

import com.example.ecommerce.DTO.mapper.Mapper;
import com.example.ecommerce.DTO.request.ProductRequestDTO;
import com.example.ecommerce.DTO.response.ProductResponseDTO;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }
    public Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(() ->
                new IllegalArgumentException("cannot find product with id: " + productId));
    }
    public List<ProductResponseDTO> getAllProducts(){
        List<Product> products = productRepository.findAll().stream().toList();
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for (Product product : products){
            productResponseDTOS.add(Mapper.productToProductResponseDTO(product));
        }
        return productResponseDTOS;
    }

    public ProductResponseDTO getProductById(Long id){
        return Mapper.productToProductResponseDTO(this.getProduct(id));
    }

    @Transactional
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO){
        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setContent(productRequestDTO.getContent());
        product.setDiscountPrice(productRequestDTO.getDiscountPrice());
        product.setImageUrl(productRequestDTO.getImageUrl());
        product.setQuantity(productRequestDTO.getQuantity());
        product.setPrice(productRequestDTO.getPrice());
        product.setSoldQuantity(productRequestDTO.getSoldQuantity());
        if (productRequestDTO.getCategoryId() == null) {
            throw new IllegalArgumentException("Product at least on category");
        }
        Category category = categoryService.getIdCategory(productRequestDTO.getCategoryId());
        product.setCategory(category);
        productRepository.save(product);
        return Mapper.productToProductResponseDTO(product);
    }
    @Transactional
    public ProductResponseDTO updateProduct(Long productId, ProductRequestDTO productRequestDTO){
        Product edittedProduct = this.getProduct(productId);
        edittedProduct.setName(productRequestDTO.getName());
        edittedProduct.setContent(productRequestDTO.getContent());
        edittedProduct.setDiscountPrice(productRequestDTO.getDiscountPrice());
        edittedProduct.setImageUrl(productRequestDTO.getImageUrl());
        edittedProduct.setQuantity(productRequestDTO.getQuantity());
        edittedProduct.setPrice(productRequestDTO.getPrice());
        edittedProduct.setSoldQuantity(productRequestDTO.getSoldQuantity());
        edittedProduct.setCategory(categoryService.getIdCategory(productRequestDTO.getCategoryId()));
        return Mapper.productToProductResponseDTO(edittedProduct);
    }
    public void deleteProduct(Long id){
        productRepository.deleteById(this.getProduct(id).getId());
    }
}

