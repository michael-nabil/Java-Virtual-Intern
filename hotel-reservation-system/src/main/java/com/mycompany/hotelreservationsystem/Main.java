/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hotelreservationsystem;

/**
 *
 * @author MICHAEL NABIL
 */
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
    
    private static Hotel hotel = new Hotel();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        initializeRooms(); // Pre-populate rooms
        
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    if(createGuest())
                        System.out.println("Guest created successfully");
                    break;
                case 2:
                    if(createRoom())
                        System.out.println("Room creted successfully");
                    break;
                case 3:
                    if(createBooking())
                        System.out.println("Booking created successfully.");
                    else
                        System.out.println("Booking failed");
                    break;
                case 4:
                    checkInGuest();
                    break;
                case 5:
                    checkOutGuest();
                    break;
                case 6:
                    if(extendReservation())
                        System.out.println("you have successfully updated the check out date");
                    else
                        System.out.println("your check out date is NOT updated");
                    break;
                case 7:
                    if(cancelReservation())
                        System.out.println("Booking cancelled successfully");;
                    break;
                case 8:
                    generateReports();
                    break;
                case 9:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 9);
    }

    private static void displayMenu() {
        System.out.println("\n--- Hotel Management System ---");
        System.out.println("1. Create Guest");
        System.out.println("2. Create Room");
        System.out.println("3. Create Booking");
        System.out.println("4. Check In Guest");
        System.out.println("5. Check Out Guest");
        System.out.println("6. Extend reservation");        
        System.out.println("7. cancel reservation");        
        System.out.println("8. Generate Reports");
        System.out.println("9. Exit");
        System.out.print("Choose an option: ");
    }
    
    private static boolean createRoom(){
        Room myRoom = new Room();
        int roomTypeChoice = InputValidation.IntInput("Enter the room type:\n[1] Singel\n[2] Double\n[3] Triple\n[4] Suite\nyour choice: ");
        switch(roomTypeChoice){
            case 1:
                myRoom.setRoomType(RoomType.Single);
                break;
            case 2:
                myRoom.setRoomType(RoomType.Double);
                break;
            case 3:
                myRoom.setRoomType(RoomType.Triple);
                break;
            case 4:
                myRoom.setRoomType(RoomType.Suite);
                break;
            default:
                System.out.println("invalid Choice");
                return false;
        }
        Double pricePerNight = InputValidation.doubleInput("Enter the room price per night: ");
        if(pricePerNight < 1){
            System.out.println("Invalid price room");
            return false;
        }
        else
            myRoom.setPricePerNight(pricePerNight);
        String roomFeatures = InputValidation.StringInput("Enter the room features: ");
        myRoom.setFeatures(roomFeatures);
        myRoom.setRoomState(RoomState.Available);
        hotel.rooms.add(myRoom);
        return true;
    }
    
    private static boolean createGuest(){
        Guest myGuest = new Guest();
        String guestName = InputValidation.StringInput("Enter the Guest name: ");
        if(guestName.length() == 0){
            System.out.println("error empty name!");
            return false;
        }
        String guestPhoneNumber = InputValidation.StringInput("Enter the Guest phone number: ");
        if(guestPhoneNumber.length() == 0){
            System.out.println("error empty phone number!");
            return false;
        }
        String guestAddress = InputValidation.StringInput("Enter the Guest address: ");
        if(guestAddress.length() == 0){
            System.out.println("error empty address!");
            return false;
        }
        
        myGuest.setAddress(guestAddress);
        myGuest.setName(guestName);
        myGuest.setPhoneNumber(guestPhoneNumber);
        
        hotel.guests.add(myGuest);
        return true;
    }
    
    private static void initializeRooms() {
        Room room1 = new Room();
//        room1.setRoomNumber(101);
        room1.setPricePerNight(100);
        room1.setRoomType(RoomType.Single);
        room1.setFeatures("room 1");
        room1.setRoomState(RoomState.Available);
        
        Room room2 = new Room();
//        room2.setRoomNumber(102);
        room2.setPricePerNight(150);
        room2.setRoomType(RoomType.Double);
        room2.setFeatures("room 2");
        room2.setRoomState(RoomState.Available);
        
        hotel.rooms.add(room1);
        hotel.rooms.add(room2);
    }

    private static boolean createBooking() {
        System.out.print("Enter guest name: ");
        String guestName = scanner.nextLine();
        Guest myGuest = null;
        
        for(Guest g: hotel.guests){
            if(g.getName().equals(guestName)){
                myGuest = g;
                break;
            }
        }
        if(myGuest == null){
            System.out.println("This guest name is not found!");
            return false;
        }
        
        int roomActualIndex = -1;    //the index of each room in the array
        int availableRoomIndex = -1;     //the index of the room in prespective to the available rooms
        int [] roomsActualIndexes = new int[hotel.rooms.size()];
        for(Room r: hotel.rooms){
            roomActualIndex++;
            //if(r.getRoomState() == RoomState.Available){
                availableRoomIndex++;
                roomsActualIndexes[availableRoomIndex] = roomActualIndex;
                System.out.println("Room ["+ (int)(availableRoomIndex+1) +"] "+ r.getRoomState().toString());
                System.out.println("room type: " + r.getRoomType());
                System.out.println("room price per night: " + r.getPricePerNight()+"$");
                System.out.println("room features: " + r.getFeatures());
                System.out.println("room rating: " + r.getRate());
                System.out.println("____________________________________________");
            //}
            
        }
        int chosenRoomIndex = InputValidation.IntInput("Enter the chosen room number: ");
        if(chosenRoomIndex > availableRoomIndex+1 || chosenRoomIndex < 1){
            System.out.println("Invalid room number!");
            return false;
        }
        
        Room chosenRoom = hotel.rooms.get(roomsActualIndexes[chosenRoomIndex - 1]);
        System.out.println(chosenRoom.getFeatures());
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        System.out.print("Enter check-in date (DD/MM/YYYY): ");
        LocalDate checkInDate = LocalDate.parse((CharSequence)scanner.nextLine(),formatter);
        System.out.print("Enter check-out date (DD/MM/YYYY): ");
        LocalDate checkOutDate = LocalDate.parse((CharSequence)scanner.nextLine(),formatter);
        
        for(Booking b: hotel.bookings){
            if(b.getRoom().getRoomNumber() == chosenRoom.getRoomNumber()){
                if(b.getIsHistory())
                    continue;
                else{
                    if(b.getCheckInDate().isBefore(checkInDate)){
                        if(b.getCheckOutDate().isAfter(checkInDate)){
                            System.out.println("Ops unfortunately this room is reserved during your stay interval!");
                            return false;
                        }
                        continue;
                    }
                    continue;
                }
            }
        }
        
//        Room availableRoom = hotel.rooms.stream()
//            .filter(room -> room.getRoomState() == RoomState.Available)
//            .findFirst()
//            .orElse(null);
//        if (availableRoom != null) {
//            BookingManager bookingManager = new BookingManager() {};
//            Booking booking = bookingManager.createBooking(myGuest, availableRoom, checkInDate, checkOutDate);
//            if (booking != null) {
//                hotel.bookings.add(booking);
//                System.out.println("Booking created successfully.");
//            }
//        } else {
//            System.out.println("No available rooms.");
//        }

        BookingManager bookingManager = new BookingManager() {};
        Booking booking = bookingManager.createBooking(myGuest, chosenRoom, checkInDate, checkOutDate);
        if (booking != null) {
                hotel.bookings.add(booking);
//                System.out.println("Booking created successfully.");
                return true;
            }
        else {
//            System.out.println("No available rooms.");
            return false;
        }
    }

    private static void checkInGuest() {
        System.out.print("Enter guest name for check-in: ");
        String guestName = scanner.nextLine();

        for (Booking booking : hotel.bookings) {
            if (booking.getGuest().getName().equalsIgnoreCase(guestName)) {
                if(!booking.getIsHistory())
                {
                    hotel.checkIn(booking);
                    return;
                }
            }
        }
        System.out.println("No booking found for guest: " + guestName);
    }

    private static void checkOutGuest() {
        System.out.print("Enter guest name for check-out: ");
        String guestName = scanner.nextLine();

        for (Booking booking : hotel.bookings) {
            if (booking.getGuest().getName().equalsIgnoreCase(guestName)) {
                if(!booking.getIsHistory())
                {
                    hotel.checkOut(booking);
                    return;
                }
            }
        }
        System.out.println("No booking found for guest: " + guestName);
    }

    private static boolean extendReservation(){
        System.out.print("Enter guest name: ");
        String guestName = scanner.nextLine();
        Guest myGuest = null;
        
        for(Guest g: hotel.guests){
            if(g.getName().equals(guestName)){
                myGuest = g;
                break;
            }
        }
        if(myGuest == null){
            System.out.println("This guest name is not found!");
            return false;
        }
        
        ArrayList <Booking> guestBookings = new ArrayList<Booking>();
        for(Booking b: hotel.bookings){
            if(b.getGuest().getName().equals(guestName) && !b.getIsHistory())
                guestBookings.add(b);
        }
        if(guestBookings.size()==0){
            System.out.println("No booking yet!");
            return false;
        }
        int counter = -1;
        for(Booking b: guestBookings){
            counter++;
            System.out.println("Booking ["+(int)(counter+1)+"]:");
            System.out.println("room type: "+b.getRoom().getRoomType());
            System.out.println("check in date: "+b.getCheckInDate().toString());
            System.out.println("check out date: "+b.getCheckOutDate().toString());
            System.out.println("__________________________________________");
        }
        int chosenBookingIndex = InputValidation.IntInput("Enter the chosen booking number: ");
        if(chosenBookingIndex > counter+1 || chosenBookingIndex < 1){
            System.out.println("Invalid room number!");
            return false;
        }
        Booking chosenBooking = hotel.bookings.get(hotel.bookings.indexOf(guestBookings.get(chosenBookingIndex - 1)));
//        Booking chosenBooking = guestBookings.get(chosenBookingIndex);
        
        LocalDate lastAvailableDayForExtending = LocalDate.MAX;
        for(Booking b: hotel.bookings){
            if(!b.getIsHistory() && b.getRoom().getRoomNumber() == chosenBooking.getRoom().getRoomNumber() && b.getCheckInDate().isAfter(chosenBooking.getCheckOutDate())){
                if(b.getCheckInDate().isBefore(lastAvailableDayForExtending))
                    lastAvailableDayForExtending = b.getCheckInDate();
            }
        }
        if(lastAvailableDayForExtending == LocalDate.MAX){
            System.out.println("You dont have a limit day for extending");
        }else{
            System.out.println("your maximum available date for extending is: "+lastAvailableDayForExtending.toString());
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        System.out.print("Enter Extending date(check-out) (DD/MM/YYYY): ");
        LocalDate extendedDate = LocalDate.parse((CharSequence)scanner.nextLine(),formatter);
        if(extendedDate.isBefore(chosenBooking.getCheckInDate())){
            System.out.println("Invalid date (the date you entered is before the check in date)");
            return false;
        }else if(extendedDate.isAfter(lastAvailableDayForExtending)){
            System.out.println("Invalid date (you have exceded the max available date)");
            return false;
        }else if(extendedDate.isBefore(chosenBooking.getCheckOutDate())){
            System.out.println("You have shrinked the booking duration");
            chosenBooking.setCheckOutDate(extendedDate);
            return true;
        }else{
            System.out.println("you have extended the booking duration");
            chosenBooking.setCheckOutDate(extendedDate);
            return true;
        }
    }
    
    private static boolean cancelReservation(){
        System.out.print("Enter guest name: ");
        String guestName = scanner.nextLine();
        Guest myGuest = null;
        
        for(Guest g: hotel.guests){
            if(g.getName().equals(guestName)){
                myGuest = g;
                break;
            }
        }
        if(myGuest == null){
            System.out.println("This guest name is not found!");
            return false;
        }
        
        ArrayList <Booking> guestBookings = new ArrayList<Booking>();
        for(Booking b: hotel.bookings){
            if(b.getGuest().getName().equals(guestName) && !b.getIsHistory())
                guestBookings.add(b);
        }
        if(guestBookings.size()==0){
            System.out.println("No booking yet!");
            return false;
        }
        int counter = -1;
        for(Booking b: guestBookings){
            counter++;
            System.out.println("Booking ["+(int)(counter+1)+"]:");
            System.out.println("room type: "+b.getRoom().getRoomType());
            System.out.println("check in date: "+b.getCheckInDate().toString());
            System.out.println("check out date: "+b.getCheckOutDate().toString());
            System.out.println("__________________________________________");
        }
        int chosenBookingIndex = InputValidation.IntInput("Enter the chosen booking number: ");
        if(chosenBookingIndex > counter+1 || chosenBookingIndex < 1){
            System.out.println("Invalid room number!");
            return false;
        }
        Booking chosenBooking = hotel.bookings.get(hotel.bookings.indexOf(guestBookings.get(chosenBookingIndex - 1)));
//        Booking chosenBooking = guestBookings.get(chosenBookingIndex);
        System.out.println(chosenBooking.getCheckInDate().toString());
        
        chosenBooking.getRoom().setRoomState(RoomState.Available);
        hotel.bookings.remove(chosenBooking);
        return true;
    }
    
    private static void generateReports() {
        Reports reports = new Reports() {};
        reports.occupayencyRate(hotel);
        reports.peakTimes(hotel);
        reports.averageStayDuration(hotel);
    }
}
