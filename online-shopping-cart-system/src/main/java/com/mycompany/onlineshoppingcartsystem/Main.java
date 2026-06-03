/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.onlineshoppingcartsystem;

import java.util.Scanner;
import com.mycompany.onlineshoppingcartsystem.InputValidation.ValidateInput;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
//        String card = "1234567890123456";
//        System.out.println(card.matches("\\d{16}"));


//option to add a product to cart now showing after browsing all the products
//after adding a product to the caet after searching, the cart is still empty
        System.out.println("\n*** Online Shopping Cart System ***");
        System.out.println("Add User:");
        String userName = ValidateInput.StringInput("Enter user name: ");
        String address = ValidateInput.StringInput("Enter user address: ");
        String phoneNumber = ValidateInput.StringInput("Enter user phone number: ");
        
        User user = new User();
        user.setName(userName);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
        user.setCart(new Cart());
        
        ProductCatalog productCatalog = new ProductCatalog();
        Product p1 = new Product("bag","large backpack with 6 pockets",650,10,"bags");
        Product p2 = new Product("laptop","hp laptop with 12 gen intel cpu",20000,5,"electronics");
        Product p3 = new Product("t-shirt","polo t-shirt",300,20,"clothing");
        Product p4 = new Product("samsung s23","new samsung phone 2023 phone",15000,7,"electronics");
        
        productCatalog.addProduct(p1);
        productCatalog.addProduct(p2);
        productCatalog.addProduct(p3);
        productCatalog.addProduct(p4);
        
        
        while (true) {
            // Display the menu
            System.out.println("1. Add Product to Cart");
            System.out.println("2. View Cart");
            System.out.println("3. Remove Product from Cart");
            System.out.println("4. update quantities");
            System.out.println("5. Apply discounts");
            System.out.println("6. Checkout");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = ValidateInput.IntInput();
            switch (choice) {
                case 1:
                    System.out.println("1. View Products");
                    System.out.println("2. Search for Products");
                    choice = ValidateInput.IntInput("Enter your choice: ");
                    switch(choice){
                        case 1:
                            productCatalog.browseProducts(productCatalog.getProducts());
                            List<Product> availableProducts = productCatalog.getProducts();
                            int chosenProductIndex = ValidateInput.IntInput("Enter your chosen product number or -1 to exit: ");
                            if(chosenProductIndex == -1)
                                continue;
                            else if(chosenProductIndex > availableProducts.size()){
                                System.out.println("Invalid choice");
                                continue;
                            }
                            int productQuantity = ValidateInput.IntInput("Enter the chosen quantity: ");
                            Product chosenProduct = availableProducts.get(chosenProductIndex - 1);
                            user.getCart().addItem(chosenProduct, productQuantity);
                            break;
                        case 2:
                            System.out.println("1. search with prices range");
                            System.out.println("2. search with category name");
                            choice = ValidateInput.IntInput("Enter your choice: ");
                            List<Product> filteredList = new ArrayList<Product>();
                            switch(choice){
                                case 1:
                                    int minPriceRange = ValidateInput.IntInput("Enter the minimum price range:");
                                    int maxPriceRange = ValidateInput.IntInput("Enter the maximum price range:");
                                    
                                    filteredList = productCatalog.filterProductsByPrice(minPriceRange,maxPriceRange);
                                    productCatalog.browseProducts(filteredList);
                                    
                                    break;
                                case 2:
                                    String category = ValidateInput.StringInput("Enter the category to search for: ");
                                    filteredList = productCatalog.filterProductsByCategory(category);
                                    productCatalog.browseProducts(filteredList);
                                    break;
                            }
                            if(! filteredList.isEmpty()){
                                chosenProductIndex = ValidateInput.IntInput("Enter your chosen product number or -1 to exit: ");
                                if(chosenProductIndex == -1)
                                continue;
                                else if(chosenProductIndex > filteredList.size()){
                                System.out.println("Invalid choice");
                                continue;
                                }
                                productQuantity = ValidateInput.IntInput("Enter the chosen quantity: ");
                                Product filteredProduct = filteredList.get(chosenProductIndex - 1);
                                user.getCart().addItem(filteredProduct, productQuantity);
                            }else{
                                System.out.println("No available products");
                            }
                            break;
                    }
                    break;
                case 2:
                    user.getCart().displayCart();
                    break;
                case 3:
                    user.getCart().displayCart();
                    int productIndex = ValidateInput.IntInput("Enter product index to remove: ");
                    user.getCart().removeItem(productIndex - 1);
                    break;
                case 4:
                    user.getCart().displayCart();
                    productIndex = ValidateInput.IntInput("Enter product index to update quantity: ");
                    int quantity = ValidateInput.IntInput("Enter the new quantity: ");
                    if(user.getCart().updateItemQuantity(productIndex - 1,quantity))
                        System.out.println("quantity updated successfully");
                    else
                        System.out.println("quantity update Failed");
                    break;
                case 5:
                    double discountValue = ValidateInput.doubleInput("Enter the value of the discount to apply on the whole cart: ");
                    user.getCart().applyDiscount(discountValue);
                    break;
                case 6:
                    Order order = new Order();
                    order.setCart(user.getCart());
                    System.out.println("Choose shipping details: \n1. Express shipping (add 50$ extra fees)\n2. Standard shipping");
                    int shippingChoice = ValidateInput.IntInput("Enter your choice");
                    if(shippingChoice == 1)
                        order.setIsExpressShipping(true);
                    else
                        order.setIsExpressShipping(false);
                    
                    order.orderSummary(user);
                    System.out.println("Choose your card type: \n1. Master card\n2. Visa Card\n3. Discover");
                    int creditCardChoice = ValidateInput.IntInput("Enter your choice: ");
                    String creditCardNumber = ValidateInput.StringInput("Enter your credit card number (16- digits)");
                    String creditCardType = null;
                    switch(creditCardChoice){
                        case 1 -> creditCardType = "master";
                        case 2 -> creditCardType = "visa";
                        case 3 -> creditCardType = "discover";
                        default -> System.out.println("Invalid Choice");
                    }
                    if(order.checkOut(creditCardNumber,creditCardType)){
                        order.orderConfirmation();
                    }
                    else
                        System.out.println("Invalid credit Card! Order failed");
                    break;
                case 7:
                    System.out.println("Good by! thanks for using our system.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
