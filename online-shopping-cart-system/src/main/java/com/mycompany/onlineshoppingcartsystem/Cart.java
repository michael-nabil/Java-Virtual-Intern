/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onlineshoppingcartsystem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MICHAEL NABIL
 */
public class Cart {
    
    private List<CartItem> items = new ArrayList<>();
    private double discount = 0.0; 

    
    public void addItem(Product product, int quantity)  {
        if (!product.isAvailableQuantity(quantity)) {
            System.out.println("Product " + product.getName() + " is out of stock.");
            return;
            //System.out.println("You can add only " + availableQuantity);// to be changed in product class (method :isAvailable)
        }
        for (CartItem item : items) {
            if (item.getProduct().getName().equals(product.getName())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
        product.setStockQuantity(product.getStockQuantity() - quantity);
    }

//    public void removeItem(Product product){
//        CartItem toRemove = null;
//        for (CartItem item : items) {
//            if (item.getProduct().getName().equals(product.getName())) {
//                toRemove = item;
//                break;
//            }
//        }
//        if (toRemove != null) {
//            items.remove(toRemove);
//        } else {
//            System.out.println("Product not found in cart.");
//        }
//    }

    public boolean removeItem(int productIndex){
        if(this.items.size()-1 < productIndex){
            return false;
        }
        this.items.remove(productIndex);
        return true;
    }
    
//     public void updateItemQuantity(Product product, int newQuantity) {
//        if (newQuantity <= 0) {
//            System.out.println("Quantity cannot be negative.");
//            return;
//        }
//        CartItem itemToUpdate = null;
//        for (CartItem item : items) {
//            if (item.getProduct().getName().equals(product.getName())) {
//                itemToUpdate = item;
//                break;
//            }
//        }
//        if (itemToUpdate != null) {
//            if (product.isAvailableQuantity(newQuantity - itemToUpdate.getQuantity())) {// we should decrese the quantity of the product by the difference between the new and old quantity of the cart item
//                itemToUpdate.setQuantity(newQuantity); // Update to new quantity
//                product.setStockQuantity(product.getStockQuantity()-newQuantity);
//            } else {
//                System.out.println("Not enough stock available for " + product.getName());
//            }
//        } else {
//            System.out.println("Product not found in cart.");
//        }
//    }
     
    public boolean updateItemQuantity(int itemIndex, int newQuantity){
        if(this.items.size()-1 < itemIndex){
            return false;   //invalid index choice
        }else{
            if (newQuantity <= 0) {
                System.out.println("Quantity cannot be negative.");
                return false;
            }
            CartItem itemForEdit = this.items.get(itemIndex);
            
            int additionalQuantity = newQuantity - itemForEdit.getQuantity();
            
            if (itemForEdit.getProduct().isAvailableQuantity(additionalQuantity)) {// we should decrese the quantity of the product by the difference between the new and old quantity of the cart item
                itemForEdit.setQuantity(newQuantity); // Update to new quantity
                itemForEdit.getProduct().setStockQuantity(itemForEdit.getProduct().getStockQuantity() - additionalQuantity);
                return true;
            } else {
                System.out.println("Not enough stock available for " + itemForEdit.getProduct().getName());
                return false;
            }
        }
    } 

    public void applyDiscount(double discountPercentage) throws IllegalArgumentException {
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100.");
        }
        this.discount = discountPercentage;
    }

    public double calculateTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total * ((100 - this.discount)/100);
    }

    public void displayCart() {
        
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
        System.out.println("Cart Summary:");
        int itemIndex = 1;
        for (CartItem item : items) {
            System.out.println(itemIndex+". "+item.getProduct().getName() + " x " + item.getQuantity() + " = $" + item.getTotalPrice());
            itemIndex++;
        }
        if(this.discount > 0.0){
            System.out.println("The discount applied on the whole cart " + this.discount +"%");
        }
        System.out.println("Total: $" + calculateTotal());
        }
    }
}
