package org.example.dto;

public class WarehouseReadCollectionDTO {
    private String id;
    private String name;

    public WarehouseReadCollectionDTO() {}

    public WarehouseReadCollectionDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}