package ma.skypay.technicalTests.entities;

import java.time.LocalDate;

public class Booking {
    private Room room;
    private User user;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public Booking(Room room, User user, LocalDate checkIn, LocalDate checkOut) {
        this.room = room;
        this.user = user;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "room=" + room +
                ", user=" + user +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                '}';
    }
}
