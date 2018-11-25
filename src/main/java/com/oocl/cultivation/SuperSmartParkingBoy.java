package com.oocl.cultivation;

import java.util.Collections;
import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket park(Car car) {
        Collections.sort(parkingLots, (a, b) -> ((float)a.getAvailableParkingPosition() / (float)a.getCapacity()) > ((float)b.getAvailableParkingPosition() / (float)b.getCapacity()) ? -1 : 1);
        return super.park(car);
    }
}
