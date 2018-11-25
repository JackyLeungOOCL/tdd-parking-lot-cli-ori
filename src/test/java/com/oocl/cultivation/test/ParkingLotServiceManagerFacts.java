package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
