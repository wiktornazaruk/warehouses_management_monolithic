package org.example.controller;

import org.example.dto.*;
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
@RequestMapping("/api/warehouses")
public class WarehouseController {
    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService, ProductService productService) {
        this.warehouseService = warehouseService;
    }

    @PutMapping
    public ResponseEntity<WarehouseReadDTO> createWarehouse(@RequestBody WarehouseCreateOrUpdateDTO wdto) {

        Warehouse w = new Warehouse(
                wdto.getName(),
                wdto.getLocation()
        );
        Warehouse uw = warehouseService.save(w);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new WarehouseReadDTO(
                        uw.getId(),
                        uw.getName(),
                        uw.getLocation()
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<WarehouseReadCollectionDTO>> getAllWarehouses() {
        List<WarehouseReadCollectionDTO> warehouses = warehouseService.findAll().stream()
                .map(w -> new WarehouseReadCollectionDTO(w.getName(), w.getLocation()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(warehouses);
    }

    @GetMapping("/{name}")
    public ResponseEntity<WarehouseReadDTO> getWarehouseByName(@PathVariable String name) {
        Warehouse w = warehouseService.findByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Warehouse not found"));
        return ResponseEntity.ok(new WarehouseReadDTO(
                w.getId(),
                w.getName(),
                w.getLocation()
        ));
    }

    @GetMapping("/{name}/products")
    public ResponseEntity<List<ProductReadCollectionDTO>> getProductsByName(@PathVariable String name) {
        Warehouse w = warehouseService.findByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Warehouse not found"));
        List<ProductReadCollectionDTO> products = w.getProducts().stream()
                .map(p -> new ProductReadCollectionDTO(p.getId(), p.getName()))
                .toList();
        return ResponseEntity.ok(products);
    }

    @PatchMapping("/{name}")
    public ResponseEntity<WarehouseReadDTO> UpdateWarehouse(@PathVariable String name, @RequestBody WarehouseCreateOrUpdateDTO wdto) {
        Warehouse w = warehouseService.findByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Warehouse not found"));
        String newId = wdto.getId();
        if (newId != null) {
            w.setId(newId);
        }
        if (wdto.getName() != null) {
            String newName = convertToSnakeCase(wdto.getName());
            if (!newName.equals(name)){
                if (warehouseService.warehouseNameIsTaken(newName)){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Warehouse name is taken");
                }
                else {
                    w.setName(newName);
                }
            }
        }
        String newLocation = wdto.getLocation();
        if (newLocation != null) {
            w.setLocation(newLocation);
        }
        Warehouse uw = warehouseService.save(w);
        return ResponseEntity.ok(new WarehouseReadDTO(
                uw.getId(),
                uw.getName(),
                uw.getLocation()
        ));

    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable String name) {
        Warehouse w = warehouseService.findByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Warehouse not found"));
        warehouseService.deleteById(w.getId());
        return ResponseEntity.noContent().build();
    }

}