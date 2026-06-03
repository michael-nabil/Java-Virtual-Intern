/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onlineshoppingcartsystem;

import java.util.Scanner;

/**
 *
 * @author MICHAEL NABIL
 */
public class InputValidation {
    
    public static class ValidateInput{
        
        public static Scanner input = new Scanner(System.in);
        
        public static int IntInput(String message){
            System.out.print(message);
            int result=-1;
            Scanner input = new Scanner(System.in);
            try{
                String s = input.nextLine();
                result = Integer.parseInt(s);

            }catch(NumberFormatException e){
                return result;
            }
            return result;
        }
        
        public static int IntInput(){
            int result=-1;
            Scanner input = new Scanner(System.in);
            try{
                String s = input.nextLine();
                result = Integer.parseInt(s);
            }catch(NumberFormatException e){
                return result;
            }

            return result;
        }
        public static double doubleInput(String message){
            System.out.print(message);
            double result=-1;
            Scanner input = new Scanner(System.in);
            try{
                String s = input.nextLine();
                result = Double.parseDouble(s);
            }catch(NumberFormatException e){
                return result;
            }

            return result;
        }
        public static String StringInput(){
            Scanner input = new Scanner(System.in);
            String s = input.nextLine();
            return s;
        }
        public static String StringInput(String message){
            System.out.print(message);
            Scanner input = new Scanner(System.in);
            String s = input.nextLine();
            return s;
        }
    }
}
