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
            ParkingTicket ticket = parkingLot.generateTicket(car);
            lastErrorMessage = null;
            return ticket;
        } catch (RuntimeException e) {
            lastErrorMessage = e.getMessage();
            return null;
        }
    }

    public Car fetch(ParkingTicket ticket) {
        // TODO: Please implement the method
        try {
            Car car = parkingLot.receiveTicket(ticket);
            lastErrorMessage = null;
            return car;
        } catch (RuntimeException e) {
            lastErrorMessage = e.getMessage();
            return null;
        }
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
