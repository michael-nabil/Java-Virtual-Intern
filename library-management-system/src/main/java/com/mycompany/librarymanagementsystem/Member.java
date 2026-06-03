/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.time.temporal.ChronoUnit;

public class Member {
    private String name;
    private int id;
    private String contactInfo;
    private HashMap <Book,HashMap> borrowList = new HashMap<Book,HashMap>();    //String->title instead of book
    private int fineMoneyPerDay = 10;
    public static int MembersCount = 0;
    
    public Member(){
        this.id = (MembersCount) + 1000;
        MembersCount++;
    }
    public int getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setContactInfo(String contactInfo){
        this.contactInfo = contactInfo;
    }
    public String getContactInfo(){
        return this.contactInfo;
    }
    
    public void borrowBook(Book borrowedBook, String borrowDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/y");
        dateFormat.setLenient(false);
        Date validateBorrowDate = null;
        Scanner input = new Scanner(System.in);
        boolean correctInput = false;
        while (!correctInput) {
            try {
                validateBorrowDate = dateFormat.parse(borrowDate);
                if (validateBorrowDate != null)
                    correctInput = true;
            } catch (ParseException ex) {
                System.out.println("invalid input!");
                System.out.println("Enter the date in this format: (DD/MM/YYYY)");
                borrowDate = input.nextLine();
            }
        }
        
        HashMap <String,String> borrowDetails = new HashMap<String,String>();
        
        borrowDetails.put("due date",dateFormat.format(validateBorrowDate));
        borrowDetails.put("fine","0");
        
        this.borrowList.put(borrowedBook, borrowDetails);
        borrowedBook.setState("checked out");
    }
    
    public int returnBook(Book borrowedBook){
        
        boolean isBookFound = false;
        for(Book key : this.borrowList.keySet()){
            if(key.getTitle().equals(borrowedBook.getTitle()))
                isBookFound = true;
        }
        
        if(!isBookFound){
            System.out.println("This book is not borrowed by this member");
            return -1;
        }
        
        int fineValue = calculateFine(this.borrowList.get(borrowedBook).get("due date").toString());
        borrowedBook.setState("available");
        this.borrowList.get(borrowedBook).put("fine",fineValue);
        return fineValue;
        
    }
    
    private int calculateFine(String dueDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/y");
        Date todayDate = new Date();
        Date bookDueDate;
        try {
            bookDueDate = dateFormat.parse(dueDate);
        } catch (ParseException ex) {
            System.out.println("Wrong date format");
            return -1;
        }
        if(todayDate.after(bookDueDate)){
//          final long days = ChronoUnit.DAYS.between(firstDate, secondDate);  //work only for localDate object
//            int days = (int) Math.ceil( (todayDate.getTime() - bookDueDate.getTime()) / 1000.0 / 60 / 60 / 24 ) ;
            
            double daysWithFractionValue = (todayDate.getTime() - bookDueDate.getTime() / 1000.0 / 60 / 60 / 24 ) ;
            //because when there are difference in the time in hours between the two dates, then the number of days becomes less than the actual value by less than one, ex: if the result is 2 days, then it will get 1.74545 so we ceil the result
            int days = 0;
            if(daysWithFractionValue < 1)   //if the difference is 0.5 for example, then it will be ceild to 1, this case happens when the return day equals the due date, but we want to have the difference ebtween them to be zero and not 1
                days = 0;
            else
                days = (int) Math.ceil(days);
            return days * this.fineMoneyPerDay;
        }
        return 0;
    }
    
    public void printBorrowedBooks(){
        for(Book borrowedBook : this.borrowList.keySet()){
            System.out.println("book title: "+borrowedBook.getTitle());
            String dueDate = this.borrowList.get(borrowedBook).get("due date").toString();
            System.out.println("due date: "+dueDate);
            int fineValue = calculateFine(dueDate);
            this.borrowList.get(borrowedBook).put("fine",fineValue);
            System.out.println("fine value: "+fineValue);
            System.out.println("Book status: "+borrowedBook.getState());
            System.out.println("__________________________________________");
        }
    }
}
