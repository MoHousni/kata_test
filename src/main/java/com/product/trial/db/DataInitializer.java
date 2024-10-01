package com.product.trial.db;

import com.product.trial.entity.Product;
import com.product.trial.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() == 0) {
            Product product1 = new Product(null, "P001", "Product 1", "Description for Product 1", "image1.png",
                    "Category 1", BigDecimal.valueOf(100.00), 10, "IR001", 1, null, 5, System.currentTimeMillis(), System.currentTimeMillis());
            Product product2 = new Product(null, "P002", "Product 2", "Description for Product 2", "image2.png",
                    "Category 2", BigDecimal.valueOf(200.00), 5, "IR002", 2, null, 4, System.currentTimeMillis(), System.currentTimeMillis());

            productRepository.save(product1);
            productRepository.save(product2);
        }
    }
}
