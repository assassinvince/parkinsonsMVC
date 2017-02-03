package net.Parkinsons.parking;

import net.Parkinsons.parking.LegacyCar;

import java.util.Random;
import java.awt.*;
@Deprecated
public class ParkingPassCar extends LegacyCar {
	private static final Color COLOR=Color.cyan;
	
    public ParkingPassCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }



    public Color getColor(){
    	return COLOR;
    }
}
