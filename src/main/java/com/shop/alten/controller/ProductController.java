package com.shop.alten.controller;

import com.shop.alten.model.Product;
import com.shop.alten.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<Product> listProducts(){
        return productService.listProducts();
    }

    @GetMapping(path = "/{id}")
    public Product listProduct(@PathVariable("id") Long productId){
        return productService.findProductById(productId);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Product newProduct =  productService.addProduct(product);
        return  ResponseEntity.ok(newProduct);

    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long productId ,@RequestBody Map<String,Object> updates){
        return productService.updateProduct(productId,updates);
    }

}
