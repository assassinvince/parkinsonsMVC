package net.Parkinsons.parking;

import net.Parkinsons.parking.LegacyCar;

import java.util.LinkedList;
import java.util.Queue;

public class CarQueue {
    private Queue<LegacyCar> queue = new LinkedList<>();

    public boolean addCar(LegacyCar legacyCar) {
        return queue.add(legacyCar);
    }

    public LegacyCar removeCar() {
        return queue.poll();
    }

    public int carsInQueue(){
    	return queue.size();
    }
}
