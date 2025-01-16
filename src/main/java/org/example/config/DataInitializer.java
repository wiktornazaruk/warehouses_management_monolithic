package org.example.config;

import org.example.model.Product;
import org.example.model.Warehouse;
import org.example.repository.ProductRepository;
import org.example.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class DataInitializer implements CommandLineRunner {
    private final WarehouseRepository warehouseRepository;
    private final ProductRepository productRepository;

    @Autowired
    public DataInitializer(WarehouseRepository warehouseRepository, ProductRepository productRepository) {
        this.warehouseRepository = warehouseRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        // Create Warehouses

        Warehouse warehouse1 = new Warehouse("w1", "ny");
        Warehouse warehouse2 = new Warehouse("w2", "bos");

        warehouseRepository.save(warehouse1);
        warehouseRepository.save(warehouse2);

        // Create Products
        Product product1 = new Product("laptop_msi_ps42", 2200.00, 50, "electronics");
        Product product2 = new Product("iphone11_pro", 1400.00, 100, "electronics");
        Product product3 = new Product("ipad_air", 500.00, 75, "electronics");

        // Bidirectional relationship
        product1.setWarehouse(warehouse1);
        product2.setWarehouse(warehouse2);
        product3.setWarehouse(warehouse1);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
    }
}