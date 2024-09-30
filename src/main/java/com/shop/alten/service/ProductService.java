package com.shop.alten.service;

import com.shop.alten.model.Product;
import com.shop.alten.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }


    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    public Product findProductById(Long productId) {
        return productRepository.findProductById(productId).orElseThrow(
                () -> new IllegalStateException("Product with "+ productId +" does not exist")
        );
    }


    public Product addProduct(Product product) {
        if(productRepository.findProductById(product.getId()).isPresent()){
           throw new IllegalStateException("Product Id already exists");
        };
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return  productRepository.save(product);
    }

    @Transactional
    public ResponseEntity<Product> updateProduct(Long productId, Map<String,Object> updates){
        Product product = productRepository.findProductById(productId).orElseThrow(
                () -> new IllegalStateException("product id doesn´t not")
        );

        updates.forEach(
                (key,value) -> {
                    Field field = ReflectionUtils.findField(Product.class,key);
                    if(field != null){
                        field.setAccessible(true);
                        ReflectionUtils.setField(field,product,value);
                    }
                }
        );
        Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    public void deleteProduct(Long productId){
        boolean productExists = productRepository.existsById(productId);
        if(!productExists){
            throw new IllegalStateException(
                "Product with id " + productId + "does not exist"
            );
        }
        productRepository.deleteById(productId);
    }
}
