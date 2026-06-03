/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onlineshoppingcartsystem;

/**
 *
 * @author MICHAEL NABIL
 */
public class MockPaymentGateway {
//    private String [] creditCardOptions = {"master","visa","discover"};
    public boolean processPayment(String creditCardNumber,String paymentMethod){
        
        if(paymentMethod.equals("master")){//2
            if(Integer.parseInt(creditCardNumber.charAt(0)+"") == 2)
                return true;
            else
                return false;
        }
        else if(paymentMethod.equals("visa")){//4
            if(Integer.parseInt(creditCardNumber.charAt(0)+"") == 4)
                return true;
            else
                return false;
        }
        else if(paymentMethod.equals("discover")){//6
            if(Integer.parseInt(creditCardNumber.charAt(0)+"") == 6)
                return true;
            else
                return false;
        }
        return false;
        
    }
    
}
