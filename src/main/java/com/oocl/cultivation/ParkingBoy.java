package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    protected List<ParkingLot> parkingLots;
    protected String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
    }

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        return park(car, 0);
    }

    public ParkingTicket park(Car car, int parkingLotNumber) {
        try {
            if (parkingLotNumber >= parkingLots.size()) {
                return null;
            }
            ParkingTicket ticket = parkingLots.get(parkingLotNumber).generateTicket(car);
            lastErrorMessage = null;
            return ticket;
        } catch (RuntimeException e) {
            lastErrorMessage = e.getMessage();
            if (isParkingLotFullError()) {
                return park(car, ++parkingLotNumber);
            }
            return null;
        }
    }

    private boolean isParkingLotFullError() {
        return lastErrorMessage.equals("The parking lot is full.");
    }

    public Car fetch(ParkingTicket ticket) {
        return fetch(ticket, 0);
    }

    public Car fetch(ParkingTicket ticket, int parkingLotNumber) {
        try {
            if (parkingLotNumber >= parkingLots.size()) {
                return null;
            }
            Car car = parkingLots.get(parkingLotNumber).receiveTicket(ticket);
            lastErrorMessage = null;
            return car;
        } catch (RuntimeException e) {
            lastErrorMessage = e.getMessage();
            if (isUnrecognizedTicketError()) {
                return fetch(ticket, ++parkingLotNumber);
            }
            return null;
        }
    }

    private boolean isUnrecognizedTicketError() {
        return lastErrorMessage.equals("Unrecognized parking ticket.");
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
