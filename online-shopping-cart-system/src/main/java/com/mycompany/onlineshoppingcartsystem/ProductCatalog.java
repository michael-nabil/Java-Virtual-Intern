/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onlineshoppingcartsystem;

/**
 *
 * @author MICHAEL NABIL
 */
import java.util.ArrayList;
import java.util.List;


public class ProductCatalog {
    private List<Product> products;
    
    public List<Product> getProducts(){
        return this.products;
    }
    public void getProducts(List<Product> products){
        this.products = products;
    }
    
    public ProductCatalog() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public Product searchProduct(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    public List<Product> filterProductsByPrice(double minPrice, double maxPrice) {
        List<Product> filtered = new ArrayList<>();
        for (Product product : products) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                filtered.add(product);
            }
        }
        return filtered;
    }
    
    public List<Product> filterProductsByCategory(String categoryName){
        List<Product> filtered = new ArrayList<>();
        for (Product product : products){
            if (product.getCategory().equals(categoryName)){
                filtered.add(product);
            }
        }
        return filtered;
    }

    public void browseProducts(List<Product> productsList) {
        int productIndex = 1;
        for (Product product : productsList) {
            System.out.print("Product "+productIndex+" :");
            System.out.println("Name: " + product.getName());
            System.out.println("Description: " + product.getDescription());
            System.out.println("Price: $" + product.getPrice());
            System.out.println("Stock Quantity: " + product.getStockQuantity());
            System.out.println("----------------------------");
            productIndex++;
        }
    }
}