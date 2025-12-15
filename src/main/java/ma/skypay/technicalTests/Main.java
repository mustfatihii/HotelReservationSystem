package ma.skypay.technicalTests;

import ma.skypay.technicalTests.enums.RoomType;
import ma.skypay.technicalTests.services.Service;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();

        //----------------------Creating rooms-----------------------

        service.setRoom(1, RoomType.STANDARD,1000);
        service.setRoom(2,RoomType.JUNIOR,2000);
        service.setRoom(3,RoomType.MASTER,3000);

        //----------------------Creating Users-------------------------

        service.setUser(1,5000);
        service.setUser(2,10000);

        //----------------------Bookings-------------------------------

        service.bookRoom(1,2, LocalDate.of(2026,6,30),LocalDate.of(2026,7,7));
        service.bookRoom(1,2, LocalDate.of(2026,7,7), LocalDate.of(2026,6,30));

        service.bookRoom(2,1, LocalDate.of(2026,7,7), LocalDate.of(2026,7,9));
        service.bookRoom(2,3, LocalDate.of(2026,7,7), LocalDate.of(2026,7,8));

        //------------------------Modifying Room Data---------------------

        service.setRoom(1,RoomType.MASTER,10000);

        //-----------------------Printing All Users-----------------------

        service.printAllUsers();

        //----------------------Printing All Rooms And Bookings------------
        service.printAll();
    }
}