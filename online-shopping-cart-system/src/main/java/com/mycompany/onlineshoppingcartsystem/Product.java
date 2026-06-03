/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onlineshoppingcartsystem;

/**
 *
 * @author MICHAEL NABIL
 */
public class Product {
    private String name;
    private String description;
    private String category;
    private double price;
    private int stockQuantity;

    public String getCategory(){
        return this.category;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public Product(String name, String description, double price, int stockQuantity,String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public boolean isInStock() {
        return stockQuantity > 0;
    }
    public boolean isAvailableQuantity(int quantity){
        return (this.stockQuantity >= quantity);
    }
}