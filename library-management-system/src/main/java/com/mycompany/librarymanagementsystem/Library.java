/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem;
import static com.mycompany.librarymanagementsystem.Main.readInt;
import java.util.*;
import java.util.function.Predicate;
/**
 *
 * @author MICHAEL NABIL
 */
public class Library {
    ArrayList <Book> books = null;
    ArrayList <Member> members = null;
    
    public Library(){
        this.books = new ArrayList<Book>();
        this.members = new ArrayList<Member>();
    }
    public Book addBook(Book book){
        book = new Book();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter book details:");

        System.out.print("book title: ");
        String title = input.nextLine();
        book.setTitle(title);
        System.out.print("book author: ");
        String author = input.nextLine();
        book.setAuthor(author);
        System.out.print("book isbn in this format -> (x-x-x-x-x) : ");
        String isbn = input.nextLine();
        book.setIsbn(isbn);
        System.out.print("book publication date in this format -> (dd/mm/yyyy): ");
        String publicationDate = input.nextLine();
        book.setPublicationDate(publicationDate);
        this.books.add(book);
        
        return book;
    }
    
    public boolean editBook(String requestedBookTitle){
        System.out.print("1. Edit Title\n2. Edit Author\n3. Edit ISBN\n4. Edit Publication date\nEnter your choice: ");
        int editChoice = readInt(4,1);
        Scanner input = new Scanner(System.in);
        switch(editChoice){
            case 1:
                System.out.print("Enter the new book Title: ");
                String newTitle = input.nextLine();
                return this.editBookTitle(requestedBookTitle, newTitle);
                //break;
            case 2:
                System.out.print("Enter the new book Author: ");
                String newAuthor = input.nextLine();
                return this.editBookAuthor(requestedBookTitle, newAuthor);
                //break;
            case 3:
                System.out.print("Enter the new book ISBN: ");
                String newIsbn = input.nextLine();
                return this.editBookIsbn(requestedBookTitle, newIsbn);
                //break;
            case 4:
                System.out.print("Enter the new book Publication date: ");
                String newPublicationDate = input.nextLine();
                return this.editBookPublicationDate(requestedBookTitle, newPublicationDate);
                //break;
        }
        return false;
    }
    
    public boolean editBookTitle(String oldTitle, String newTitle){
        for(Book b : this.books){
            if(b.getTitle().equals(oldTitle)){
//                System.out.println("Enter which value do you want to edit:\n1. title\n2. author\n3. isbn\n4. publication date");
//                Scanner input = new Scanner(System.in);
//                int choice = 
                b.setTitle(newTitle);
                return true;
            }
        }
//        System.out.println("the book with \""+ title +"\" doesn't exist !");
        return false;
    }
    public boolean editBookAuthor(String title, String newAuthor){
        for(Book b : this.books){
            if(b.getTitle().equals(title)){
                b.setAuthor(newAuthor);
                return true;
            }
        }
//        System.out.println("the book with \""+ title +"\" doesn't exist !");
        return false;
    }
    
    public boolean editBookIsbn(String title, String newIsbn){
        for(Book b : this.books){
            if(b.getTitle().equals(title)){
                b.setIsbn(newIsbn);
                return true;
            }
        }
//        System.out.println("the book with \""+ title +"\" doesn't exist !");
        return false;
    }
    public boolean editBookPublicationDate(String title, String newPublicationDate){
        for(Book b : this.books){
            if(b.getTitle().equals(title)){
                b.setPublicationDate(newPublicationDate);
                return true;
            }
        }
//        System.out.println("the book with \""+ title +"\" doesn't exist !");
        return false;
    }
    
