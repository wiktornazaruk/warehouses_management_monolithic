package org.example.dto;

public class ProductCreateOrUpdateDTO {
    private String name;
    private double price;
    private int quantity;
    private String category;
    private String warehouseName;
    public ProductCreateOrUpdateDTO() {}
    public ProductCreateOrUpdateDTO(String name, double price, int quantity, String category, String warehouseName) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.warehouseName = warehouseName;
    }
    public ProductCreateOrUpdateDTO(String name, double price, int quantity, String category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) { this.price = price; }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) { this.category = category; }
    public String getWarehouseName() { return warehouseName; }
    public void setWarehouseName(String warehouseName) { this.warehouseName = warehouseName; }
}
