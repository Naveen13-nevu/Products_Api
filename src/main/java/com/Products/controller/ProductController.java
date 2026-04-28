package com.Products.controller;

import com.Products.model.Product;
import com.Products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

@Autowired
private ProductService productService;

@GetMapping
public List<Product> getAllProduct(){
    return  productService.getAllProduct();
}

@GetMapping("/category/{category}")
public List<Product> getByCategory(@PathVariable String category) {
    return productService.getByCategory(category);
}
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id){
        return productService.getById(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product){
        return productService.updateProduct(id,product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return "product deleted successfully";
    }

}


