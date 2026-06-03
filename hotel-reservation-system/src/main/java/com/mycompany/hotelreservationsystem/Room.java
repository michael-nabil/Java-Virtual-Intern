/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotelreservationsystem;
import java.util.ArrayList;
/**
 *
 * @author MICHAEL NABIL
 */
public class Room {
    public static int roomsNumber=0;
    private int roomNumber;
    private double pricePerNight;
    private RoomType roomType;
    private double rate;
    private ArrayList<Double> rateList = new ArrayList<Double>();
    private String features;
    private RoomState roomState;
    
    public Room(){
        roomsNumber++;
        this.roomNumber = this.roomsNumber;
        this.rate = 0;
    }
    
//    public void setRoomNumber(int roomNumber){
//        this.roomNumber = roomNumber;
//    }
    public int getRoomNumber(){
        return this.roomNumber;
    }
    
    public void setPricePerNight(double pricePerNight){
        this.pricePerNight = pricePerNight;
    }
    public double getPricePerNight(){
        return this.pricePerNight;
    }
    
    public void setRoomType(RoomType roomType){
        this.roomType = roomType;
    }
    public RoomType getRoomType(){
        return this.roomType;
    }
    
    public void addRate(double rate){
        this.rateList.add(rate);
    }
    public double getRate(){
        if(rateList.size() == 0)
            return 0;
        else{
            int sum = 0;
            for(double r: this.rateList){
                sum+=r;
            }
            this.rate = (double)sum/rateList.size();
            return this.rate;
        }
    }
    
    public void setFeatures(String features){
        this.features = features;
    }
    public String getFeatures(){
        return this.features;
    }
    
    public void setRoomState(RoomState roomState){
        this.roomState = roomState;
    }
    public RoomState getRoomState(){
        return this.roomState;
    }
}
