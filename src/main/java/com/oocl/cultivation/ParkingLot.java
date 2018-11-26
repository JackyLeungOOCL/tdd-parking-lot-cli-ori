package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int capacity;
    private Map<ParkingTicket, Car> cars = new HashMap<>();
    private ParkingLotServiceManager manager = null;

    public ParkingLot() {
        this(10);
    }

    public ParkingLot(ParkingLotServiceManager manager) {
        this();
        this.manager = manager;
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        manager = null;
    }

    public ParkingLot(int capacity, ParkingLotServiceManager manager) {
        this(capacity);
        this.manager = manager;
    }

    public int getAvailableParkingPosition() {
        return capacity - cars.size();
    }

    public int getCapacity() {
        return capacity;
    }

    public ParkingTicket generateTicket(Car car) {
        int carPos = getAvailableParkingPosition();
        if (carPos <= 0) {
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
        return car;
    }

    public void changeManager(ParkingLotServiceManager manager) {
        this.manager = manager;
    }
}
