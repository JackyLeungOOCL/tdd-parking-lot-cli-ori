package com.oocl.cultivation;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int capacity;
    private Map<ParkingTicket, Car> cars = new HashMap<>();

    public ParkingLot() {
        this(10);
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public int getAvailableParkingPosition() {
        return capacity - cars.size();
    }

    public ParkingTicket generateTicket(Car car) {
        int carPos = getAvailableParkingPosition();
        if (carPos <= 0) {
            // No available parking position
            throw new RuntimeException("The parking lot is full.");
        }

        ParkingTicket ticket = new ParkingTicket();
        cars.put(ticket, car);

        return ticket;
    }

    public Car receiveTicket(ParkingTicket ticket) {
        if (ticket == null) {
            throw new RuntimeException("Please provide your parking ticket.");
        }
        Car car = cars.get(ticket);
        if (car == null) {
            throw new RuntimeException("Unrecognized parking ticket.");
        }
        cars.remove(ticket, car);
        return  car;
//        return cars.get(parkingTicket);
    }


}
