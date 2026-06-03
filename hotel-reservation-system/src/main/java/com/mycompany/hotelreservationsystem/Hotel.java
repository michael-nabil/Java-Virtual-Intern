/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotelreservationsystem;
import java.util.*;
import java.time.LocalDate;
/**
 *
 * @author MICHAEL NABIL
 */
public class Hotel {
    
    public ArrayList<Room> rooms = new ArrayList<Room>();
    public ArrayList<Booking> bookings = new ArrayList<Booking>();
    public ArrayList<Guest> guests = new ArrayList<Guest>();
    
    public boolean checkIn(Booking booking){
        if(LocalDate.now().isEqual(booking.getCheckInDate())){
            booking.getRoom().setRoomState(RoomState.Occupied);
            System.out.println("Welcome");
            return true;
        }
        else{
            System.out.println("your check-In date is not today, but it is \' "+ booking.getCheckInDate().toString() +" \'");
            return false;
        }
    }
    public boolean checkOut(Booking booking){
        double finalBill = booking.getRoom().getPricePerNight() * booking.getCheckOutDate().compareTo(booking.getCheckInDate());
        System.out.println("Final Bill: "+finalBill);
        double ratting = InputValidation.doubleInput("Please rate ouer room (1-5): ");
        if(ratting < 1){
            booking.getRoom().addRate(1);
        }else if(ratting > 5){
            booking.getRoom().addRate(5);
        }else{
            booking.getRoom().addRate(ratting);
        }
        System.out.println("GoodBye");
        booking.getRoom().setRoomState(RoomState.Available);
        booking.setIsHistory(true);
        return true;
    }
    
    public void /*RoomState*/ ManageRoomAvailability(){
        
    }
}
