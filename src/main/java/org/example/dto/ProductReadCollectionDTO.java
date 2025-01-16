package org.example.dto;

public class ProductReadCollectionDTO {
    private String id;
    private String name;

    public ProductReadCollectionDTO() {}

    public ProductReadCollectionDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
}