/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onlineshoppingcartsystem;

import java.time.LocalDate;
import java.util.Random;

/**
 *
 * @author MICHAEL NABIL
 */
public class Order {
    private static int oredersCount = 0;
    private Cart cart;
    private boolean isExpressShipping = false;
    final private int expressShippingCost = 50;
    
    public Order(){
        Order.oredersCount++;
    }    
    public void setCart(Cart cart){
        this.cart = cart;
    }
    public Cart getCart(){
        return cart;
    }
    public void setIsExpressShipping(boolean isExpress){
        this.isExpressShipping = isExpress;
    }
    public boolean getIsExpressShipping(){
        return this.isExpressShipping;
    }
    
    public void orderSummary(User user){
        double totalCost = cart.calculateTotal();
        if(this.isExpressShipping){
            totalCost += this.expressShippingCost;
        }
        System.out.println("Customer Name: "+user.getName());
        System.out.println("Phone number: "+user.getPhoneNumber());
        System.out.println("Address: "+user.getAddress());
        
        this.cart.displayCart();
        System.out.println("Total Cost: "+totalCost);
        System.out.print("Shipping Details: ");
        System.out.println(this.isExpressShipping == true ? "Express" : "Standerd" + "shipping");
        
    }
    public boolean checkOut(String cardNumber, String paymentMethod){
        System.out.println("choose payment method, enter credit card number");
        MockPaymentGateway paymentGateway = new MockPaymentGateway();
        return paymentGateway.processPayment(cardNumber, paymentMethod);
    }
    public void orderConfirmation(){
        System.out.println("The purchase is completed successfully");
        Random randGenerator = new Random();
        LocalDate currentDate = LocalDate.now();
        LocalDate deliveryDate = null;
        if(this.isExpressShipping){
            deliveryDate = currentDate.plusDays(1);
        }
        else{
            int randomWaitingDays = randGenerator.nextInt(3) + 2;
            deliveryDate = currentDate.plusDays(randomWaitingDays);
        }
        System.out.println("Estimated delivery date: "+ deliveryDate.toString());
    }
}
