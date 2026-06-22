package com.Products.service;

import com.Products.model.Product;
import com.Products.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Cacheable("allProducts")
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Cacheable(value = "categoryProducts", key = "#category")
    public List<Product> getByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Cacheable(value = "products", key = "#id")
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("product not found with id " + id));

    }

    @CacheEvict(value = {"allProducts", "categoryProducts"}, allEntries = true)
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
    @CachePut(value = "products", key = "#id")
    @CacheEvict(value = {"allProducts", "categoryProducts"}, allEntries = true)
    public Product updateProduct( Long id, Product updatedProduct) {
    Product existing = getById(id);

    existing.setName(updatedProduct.getName());
    existing.setBrand(updatedProduct.getBrand());
    existing.setCategory(updatedProduct.getCategory());
    existing.setPrice(updatedProduct.getPrice());
    return productRepository.save(existing);
    }
    @CacheEvict(value = {"products", "allProducts", "categoryProducts"}, key = "#id", allEntries = true)
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

