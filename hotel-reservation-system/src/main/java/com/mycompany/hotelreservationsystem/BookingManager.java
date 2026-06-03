/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotelreservationsystem;
import java.time.LocalDate;
/**
 *
 * @author MICHAEL NABIL
 */
public abstract class BookingManager {
    public Booking createBooking(Guest guest,Room room,LocalDate checkInDate, LocalDate checkOutDate){
        if(checkInDate.isBefore(LocalDate.now())){
            System.out.println("invalid Check-In date");
            return null;
        }
        else if(checkOutDate.isBefore(checkInDate)){
            System.out.println("invalid Check-Out date");
            return null;
        }
        Booking newBooking = new Booking(guest,room,checkInDate,checkOutDate);
        newBooking.getRoom().setRoomState(RoomState.Booked);
        return newBooking;
    }
    public Booking extendBooking(Booking booking,LocalDate extendedDate){
        
        //check if this room is reserved in the new date (extended date), but we will nedd this this if we allow making a reservation for the room in the same time of it is reserved by another one
        
        if(extendedDate.isAfter(booking.getCheckOutDate())){
            booking.setCheckOutDate(extendedDate);
            return booking;
        }
        else{
            System.out.println("Invalid extend date");
            return null;
        }
    }
    public boolean cancelBooking(Booking booking){
        booking.getRoom().setRoomState(RoomState.Available);
        return true;
    }
}
