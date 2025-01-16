package org.example.dto;

public class ProductReadDTO {
    private String id;
    private String name;
    private double price;
    private int quantity;
    private String category;
    private String warehouseName;

    public ProductReadDTO() {}

    public ProductReadDTO(String id, String name, double price, int quantity, String category, String warehouseName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.warehouseName = warehouseName;
    }

    public ProductReadDTO(String id, String name, double price, int quantity, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public ProductReadDTO(String name, double price, int quantity, String category, String warehouseName) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.warehouseName = warehouseName;
    }

    public ProductReadDTO(String name, double price, int quantity, String category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public String getCategory() { return category; }
    public String getWarehouseName() { return warehouseName; }

}
