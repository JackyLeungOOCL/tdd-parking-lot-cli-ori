package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotServiceManager extends ParkingBoy {
    private List<ParkingBoy> managementList = new ArrayList<>();

    public ParkingLotServiceManager(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public ParkingLotServiceManager(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public void addParkingBoy(ParkingBoy parkingBoy) {
        managementList.add(parkingBoy);
    }

    public ParkingTicket park(Car car, ParkingBoy parkingBoy) {
        if (managementList.indexOf(parkingBoy) < 0) {
            throw new RuntimeException("Parking boy is not managed by this manager.");
        }
        return parkingBoy.park(car);
    }

    public Car fetch(ParkingTicket ticket, ParkingBoy parkingBoy) {
        if (managementList.indexOf(parkingBoy) < 0) {
            throw new RuntimeException("Parking boy is not managed by this manager.");
        }
        return parkingBoy.fetch(ticket);
    }

    public List<ParkingBoy> getManagementList() {
        return managementList;
    }
}
