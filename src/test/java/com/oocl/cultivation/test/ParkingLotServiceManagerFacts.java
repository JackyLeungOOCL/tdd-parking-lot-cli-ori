package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotServiceManagerFacts {
    @Test
    void should_add_parking_boys_to_management_list() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotServiceManager manager = new ParkingLotServiceManager(parkingLot);

        assertEquals(manager.getManagementList().size(), 0);
        manager.addParkingBoy(new ParkingBoy(parkingLot));
        assertEquals(manager.getManagementList().size(), 1);
    }

    @Test
    void should_specify_a_parking_boy_to_park_car() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingLotServiceManager manager = new ParkingLotServiceManager(parkingLot1);
        ParkingBoy parkingBoy1 = new ParkingBoy(parkingLot1);
        ParkingBoy parkingBoy2 = new ParkingBoy(parkingLot2);
        manager.addParkingBoy(parkingBoy1);

        assertDoesNotThrow(() -> manager.park(new Car(), parkingBoy1));
        Throwable exception = assertThrows(RuntimeException.class, () -> manager.park(new Car(), parkingBoy2));
        assertEquals("Parking boy is not managed by this manager.", exception.getMessage());
    }

    @Test
    void should_specify_a_parking_boy_to_fetch_car() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingLotServiceManager manager = new ParkingLotServiceManager(parkingLot1);
        ParkingBoy parkingBoy1 = new ParkingBoy(parkingLot1);
        ParkingBoy parkingBoy2 = new ParkingBoy(parkingLot2);
        manager.addParkingBoy(parkingBoy1);
        ParkingTicket ticket = manager.park(new Car(), parkingBoy1);

        Throwable exception = assertThrows(RuntimeException.class, () -> manager.fetch(ticket, parkingBoy2));
        assertEquals("Parking boy is not managed by this manager.", exception.getMessage());
    }


    // Should park cars as a standard parking boy

    @Test
    void should_park_a_car_to_a_parking_lot_and_get_it_back() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotServiceManager manager = new ParkingLotServiceManager(parkingLot);
        Car car = new Car();

        ParkingTicket ticket = manager.park(car);
        Car fetched = manager.fetch(ticket);

        assertSame(fetched, car);
    }

    @Test
    void should_park_multiple_cars_to_a_parking_lot_and_get_them_back() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotServiceManager manager = new ParkingLotServiceManager(parkingLot);
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingTicket firstTicket = manager.park(firstCar);
        ParkingTicket secondTicket = manager.park(secondCar);

        Car fetchedByFirstTicket = manager.fetch(firstTicket);
        Car fetchedBySecondTicket = manager.fetch(secondTicket);

        assertSame(firstCar, fetchedByFirstTicket);
        assertSame(secondCar, fetchedBySecondTicket);
    }

    @Test
    void should_park_cars_sequentially() {
        final int capacity = 1;
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(capacity);
        ParkingLot parkingLot2 = new ParkingLot(capacity);
        ParkingLot parkingLot3 = new ParkingLot();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        parkingLots.add(parkingLot3);
        ParkingLotServiceManager manager = new ParkingLotServiceManager(parkingLots);

        manager.park(new Car());
        assertEquals(parkingLot1.getAvailableParkingPosition(), 0);
        assertEquals(parkingLot2.getAvailableParkingPosition(), 1);
        assertEquals(parkingLot3.getAvailableParkingPosition(), 10);
        manager.park(new Car());
        assertEquals(parkingLot1.getAvailableParkingPosition(), 0);
        assertEquals(parkingLot2.getAvailableParkingPosition(), 0);
        assertEquals(parkingLot3.getAvailableParkingPosition(), 10);
        manager.park(new Car());
        assertEquals(parkingLot1.getAvailableParkingPosition(), 0);
        assertEquals(parkingLot2.getAvailableParkingPosition(), 0);
        assertEquals(parkingLot3.getAvailableParkingPosition(), 9);
    }

    // Should display errors from parking boy

    @Test
    void should_query_message_once_the_ticket_is_wrong() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotServiceManager manager = new ParkingLotServiceManager(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        manager.addParkingBoy(parkingBoy);
        ParkingTicket wrongTicket = new ParkingTicket();

        manager.fetch(wrongTicket, parkingBoy);
        String message = parkingBoy.getLastErrorMessage();

        assertEquals("Unrecognized parking ticket.", message);
    }

    @Test
    void should_query_message_once_ticket_is_not_provided() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotServiceManager manager = new ParkingLotServiceManager(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        manager.addParkingBoy(parkingBoy);

        manager.fetch(null, parkingBoy);

        assertEquals(
                "Please provide your parking ticket.",
                manager.getLastErrorMessage());
    }

    @Test
    void should_query_error_message_for_used_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotServiceManager manager = new ParkingLotServiceManager(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        manager.addParkingBoy(parkingBoy);
        Car car = new Car();

        ParkingTicket ticket = manager.park(car, parkingBoy);
        parkingBoy.fetch(ticket);
        parkingBoy.fetch(ticket);

        assertEquals(
                "Unrecognized parking ticket.",
                parkingBoy.getLastErrorMessage()
        );
    }

    @Test
    void should_get_message_if_there_is_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot = new ParkingLot(capacity);
        ParkingLotServiceManager manager = new ParkingLotServiceManager(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        manager.addParkingBoy(parkingBoy);

        manager.park(new Car(), parkingBoy);
        manager.park(new Car(), parkingBoy);

        assertEquals("The parking lot is full.", parkingBoy.getLastErrorMessage());
    }
}
