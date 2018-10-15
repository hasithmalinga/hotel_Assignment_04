/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.tests;

import hotel.credit.CreditCard;
import hotel.credit.CreditCardType;
import hotel.entities.Booking;
import hotel.entities.Guest;
import hotel.entities.Room;
import hotel.entities.RoomType;
import hotel.entities.ServiceCharge;
import hotel.entities.ServiceType;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hasith Malinga
 */
public class TestBug02 {
    public static void main(String[] args) {
        Date arrivalDate = Calendar.getInstance().getTime();
        Guest guest = new Guest("Hasith Malinga", "20, Dandenong VIC", 452455019);
        CreditCard creditcard = new CreditCard(CreditCardType.VISA, 123456, 234);
        
        Room room = new Room(201, RoomType.DOUBLE);
        
        Booking booking = room.book(guest, arrivalDate, 1, 2, creditcard);

        long confNo = booking.getConfirmationNumber();

        System.out.println("Room has been booked. Confirmation No: " + confNo);
        
        booking.checkIn();
        System.out.println("The guest has checked in");
        
        booking.checkOut();
        System.out.println("The guest has checked out");
        
        String roomState = room.isReady() ? "READY" : "OCCUPIED";
        System.out.println("Room State : " + roomState);
        System.out.println();
        System.out.println("Adding service charges after checkout");
        System.out.println();
        System.out.println("bar fridge : $100");
        booking.addServiceCharge(ServiceType.BAR_FRIDGE, 100);
        System.out.println("Service charges added 100");
        System.out.println();
        List<ServiceCharge> serviceCharges = booking.getCharges();
        System.out.println();
        System.out.println("Service charges for the room which doesn't have a booking");
        for (ServiceCharge serviceCharge : serviceCharges) {
            System.out.println(serviceCharge.getType() + " : " + serviceCharge.getCost());
        }
    }
}
