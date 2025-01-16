package org.example.controller;

import org.example.dto.ProductCreateOrUpdateDTO;
import org.example.dto.ProductReadCollectionDTO;
import org.example.dto.ProductReadDTO;
import org.example.model.Product;
import org.example.model.Warehouse;
import org.example.service.ProductService;
import org.example.service.WarehouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static org.example.utils.StringUtil.convertToSnakeCase;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private final WarehouseService warehouseService;

    public ProductController(ProductService productService, WarehouseService warehouseService) {
        this.productService = productService;
        this.warehouseService = warehouseService;
    }

    private Warehouse getWarehouseByName(String name) {
        if (name != null && warehouseService.findByName(name).isPresent()) {
            return warehouseService.findByName(name).get();
        }
        return null;
    }

    @PutMapping
    public ResponseEntity<ProductReadDTO> createProduct(@RequestBody ProductCreateOrUpdateDTO pdto) {
        if (productService.productNameIsTaken(pdto.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Product name is taken");
        }

        Product product = new Product(
                pdto.getName(),
                pdto.getPrice(),
                pdto.getQuantity(),
                pdto.getCategory()
        );

        Warehouse w = getWarehouseByName(pdto.getWarehouseName());

        if (w != null) {
            product.setWarehouse(w);
            Product savedProduct = productService.save(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ProductReadDTO(
                            savedProduct.getName(),
                            savedProduct.getPrice(),
                            savedProduct.getQuantity(),
                            savedProduct.getCategory(),
                            savedProduct.getWarehouse().getName()
                    )
            );
        }
        Product savedProduct = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ProductReadDTO(
                        savedProduct.getName(),
                        savedProduct.getPrice(),
                        savedProduct.getQuantity(),
                        savedProduct.getCategory()
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<ProductReadCollectionDTO>> getAllProducts() {
        List<ProductReadCollectionDTO> products = productService.findAll().stream()
                .map(p -> new ProductReadCollectionDTO(p.getId(), p.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{name}")
    public ResponseEntity<ProductReadDTO> getProductByName(@PathVariable String name) {
        Product product = productService.findByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        if (product.getWarehouse() != null) {
            return ResponseEntity.ok(new ProductReadDTO(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getQuantity(),
                    product.getCategory(),
                    product.getWarehouse().getName()
            ));
        }
        return ResponseEntity.ok(new ProductReadDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory()
        ));
    }

    @PatchMapping("/{name}")
    public ResponseEntity<ProductReadDTO> updateProduct(@PathVariable String name, @RequestBody ProductCreateOrUpdateDTO pdto) {
        Product product = productService.findByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        if (pdto.getName() != null) {
            String newName = convertToSnakeCase(pdto.getName());
            if (!newName.equals(name)) {
                if (productService.productNameIsTaken(newName)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Product name is taken");
                }
                else {
                    product.setName(newName);
                }
            }
        }
        double price = pdto.getPrice();
        if (price >= 0) {
            product.setPrice(price);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Product price must be grater or equal than 0");
        }
        int quantity = pdto.getQuantity();
        if (quantity >= 0) {
            product.setQuantity(quantity);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Product quantity must be grater or equal than 0");
        }
        String category = pdto.getCategory();
        if (category != null) {
            product.setCategory(category);
        }
        Warehouse w = getWarehouseByName(pdto.getWarehouseName());
        if (w != null) {
            product.setWarehouse(w);
        }
        Product updatedProduct = productService.save(product);
        return ResponseEntity.ok(new ProductReadDTO(
                updatedProduct.getName(),
                updatedProduct.getPrice(),
                updatedProduct.getQuantity(),
                updatedProduct.getCategory(),
                updatedProduct.getWarehouse().getName()
        ));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String name) {
        Product product = productService.findByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        productService.deleteById(product.getId());
        return ResponseEntity.noContent().build();
    }
}
