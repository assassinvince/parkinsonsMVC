package net.Parkinsons.parking;

import net.Parkinsons.parking.model.Car;
import net.Parkinsons.parking.model.CarImpl;

import java.util.LinkedList;
import java.util.Queue;

public class CarQueue {
    private Queue<CarImpl> queue = new LinkedList<>();

    public boolean addCar(CarImpl legacyCar) {
        return queue.add(legacyCar);
    }

    public CarImpl removeCar() {
        return queue.poll();
    }

    public int carsInQueue(){
    	return queue.size();
    }
}
