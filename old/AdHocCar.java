package net.Parkinsons.parking;

import net.Parkinsons.parking.LegacyCar;

import java.util.Random;
import java.awt.*;
@Deprecated
public class AdHocCar extends LegacyCar {
	private static final Color COLOR=Color.pink;
	
    public AdHocCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }

    public Color getColor(){
    	return COLOR;
    }
}
