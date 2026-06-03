/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author MICHAEL NABIL
 */
public class Book {

    private String title;
    private String author;
    private String isbn; //in x-x-x-x-x
    private Date publicationDate;
    private String state;
    public SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/y");
    
    public Book() {
        this.state = "available";
    }
    public Book(String title, String author, String isbn, String publicationDate){
        setTitle(title);
        setAuthor(author);
        setIsbn(isbn);
        setPublicationDate(publicationDate);
        setState("available");
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public String getAuthor(){
        return this.author;
    }
    public void setIsbn(String isbn){
        boolean isCorrectFormat = false;
        
        while(!isCorrectFormat){
            int dashesCount = 0;
            int digitsCount = 0;
            for(int i=0;i<isbn.length();i++){
                if(isbn.charAt(i)=='-')
                    dashesCount++;
                else{
                    if(isbn.charAt(i)<'0' || isbn.charAt(i)>'9')
                        break;
                    
                    digitsCount++;
                }
            }
            if(dashesCount != 4 || digitsCount != 10 || isbn.length()>14){
                System.out.println("invalid isbn format!\ncorrect format : x-x-x-x-x");
                Scanner input = new Scanner(System.in);
                isbn = input.nextLine();
            }
            else
                isCorrectFormat = true;
        }
        this.isbn = isbn;
    }
    public String getIsbn(){
        return this.isbn;
    }
    
    public void setPublicationDate(String publicationDate) {
        this.publicationDate = null;//new Date();
        
        this.dateFormat.setLenient(false);       //to strictly parse the input onlt if it is valid, and if the month is more than 12, dont make strange behaviors

//        System.out.println("Enter the date in this format: (DD/MM/YYYY)");
        Scanner input = new Scanner(System.in);
        boolean correctInput = false;
        while (!correctInput) {
//            String userInput = input.nextLine();
            try {
                this.publicationDate = this.dateFormat.parse(publicationDate);
                if (this.publicationDate == null) {
                    System.out.println("try again   ");
                } else {
                    correctInput = true;
                }
            } catch (ParseException ex) {
                System.out.println("invalid input!");
                System.out.println("Enter the date in this format: (DD/MM/YYYY)");
                publicationDate = input.nextLine();
            }
            System.out.println(this.dateFormat.format(this.publicationDate));
        }
    }
    public String getPublicationDate(){
        return this.dateFormat.format(this.publicationDate);
    }
    
    public void setState(String state){
        boolean isValidValue = false;
        while(!isValidValue){
            if(state.equals("available") || state.equals("checked out")){
                isValidValue = true;
                this.state = state;
            }
            else{
                System.out.println("Invalid value for the book state");
                System.out.println("Enter a valid value (\"available\" or \"checked out\")");
                Scanner input = new Scanner(System.in);
                state = input.nextLine();
            }
        }
    }
    public String getState(){
        return this.state;
    }
}
