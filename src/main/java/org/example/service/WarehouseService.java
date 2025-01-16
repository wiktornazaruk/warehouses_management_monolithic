package org.example.service;

import org.example.model.Warehouse;
import org.example.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public Warehouse save(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public List<Warehouse> findAll() {
        return warehouseRepository.findAll();
    }

    public Optional<Warehouse> findById(String id) {
        return warehouseRepository.findById(id);
    }

    public void deleteById(String id) {
        warehouseRepository.deleteById(id);
    }

    public Optional<Warehouse> findByName(String name) {
        return warehouseRepository.findByName(name);
    }

    public boolean warehouseNameIsTaken(String name) {
        List<Warehouse> warehouses = findAll();
        for (Warehouse warehouse : warehouses) {
            if (warehouse.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}