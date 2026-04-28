package com.Products.service;

import com.Products.model.Product;
import com.Products.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public List<Product> getByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("product not found with id " + id));

    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
    public Product updateProduct( Long id, Product updatedProduct) {
    Product existing = getById(id);

    existing.setName(updatedProduct.getName());
    existing.setBrand(updatedProduct.getBrand());
    existing.setCategory(updatedProduct.getCategory());
    existing.setPrice(updatedProduct.getPrice());
    return productRepository.save(existing);
    }
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

