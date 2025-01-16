package org.example.dto;

public class WarehouseReadDTO {
    private String id;
    private String name;
    private String location;

    public WarehouseReadDTO() {}

    public WarehouseReadDTO(String name,  String location) {
        this.name = name;
        this.location = location;
    }

    public WarehouseReadDTO(String id, String name,  String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    // Getters
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getLocation() {
        return location;
    }
}