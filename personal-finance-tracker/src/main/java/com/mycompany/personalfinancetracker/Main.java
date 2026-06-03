/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.personalfinancetracker;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.HashMap;
/**
 *
 * @author MICHAEL NABIL
 */
public class Main {
    public static int [] amount = new int[1000];
    public static String [] description = new String[1000];
    public static String [] category = new String[1000];
    public static int transactionCount = 0;
    
    public static void main(String[] args) {
        int choice = 0;
        while(choice != 5)
        {
            menu();
            choice = readInt(5,1);
            System.out.println();
            switch(choice){
                case 1:
                    inputTransaction();
                    break;
                case 2:
                    if(transactionCount==0)
                        System.out.println("No transactions recorded yet!");
                    else
                        viewTransaction();
                    break;
                case 3:
                    if(transactionCount==0)
                        System.out.println("No transactions recorded yet!");
                    else
                        viewSummary();
                    break;
                case 4:
                    if(transactionCount==0)
                        System.out.println("No transactions recorded yet!");
                    else
                        getInsights();
                    break;
                case 5:
                    System.out.println("\nExiting the program. Goodbye!");
                    break;
            }
        }




        //for(int i=0;i<5;i++)
            //inputTransaction();
        //viewTransaction();
        //viewSummary();
        //getInsights(); 
        
        //System.out.printf("%-20dtest\n",3);
        //System.out.printf("%-20stest","fasolyaaaaaa");
    }
    public static int readInt(int upperBound, int lowerBound){
        Scanner input = new Scanner(System.in);
        int intValue = -1;
        boolean correctInputFlag = false;
        while(!correctInputFlag){
            try{
                intValue = input.nextInt();
                if(intValue<lowerBound || intValue>upperBound){
                    correctInputFlag = false;
                    System.out.print("Invalid input! Enter a valid choice: ");
                }
                else
                    correctInputFlag = true;    //this line is executed only if the previous statement doesn't cause exceptions
            }
            catch(InputMismatchException ex){
                System.out.print("Invalid input! Enter a numeric value: ");
            }
            //clear the buffer after each input.nextInt() regrading occurance of an exception or not
            input.nextLine();   //to remove(read) any additional string input in the same line of the int
        }
        return intValue;
    }
    public static void menu(){
        System.out.print("1. Input Transaction\n2. View Transactions\n3. View Summary\n4. Get Insights\n5. Exit\nEnter your choice: ");
    }
    public static void inputTransaction(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a brif description of the transaction: ");
        description[transactionCount] = input.nextLine();
        
        System.out.print("Input the monetary value of the transaction: ");
        amount[transactionCount] = readInt(Integer.MAX_VALUE,Integer.MIN_VALUE);
//        boolean correctInputFlag = false;
//        while(!correctInputFlag){
//            try{
//                amount[transactionCount] = input.nextInt();
//                correctInputFlag = true;    //this line is executed only if the previous statement doesn't cause exceptions
//            }
//            catch(InputMismatchException ex){
//                System.out.print("Invalid input! Enter a numeric value: ");
//            }
//            //clear the buffer after each input.nextInt() regrading occurance of an exception or not
//            input.nextLine();   //to remove(read) any additional string input in the same line of the int
//        }
        System.out.print("Specify a category for the transaction: ");
        category[transactionCount] = input.nextLine().split(" ")[0];    //split the input on the spaces, and take the first input only, in case the input is more than one word(token)
        System.out.println();
        transactionCount++;
        
        //System.out.println(description[transactionCount]+"\n"+amount[transactionCount]+"\n"+category[transactionCount]);
        
    }
    public static void  viewTransaction()
    {
        System.out.print("1. Sort the transactions\n2. View transactions with their entry order\nyour choice: ");
        int choice = readInt(2,1);
//        boolean correctInputFlag = false;
//        while(!correctInputFlag){
//            try{
//                choice = input.nextInt();
//                if(choice<1 || choice>2){
//                    correctInputFlag = false;
//                    System.out.print("Invalid input! Enter a valid choice: ");
//                }
//                else
//                    correctInputFlag = true;    //this line is executed only if the previous statement doesn't cause exceptions
//            }
//            catch(InputMismatchException ex){
//                System.out.print("Invalid input! Enter a numeric value: ");
//            }
//            //clear the buffer after each input.nextInt() regrading occurance of an exception or not
//            input.nextLine();   //to remove(read) any additional string input in the same line of the int
//        }
        
        int [] amountIndexes = new int[transactionCount];
            for(int i=0;i<amountIndexes.length;i++)
                amountIndexes[i] = i;
        System.out.println();
        if(choice == 1)
        {
            //ask for ascending or descending
            System.out.print("1. Ascending order\n2. Descending order\nYour choice: ");
            
            choice = readInt(2,1);
            System.out.println();
//            correctInputFlag = false;
//            while(!correctInputFlag){
//                try{
//                    choice = input.nextInt();
//                    if(choice<1 || choice>2){
//                        correctInputFlag = false;
//                        System.out.print("Invalid input! Enter a valid choice: ");
//                    }
//                    else
//                        correctInputFlag = true;    //this line is executed only if the previous statement doesn't cause exceptions
//                }
//                catch(InputMismatchException ex){
//                    System.out.print("Invalid input! Enter a numeric value: ");
//                }
//                //clear the buffer after each input.nextInt() regrading occurance of an exception or not
//                input.nextLine();   //to remove(read) any additional string input in the same line of the int
//            }
            
            
            int [] sortedAmount = amount.clone();
            
            //HashMap <String, Integer> amountIndexes = new HashMap<>();
            //for(int i=0;i<transactionCount;i++)
            //    amountIndexes.put(amount[i]+"", i);
            if(choice == 1){
                for(int i=0;i<transactionCount;i++){
                    for(int j=0 ;j<transactionCount-i-1;j++)
                    {
                        if(Math.abs(sortedAmount[j]) > Math.abs(sortedAmount[j+1])){
                            sortedAmount[j] = sortedAmount[j] + sortedAmount[j+1];
                            sortedAmount[j+1] = sortedAmount[j] - sortedAmount[j+1];
                            sortedAmount[j] = sortedAmount[j] - sortedAmount[j+1];

                            amountIndexes[j] = amountIndexes[j] + amountIndexes[j+1];
                            amountIndexes[j+1] = amountIndexes[j] - amountIndexes[j+1];
                            amountIndexes[j] = amountIndexes[j] - amountIndexes[j+1];
                        }
                    }
                }
            }
            else if(choice == 2)
            {
                for(int i=0;i<transactionCount;i++){
                    for(int j=0 ;j<transactionCount-i-1;j++)
                    {
                        if(Math.abs(sortedAmount[j]) < Math.abs(sortedAmount[j+1])){
                            sortedAmount[j] = sortedAmount[j] + sortedAmount[j+1];
                            sortedAmount[j+1] = sortedAmount[j] - sortedAmount[j+1];
                            sortedAmount[j] = sortedAmount[j] - sortedAmount[j+1];

                            amountIndexes[j] = amountIndexes[j] + amountIndexes[j+1];
                            amountIndexes[j+1] = amountIndexes[j] - amountIndexes[j+1];
                            amountIndexes[j] = amountIndexes[j] - amountIndexes[j+1];
                        }
                    }
                }
            }
        }
        
        System.out.println("----------------------------------------");
        System.out.printf("%-20s %-10s %-10s\n","Description","Amount","Category");
        System.out.println("----------------------------------------");
        for(int i=0;i<transactionCount;i++)
        {
            System.out.printf("%-20s %-10d %-10s\n",description[amountIndexes[i]],amount[amountIndexes[i]],category[amountIndexes[i]]);
        }
        System.out.println();
    }
    public static void viewSummary(){
        int totalIncome = 0;
        int totalExpenses = 0;
        for(int value : amount)
        {
            if(value > 0)
                totalIncome += value;
            else
                totalExpenses += value;
        }
        
        System.out.println("The total Income = "+totalIncome+"\nThe total Expenses = "+totalExpenses+"\nBallance: "+(int)(totalIncome+totalExpenses));
    }
    public static void getInsights(){
        HashMap <String,Integer> expensesByCategory = new HashMap<>();
        int totalExpenses = 0;
        for(int i=0;i<transactionCount;i++){
            if(amount[i]<0){
                if(expensesByCategory.containsKey(category[i])){
                    expensesByCategory.replace(category[i], expensesByCategory.get(category[i])+amount[i]);
                }
                else{
                    expensesByCategory.put(category[i], amount[i]);
                }
                totalExpenses += amount[i];
            }
        }
        System.out.println("Total expenses: "+Math.abs(totalExpenses));
        for(String category : expensesByCategory.keySet()){
            System.out.println("Category: " + category + " - spent: " + Math.abs(expensesByCategory.get(category)) + " ("+Math.abs((expensesByCategory.get(category))/(float)totalExpenses)*100+"%) of total");
        }
        System.out.println();
    }
}
