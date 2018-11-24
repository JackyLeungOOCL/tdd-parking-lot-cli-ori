package com.oocl.cultivation;

public class ParkingBoy {

    private final ParkingLot parkingLot;
    private String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        // TODO: Please implement the method
        try {
            return parkingLot.generateTicket(car);
        } catch (RuntimeException e) {
            lastErrorMessage = e.getMessage();
            return null;
        }
    }

    public Car fetch(ParkingTicket ticket) {
        // TODO: Please implement the method
        return parkingLot.receiveTicket(ticket);
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
