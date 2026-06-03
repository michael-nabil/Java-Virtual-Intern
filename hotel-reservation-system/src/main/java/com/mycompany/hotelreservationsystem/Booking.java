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
public class Booking {
    private Guest guest;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private boolean isHistory = false;
    
    public Booking(Guest guest, Room room, LocalDate checkInDate, LocalDate checkOutDate){
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
    
    public boolean getIsHistory(){
        return this.isHistory;
    }
    public void setIsHistory(boolean state){
        this.isHistory = state;
    }
    
    public Guest getGuest(){
        return this.guest;
    }
    public Room getRoom(){
        return this.room;
    }
    public LocalDate getCheckInDate(){
        return this.checkInDate;
    }
    public void setCheckInDate(LocalDate checkInDate){
        this.checkInDate = checkInDate;
    }
    public LocalDate getCheckOutDate(){
        return this.checkOutDate;
    }
    public void setCheckOutDate(LocalDate checkOutDate){
        this.checkOutDate = checkOutDate;
    }
}
