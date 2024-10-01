package com.product.trial.service;

import com.product.trial.entity.Product;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface ProductService {
    List<Product> getAllProducts();

    Optional<Product> getProduct(Long id);

    Product createNewProduct(Product product) throws IOException;

    Product updateExistedProduct(Long id, Map<String, Object> updates) throws IOException;

    void deleteProduct(Long id) throws IOException;
}
