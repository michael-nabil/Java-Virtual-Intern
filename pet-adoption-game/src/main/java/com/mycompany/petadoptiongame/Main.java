/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.petadoptiongame;
import java.util.Scanner;


public class Main {
    public static boolean confirmation = false;     //if there is an adopted pet
    public static int hunger = 0, happiness = 0;
    public static String name = "";
    
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int choice = 0;
        boolean isTheGameEnded = false;
        while(! isTheGameEnded)
        {
            //Scanner input = new Scanner(System.in);
            menu();
            System.out.print("choose an option: ");
            //validate on the user choice when choosing from the menu
            String [] userInput = input.nextLine().strip().split(" ");      //strip first to remove exess spaces at the start and end, then split according to spaces in the middle
            try{
                choice = Integer.parseInt(userInput[0]);
            }
            catch(NumberFormatException e)
            {
                System.out.print("wrong input\n");
                continue;
            }
            if(choice < 1 || choice > 5)
            {
                System.out.print("wrong input\n");
                continue;                
            }
            switch(choice)
            {
                case 1:
                    boolean result = adopt();
                    if(result == false)
                        System.out.println("You currently have a pet, to adopt another pet select \"End Game\"");
                    else
                        confirmation = true;
                    break;
                    
                case 2:
                    if(confirmation == false)
                        System.out.println("Adopt a pet first");
                    else
                    {
                        System.out.println("Pet has been fed.");
                        if(feed())
                            isTheGameEnded = endGame();
                    }
                    break;
                case 3:
                    if(confirmation == false)
                        System.out.println("Adopt a pet first");
                    else
                    {
                        System.out.println("Pet played and is happier now.");
                        if(play())
                            isTheGameEnded = endGame();
                    }
                    break;
                case 4:
                    if(confirmation == false)
                        System.out.println("Adopt a pet first");
                    else
                        check();
                    break;
                case 5:
                    if(confirmation == false)
                        System.out.println("Adopt a pet first");
                    else
                        isTheGameEnded = endGame();
                    break;
                
            }
            //after gameOver, ask: play again: enter 1, exit: enter 0,    we can add this functionality to the game over method as it is associated with it, and retuen the user choice
            //after each feed and play, check if the returned value is true then call the GAMEOVER method
        }
        
    }
    
    
    public static void menu(){
        System.out.print("1. Adopt pet\n2. Feed pet\n3. Play with pet\n4. Check status\n5. End game\n");
    }
    public static boolean adopt(){
        if(confirmation == true)
            return false;
        else
        {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter the pet's name: ");
            name = input.nextLine();
            name = name.strip();
            if(name.contains(" "))
            {
                name = name.substring(0, name.indexOf(' '));
            }
            hunger = 50;
            happiness = 50;
            return true;
        }   
    }
    public static boolean feed(){
        if(hunger < 90)
            hunger += 10;
        happiness -= 10;
        
        return happiness == 0;
    }
    public static boolean play(){
        if(happiness < 90)
            happiness += 10;
        hunger -= 20;
        
        return hunger == 0;
    }
    public static void check(){
        //validate that there are a current adopted pet (if the confirmation is true)
        System.out.println(name+"'s hunger: "+hunger+"\n"+name+"'s happiness: "+happiness);
    }
    public static boolean endGame()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("___________GAME OVER___________\nYour pet's condition has reached a critical level.\n");
        check();
        confirmation = false;
        System.out.println("_______________________________");
        System.out.print("1. play again\n0. exit\nEnter your choice:");
        String [] userInput = input.nextLine().strip().split(" ");
        
        boolean isValid = false;
        int choice = 0;
        while(!isValid)
        {
            try{
                choice = Integer.parseInt(userInput[0]);
                if(choice == 1 || choice == 0)
                    isValid = true;     //and in case we reched this line and didnt get an exception in the previous line, then this means that the user input is an integer.
                //the else, the "isValid" is still false
            }
            catch(NumberFormatException e)
            {
                System.out.println("Invalid input");
            }
        }
        if(choice == 0)
            return true;    //the game ended
        else
            return false;   //the game continues
    }
}
