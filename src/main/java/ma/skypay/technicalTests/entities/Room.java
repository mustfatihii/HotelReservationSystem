package ma.skypay.technicalTests.entities;

import ma.skypay.technicalTests.enums.RoomType;

import java.util.Objects;

public class Room {
    private int id;
    private RoomType roomType;
    private int pricePerNight;

    public Room(int id) {
        this.id = id;
    }

    public Room(int id, RoomType roomType, int pricePerNight) {
        this.id = id;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomType=" + roomType +
                ", pricePerNight=" + pricePerNight +
                '}';
    }
}
