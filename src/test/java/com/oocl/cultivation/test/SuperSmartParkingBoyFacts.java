package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.SuperSmartParkingBoy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuperSmartParkingBoyFacts {
    @Test
    void should_park_cars_to_parking_lot_with_larger_position_rate() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(4);
        ParkingLot parkingLot3 = new ParkingLot(6);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        parkingLots.add(parkingLot3);

        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        superSmartParkingBoy.park(new Car());
        assertEquals(parkingLot1.getAvailableParkingPosition(), 1);
        assertEquals(parkingLot2.getAvailableParkingPosition(), 4);
        assertEquals(parkingLot3.getAvailableParkingPosition(), 6);

        superSmartParkingBoy.park(new Car());
        assertEquals(parkingLot1.getAvailableParkingPosition(), 1);
        assertEquals(parkingLot2.getAvailableParkingPosition(), 3);
        assertEquals(parkingLot3.getAvailableParkingPosition(), 6);

        superSmartParkingBoy.park(new Car());
        assertEquals(parkingLot1.getAvailableParkingPosition(), 1);
        assertEquals(parkingLot2.getAvailableParkingPosition(), 3);
        assertEquals(parkingLot3.getAvailableParkingPosition(), 5);

    }
}
