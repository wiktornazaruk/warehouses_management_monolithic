package org.example.dto;

public class ProductDTO {
    String name;
    double price;
    int quantity;
    String category;
    public ProductDTO() {}
    public ProductDTO(String name, double price, int quantity, String category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    public String getCategory() {
        return category;
    }
}
