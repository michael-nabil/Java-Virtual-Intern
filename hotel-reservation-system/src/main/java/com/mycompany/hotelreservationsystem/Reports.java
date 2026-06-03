/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotelreservationsystem;
import java.util.*;

/**
 *
 * @author MICHAEL NABIL
 */
public abstract class Reports {
    public void occupayencyRate(Hotel hotel){
        int totalNumberOfRooms = Room.roomsNumber;
        int totalNumberOfReservedRooms = 0;
        for(Room r: hotel.rooms){
            if(r.getRoomState() == RoomState.Booked){
                totalNumberOfReservedRooms++;
            }
        }
        System.out.println("occupyency rate: "+(totalNumberOfReservedRooms/totalNumberOfRooms)*100);
    }
    
    public void peakTimes(Hotel hotel){
        HashMap <Integer,Integer> ReservationsPerMonth = new HashMap<Integer,Integer>();
        for(int i=1;i<13;i++){
            ReservationsPerMonth.put(i, 0);
        }
        for(Booking b:hotel.bookings){
            ReservationsPerMonth.put(b.getCheckInDate().getMonthValue(), ReservationsPerMonth.get(b.getCheckInDate().getMonthValue())+1);
        }
        
        int peakMonth = 0;
        int reservationsPerPeakMonth = 0;
        
        for(Map.Entry<Integer, Integer> entry : ReservationsPerMonth.entrySet()){
            if(entry.getValue()>reservationsPerPeakMonth){
                reservationsPerPeakMonth = entry.getValue();
                peakMonth = entry.getKey();
            }
        }
        System.out.println("The peak month is "+peakMonth+" with total reservations equal: "+reservationsPerPeakMonth);
    }
    public void averageStayDuration(Hotel hotel){
        
        int [] stayDaysPerBooking = new int [hotel.bookings.size()];
        int totalStayingDays = 0;
        
        for(int i=0; i<stayDaysPerBooking.length;i++){
            stayDaysPerBooking[i] = hotel.bookings.get(i).getCheckOutDate().compareTo(hotel.bookings.get(i).getCheckInDate());
            totalStayingDays += stayDaysPerBooking[i];
        }
        
        System.out.println("Average staying days: "+ totalStayingDays/stayDaysPerBooking.length);
    }
}
