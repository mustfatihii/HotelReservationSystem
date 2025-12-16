package ma.skypay.technicalTests.services;

import ma.skypay.technicalTests.entities.Booking;
import ma.skypay.technicalTests.entities.Room;
import ma.skypay.technicalTests.entities.User;
import ma.skypay.technicalTests.enums.RoomType;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Service {

    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Booking> bookings = new ArrayList<>();

    public void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight){
        if(roomExists(roomNumber)) {
            for (Room room : rooms) {
                if (room.getId() == roomNumber) {
                    room.setRoomType(roomType);
                    room.setPricePerNight(roomPricePerNight);
                    break;
                }
            }
        }else{
            rooms.add(new Room(roomNumber,roomType,roomPricePerNight));
        }
    }

    public void bookRoom(int userId, int roomNumber, LocalDate checkIn, LocalDate checkOut){
        if(userExists(userId) &&
           roomExists(roomNumber) &&
           isValidDates(checkIn,checkOut) &&
           canUserPay(userId,roomNumber,checkIn,checkOut) &&
           isRoomAvailable(roomNumber,checkIn,checkOut)){
            updateUserBalance(userId,roomNumber, checkIn,checkOut);
            bookings.add(
                    new Booking(
                            getRoom(roomNumber),
                            getUser(userId),
                            checkIn,
                            checkOut
                    )
            );
            System.out.println("Booking Done successfully");
        }else {
            System.out.println("Invalid Data");
        }
    }

    private void updateUserBalance(int userId, int roomId, LocalDate checkIn, LocalDate checkOut) {
        for (User user : users){
            if(user.getId()==userId){
                user.setBalance(
                        user.getBalance()-getRoom(roomId).getPricePerNight()*getStayDuration(checkIn,checkOut)
                        );
                break;
            }
        }
    }

    private int getStayDuration(LocalDate checkIn, LocalDate checkOut){
        return (int) ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    public void printAll(){
        printHeader("Rooms");
        for (int i = rooms.size() - 1; i >= 0; i--) {
            System.out.println(rooms.get(i));
        }
        printFooter();

        printHeader("Bookings");
        for (int i = bookings.size() - 1; i >= 0; i--) {
            System.out.println(bookings.get(i));
        }
        printFooter();
    }

    public void setUser(int userId, int balance){
        if(userExists(userId)){
            for (User user : users){
                if(user.getId()==userId){
                    user.setBalance(balance);
                    break;
                }
            }
        }else{
            users.add(new User(userId,balance));
        }
    }

    public void printAllUsers(){
        printHeader("Users");
        for (int i = users.size() - 1; i >= 0; i--) {
            System.out.println(users.get(i));
        }
        printFooter();
    }

    private boolean userExists(int userId){
        return users.contains(new User(userId));
    }

    private boolean roomExists(int roomId){
        return rooms.contains(new Room(roomId));
    }

    private boolean isValidDates(LocalDate checkIn,LocalDate checkOut){
        return checkOut.isAfter(checkIn);
    }

    private boolean isRoomAvailable(int roomId, LocalDate checkIn, LocalDate checkOut){
        for (Booking booking : bookings){
            if(booking.getRoom().getId()== roomId &&
               (
                (checkIn.isAfter(booking.getCheckIn().minusDays(1)) && checkIn.isBefore(booking.getCheckOut()))
                || (checkOut.isAfter(booking.getCheckIn()) && checkOut.isBefore(booking.getCheckOut()))
                || (checkIn.isBefore(booking.getCheckIn().plusDays(1)) && checkOut.isAfter(booking.getCheckOut().minusDays(1)))
               )
            ){
                return false;
            }

        }
        return true;
    }

    private boolean canUserPay(int userId,int roomId,LocalDate checkIn,LocalDate checkOut){
        return getUser(userId).getBalance() >= getRoom(roomId).getPricePerNight() * getStayDuration(checkIn,checkOut);
    }

    private User getUser(int userId){
        return users.stream()
                .filter(user -> user.getId() == userId)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found with id: " + userId)
                );
    }

    private Room getRoom(int roomId){
        return rooms.stream()
                .filter(room -> room.getId() == roomId)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Room not found with id: " + roomId)
                );
    }

    private void printHeader(String title){
        String toPrint = "--------------------------------";
        System.out.println(toPrint+title+toPrint);
    }

    private void printFooter(){
        String toPrint = "-----------------------------------";
        System.out.println(toPrint+toPrint);
    }
}
