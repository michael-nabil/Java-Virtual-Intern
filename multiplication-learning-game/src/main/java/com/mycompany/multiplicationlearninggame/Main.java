/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.multiplicationlearninggame;
import java.util.*;
/**
 *
 * @author MICHAEL NABIL
 */
public class Main {
    
    public static int highestScore = 0;
    
    public static long seed = System.currentTimeMillis();
    public static Random randGenerator = new Random(seed);
    
    public static void main(String[] args) {    
        int choice = 0;
        while(choice != 4)
        {
            menu();
            choice = readInt(4,1);
            System.out.println();
            switch(choice){
                case 1:
                    startGame();
                    break;
                case 2:
                    help();
                    break;
                case 3:
                    showHighScore();
                    break;
                case 4:
                    System.out.println("\nExiting the program. Goodbye!");
                    break;
            }
        }
    }
    
    public static void help(){
        System.out.println("Welcom, this is a multiplication game program.");
        System.out.println("when you choose the first choice \'Start Game\' you start a multiplication game session.");
        System.out.println("The instructions are:\nThere are 3 difficulty levels in the game.\nIf you get 3 successive false answers, the game is over.\nEach time you get 3 successive right answers, the difficulty level increases.\nIf you asnswer 3 successive right questions in level 3, then you win.\nFinally the game ends by getting three successive wrong answers, or after 20 questions, then you get the high score of this game session.\n");
    }
    public static void showHighScore(){
        System.out.println("Your overall High Score: "+highestScore+'\n');
    }
    public static void menu(){
        System.out.print("1. Start Game\n2. Help\n3. Show High Score\n4. Exit\nEnter your choice: ");
    }
    public static int getRandInt(int digitsCount){
        int randInt = 0;
        
        switch(digitsCount){
            case 1:     //(0-9)
                randInt = randGenerator.nextInt(10);
                break;
            case 2:     //(10-99)
                randInt = 10 + randGenerator.nextInt(90);
                break;
            case 3:     //(100-999)
                randInt = 100 + randGenerator.nextInt(900);
        }
        
        return randInt;
    }
    
    public static void startGame(){
        Scanner input = new Scanner(System.in);
        int successive_correct = 0;
        int successive_wrong = 0;
        int current_level = 1;
        int score[] = new int[2];   //score[0] --> right answers count,  score[1] --> wrong answers
        
        System.out.print("Enter your name: ");
        String userName = input.nextLine();
        
        int num1,num2,result;
        
        for(int i=0;i<20;i++)
        {
            num1 = getRandInt(current_level);
            num2 = getRandInt(current_level);
            System.out.println("how much is "+num1+" times "+num2+"?\t");
            result = readInt(Integer.MAX_VALUE,Integer.MIN_VALUE);
            
            if(result == num1*num2){
                System.out.println("Right answer\n");
                score[0]++;
                successive_correct++;
                successive_wrong = 0;
                if(successive_correct == 3){
                    current_level++;
                    successive_correct = 0;
                }
                if(current_level > 3){
                    System.out.println("Congratulations you Won ^_^");
                    
                    if(score[0] > highestScore)
                        highestScore = score[0];
                    
                    return ;
                }
            }
            else{
                System.out.println("Wrong answer!");
                score[1]++;
                successive_wrong++;
                successive_correct = 0;
                
                if(successive_wrong == 3)
                    break;
            }
        }
        
        if(successive_wrong == 3)
            System.out.println("Game Over. You've made 3 successive wrong answers");
        else    //if the game have ended after the 20 trials
            System.out.println("Game finished");
        
        if(current_level == 1)
            System.out.println("Please ask your teacher for extra help");
        else if(current_level == 2)
            System.out.println("Great but you need more training");
        else
            System.out.println("Excellent you are so close");

        if(score[0] > highestScore)
            highestScore = score[0];
        
        System.out.println("High score: "+score[0]+'\n');
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
