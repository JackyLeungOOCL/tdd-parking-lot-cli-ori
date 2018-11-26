package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {

//    private final ParkingLot parkingLot;
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
        // TODO: Please implement the method
        return park(car, 0);
//        try {
//            int parkingLotNumber = 0;
//            ParkingTicket ticket = parkingLot.generateTicket(car);
//            lastErrorMessage = null;
//            return ticket;
//        } catch (RuntimeException e) {
//            lastErrorMessage = e.getMessage();
//            if (lastErrorMessage == "The parking lot is full.") {
//
//            }
//            return null;
//        }
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
//            return null;
            if (lastErrorMessage == "The parking lot is full.") {
                return park(car, ++parkingLotNumber);
            }
            return null;
        }
    }

    public Car fetch(ParkingTicket ticket) {
        // TODO: Please implement the method
        return fetch(ticket, 0);
//        try {
//            Car car = parkingLot.receiveTicket(ticket);
//            lastErrorMessage = null;
//            return car;
//        } catch (RuntimeException e) {
//            lastErrorMessage = e.getMessage();
//            return null;
//        }
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
            if (lastErrorMessage == "Unrecognized parking ticket.") {
                return fetch(ticket, ++parkingLotNumber);
            }
            return null;
        }
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
