/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.librarymanagementsystem;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;
/**
 *
 * @author MICHAEL NABIL
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("__________________WELCOM TO THE LIBRARY APPLICATION__________________");
        Library myLibrary = new Library();
        int mainMenuChoice = 0;
        Scanner input = new Scanner(System.in);
        
        while(mainMenuChoice != 3){
            mainMenu();
            mainMenuChoice = readInt(3,1);
            System.out.println();
            
            switch(mainMenuChoice){
                case 1:
                    int bookMenuChoice = 0;
                    while(bookMenuChoice != 4)
                    {
                        bookMenu();
                        bookMenuChoice = readInt(4,1);
                        System.out.println();
                        Book book = null;
                        switch(bookMenuChoice){
                            case 1:
                                book = myLibrary.addBook(book);
                                System.out.println("Book successfully added");
                                System.out.println();
                                break;
                            case 2:
                                System.out.print("Enter the book title you want to edit: ");
                                String requestedBookTitle = input.nextLine();
                                if(myLibrary.editBook(requestedBookTitle))
                                    System.out.println("The book with the title \""+requestedBookTitle+"\" is edited successfully");
                                else
                                    System.out.println("Sorry the book you requested with the title \""+requestedBookTitle+"\" doesnt exist!");
                                break;
                            case 3:
                                System.out.print("Enter the book title you want to delete: ");
                                requestedBookTitle = input.nextLine();      //test this part
                                if(myLibrary.deleteBook(requestedBookTitle))
                                    System.out.println("The book with the title \""+requestedBookTitle+"\" is deleted successfully");
                                else
                                    System.out.println("Sorry the book you requested with the title \""+requestedBookTitle+"\" doesnt exist!");
                                break;
                            case 4:
                                System.out.println("\nExiting the Book module.\n");
                                break;
                                
                        }
                    }
                    break;
                    
                case 2:
                    
                    int memberMenuChoice = 0;
                    while(memberMenuChoice != 7)
                    {
                        memberMenu();
                        memberMenuChoice = readInt(7,1);
                        System.out.println();
                        Member member = null;
                        switch(memberMenuChoice){
                            case 1:
                                member = myLibrary.addMember(member);
                                System.out.println("member successfully added");
                                System.out.println(member.getName()+"'s id: "+member.getId());
                                System.out.println();
                                break;
                            case 2:
                                System.out.print("Enter the member Id you want to edit: ");
                                int requestedMemberId = readInt(Integer.MAX_VALUE,Integer.MIN_VALUE);
                                if(myLibrary.editMember(requestedMemberId))
                                    System.out.println("the member's data with the id: \""+requestedMemberId+"\" is edited successfully");
                                else
                                    System.out.println("Sorry the member you requested with the id: \""+requestedMemberId+"\" doesnt exist!");
                                break;
                            case 3:
                                System.out.print("Enter the member Id you want to delete: ");
                                requestedMemberId = readInt(Integer.MAX_VALUE,Integer.MIN_VALUE);
                                if(myLibrary.deleteMember(requestedMemberId))
                                    System.out.println("the member's data with the id: \""+requestedMemberId+"\" is deleted successfully");
                                else
                                    System.out.println("Sorry the member you requested with the id: \""+requestedMemberId+"\" doesnt exist!");
                                break;
                            case 4:
                                if(myLibrary.borrowBook()){
                                    System.out.println("The book is borrowed successfully");
                                }
                                else{
                                    System.out.println("Sorry the book is NOT borrowed");
                                }
                                break;
                            case 5:
                                int fineValue = myLibrary.returnBook();
                                if(fineValue == -1){
                                    System.out.println("The book is NOT returned!");
                                }
                                else{
                                    if(fineValue > 0)
                                        System.out.println("you have a fine with the value of "+fineValue+" for being late");
                                    
                                    System.out.println("The book is returned successfully");
                                }
                                break;
                            case 6:
                                System.out.print("Enter the member id: ");
                                int memberId = readInt(Integer.MAX_VALUE,Integer.MIN_VALUE);
                                boolean doesMemberExist = false;
                                for(Member m:myLibrary.members){
                                    if(m.getId() == memberId){
                                        m.printBorrowedBooks();
                                        doesMemberExist = true;
                                        break;
                                    }
                                }
                                if(!doesMemberExist)
                                    System.out.println("This member doesn't exist");
                                break;
                            case 7:
                                System.out.println("\nExiting the Member module.\n");
                                break; 
                        }
                    }
                    
                    break;
                case 3:
                    System.out.println("\nExiting the program. Goodbye!");
                    break;
            }
        }
    }
    
    public static void mainMenu(){
        System.out.print("1. Books module\n2. Members module\n3. Exit\nEnter your choice: ");
    }
    
    public static void bookMenu(){
        System.out.print("1. Add book\n2. edit book\n3. delete book\n4. Exit to main menu\nEnter your choice: ");
    }
    public static void memberMenu(){
        System.out.print("1. Add member\n2. edit member\n3. Delete member\n4. Borrow book\n5. Return book\n6. Print borrowed books with details for a member\n7. Exit to main menu\nEnter your choice: ");
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
}





/*

//        Date d = new Date();
//        System.out.println(d);
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/Y");
//        System.out.print(sdf.format(d));
   
          HashMap <String,HashMap> borrowList = new HashMap<String,HashMap>();
          HashMap <String,String> borrowDetails = new HashMap<String,String>();
          
          borrowDetails.put("due date", "10/2/2022");
          borrowDetails.put("fine", "350");
          borrowList.put("how to organize your desk", borrowDetails);
          
          borrowDetails = new HashMap<String,String>();
          borrowDetails.put("due date", "15/3/2022");
          borrowDetails.put("fine", "0");
          borrowList.put("critical thinking", borrowDetails);
          
//          System.out.println(borrowList.get("critical thinking").get("fine"));
          
          for(Object k : borrowList.keySet()){
              System.out.println("Book title: "+k.toString());
              System.out.println("due date: "+ borrowList.get(k).get("due date"));
              if(Integer.parseInt((String) borrowList.get(k).get("fine")) != 0)
                System.out.println("fine value: "+ borrowList.get(k).get("fine"));
              
          }

        Book b1 = new Book("eat","mic","12-34-56-789-0","28/2/2008");
        Book b2 = new Book("play","fady","12-84-96-689-4","2/8/2020");
        Book b3 = new Book("run","filo","75-24-06-709-0","12/4/2015");
        
        Member m1 = new Member();
        m1.borrowBook(b3, "19/9/2024");
        System.out.println(m1.returnBook(b3));
        
        

//        BookManager bookMngr = new BookManager();
//        bookMngr.addBook(b1);
//        bookMngr.addBook(b2);
//        bookMngr.addBook(b3);
//          
//        ArrayList<Book> var = bookMngr.books;
//        for(Book b : var)
//            System.out.println(b.getTitle());
//        boolean isRemoved = bookMngr.deleteBook(b2.getTitle());
//        System.out.println(isRemoved);
//        for(Book b : var)
//            System.out.println(b.getTitle());
        


*/