package org.example.service;

import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.example.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, WarehouseRepository warehouseRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(String id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(String id) {
        productRepository.deleteById(id);
    }

    public List<Product> findByWarehouseId(String warehouseId) {
        return productRepository.findAll().stream()
                .filter(p -> p.getWarehouse() != null && p.getWarehouse().getId().equals(warehouseId))
                .toList();
    }

    public List<Product> findInPriceRange(double lowBound, double highBound) {
        return productRepository.findAll().stream()
                .filter(p -> p.getPrice() >= lowBound && p.getPrice() <= highBound)
                .toList();
    }

    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    public boolean productNameIsTaken(String name) {
        List<Product> products = findAll();
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}