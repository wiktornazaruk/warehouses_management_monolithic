package org.example.dto;

import org.example.model.Warehouse;

public class WarehouseDTO {
    String name;
    String location;
    WarehouseDTO () {}
    public WarehouseDTO(String name, String location) {
        this.name = name;
        this.location = location;
    }
    public String getName() {
        return name;
    }
    public String getLocation() {
        return location;
    }
}
