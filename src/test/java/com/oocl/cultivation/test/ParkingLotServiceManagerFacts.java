package com.oocl.cultivation.test;

import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingLotServiceManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingLotServiceManagerFacts {
    @Test
    void should_add_parking_boys_to_management_list() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotServiceManager manager = new ParkingLotServiceManager(parkingLot);

        assertEquals(manager.getManagementList().size(), 0);
        manager.addParkingBoy(new ParkingBoy(parkingLot));
        assertEquals(manager.getManagementList().size(), 1);
    }
}