    public boolean deleteBook(String title){
//        for(int i=0;i<books.size();i++){
//            if(books.get(i).getTitle() == title){   //returnes the book in the index i, then uses the method in the book object (getTitle)
//                this.books.remove(i);
//                return true;
//            }
//        }
////        System.out.println("the book with \""+ title +"\" doesn't exist !");
//        return false;
    
        Predicate <Book> doesBookExist = book -> book.getTitle().equals(title);
        return this.books.removeIf(doesBookExist);
    }
    public Member addMember(Member member){
        member = new Member();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter member details:");

        System.out.print("member name: ");
        String name = input.nextLine();
        member.setName(name);
        System.out.print("member contact info: ");
        String contactInfo = input.nextLine();
        member.setContactInfo(contactInfo);
        this.members.add(member);
        
        return member;
    }
    
    
    public boolean editMember(int memberId){
        System.out.print("1. Edit member Name\n2. Edit member Contact Info\nEnter your choice: ");
        int editChoice = readInt(2,1);
        Scanner input = new Scanner(System.in);
        switch(editChoice){
            case 1:
                System.out.print("Enter the edited member name: ");
                String newName = input.nextLine();
                return this.editMemberName(memberId, newName);
                //break;
            case 2:
                System.out.print("Enter the new member Contact info: ");
                String newContactInfo = input.nextLine();
                return this.editMemberContactInfo(memberId, newContactInfo);
                //break;
        }
        return false;
    }
    
    public boolean editMemberName(int memberId, String newName){
        for(Member m : this.members){
            if(m.getId() == memberId){
                m.setName(newName);
                return true;
            }
        }
        return false;
    }
    
    public boolean editMemberContactInfo(int memberId, String newContactInfo){
        for(Member m : this.members){
            if(m.getId() == memberId){
                m.setContactInfo(newContactInfo);
                return true;
            }
        }
        return false;
    }
    
    public boolean deleteMember(int memberId){
        Predicate <Member> doesMemberExist = m -> m.getId() == memberId;
        return this.members.removeIf(doesMemberExist);
    
    }
    
    public boolean borrowBook(){
        System.out.print("Enter the member Id for borrowing: ");
        int memberId = readInt(Integer.MAX_VALUE,Integer.MIN_VALUE);
        Scanner input = new Scanner(System.in);
        
        boolean isMemberFound = false;
        Member borrower = null;
        for(Member m : this.members){
            if(m.getId() == memberId){
                isMemberFound = true;
                borrower = m;
                break;
            }
        }
        if(!isMemberFound){
            System.out.println("This member is not found!");
            return false;
        }
        
        System.out.print("Enter the book title you want to borrow: ");
        String bookTitle = input.nextLine();
        boolean isBookFound = false;
        Book bookToBorrow = null;
        for(Book b : this.books){
            if(b.getTitle().equals(bookTitle)){
                isBookFound = true;
                bookToBorrow = b;
                break;
            }
        }
        if(!isBookFound){
            System.out.println("This book is not found!");
            return false;
        }
        if(bookToBorrow.getState().equals("checked out")){
            System.out.println("The book is checked out! try borrowing another book");
            return false;
        }
        
        System.out.print("Enter the borrow due date: ");
        String borrowDueDate = input.nextLine();
        borrower.borrowBook(bookToBorrow, borrowDueDate);
        
        for(Book b : this.books){
            if(b.getTitle().equals(bookToBorrow.getTitle()))
                b.setState("checked out");
        }
//        for(Book b: this.books){
//            System.out.println(b.getTitle()+"    "+b.getState());
//        }
        return true;
    }
    public int returnBook(){
        System.out.print("Enter the member Id for returning: ");
        int memberId = readInt(Integer.MAX_VALUE,Integer.MIN_VALUE);
        Scanner input = new Scanner(System.in);
        
        boolean isMemberFound = false;
        Member returner = null;
        for(Member m : this.members){
            if(m.getId() == memberId){
                isMemberFound = true;
                returner = m;
                break;
            }
        }
        if(!isMemberFound){
            System.out.println("This member is not found!");
            return -1;
        }
        
        System.out.print("Enter the book title you want to return: ");
        String bookTitle = input.nextLine();
        boolean isBookFound = false;
        Book bookToReturn = null;
        for(Book b : this.books){
            if(b.getTitle().equals(bookTitle)){
                isBookFound = true;
                bookToReturn = b;
                break;
            }
        }
        if(!isBookFound){
            System.out.println("This book is not found!");
            return -1;
        }
        if(bookToReturn.getState().equals("available")){
            System.out.println("This book is not borrowed ! choose another book");
            return -1;
        }
        
        int fineAmount = returner.returnBook(bookToReturn);
        for(Book b : this.books){
            if(b.getTitle().equals(bookToReturn.getTitle()))
                b.setState("available");
        }
        return fineAmount;
    }
}
