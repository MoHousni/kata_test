package com.product.trial.service.impl;

import com.product.trial.entity.Product;
import com.product.trial.enumclass.InventoryStatus;
import com.product.trial.repository.ProductRepository;
import com.product.trial.service.ProductService;
import com.product.trial.validation.ProductValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        if (id != null) {
            return productRepository.findById(id);
        } else return Optional.empty();
    }

    @Override
    public Product createNewProduct(Product product) throws IOException {
        ProductValidation.validateProduct(product);
        if(getProduct(product.getId()).isPresent()) {
            throw new IllegalArgumentException("Product already exists");
        } else {
            return productRepository.save(product);
        }
    }

    @Override
    public Product updateExistedProduct(Long id, Map<String, Object> updates) throws IOException {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product does not exist"));
        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    existingProduct.setName((String) value);
                    break;
                case "description":
                    existingProduct.setDescription((String) value);
                    break;
                case "price":
                    existingProduct.setPrice(BigDecimal.valueOf(((Number) value).doubleValue()));
                    break;
                case "image":
                    existingProduct.setImage((String) value);
                    break;
                case "quantity":
                    existingProduct.setQuantity((Integer) value);
                    break;
                case "inventoryStatus":
                    existingProduct.setInventoryStatus((InventoryStatus) value);
            }
        });


        return productRepository.save(existingProduct);

    }

    @Override
    public void deleteProduct(Long id) throws IOException {
        Product deletedProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product does not exist"));
        productRepository.delete(deletedProduct);
    }

}
