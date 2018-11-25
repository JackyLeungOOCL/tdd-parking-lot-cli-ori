package com.oocl.cultivation;

import java.util.Collections;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket park(Car car) {
        Collections.sort(parkingLots, (a, b) -> a.getAvailableParkingPosition() >= b.getAvailableParkingPosition() ? -1 : 1);
        return super.park(car);
    }
}
