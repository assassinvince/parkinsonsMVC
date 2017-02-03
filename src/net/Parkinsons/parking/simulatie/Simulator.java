package net.Parkinsons.parking.simulatie;

import net.Parkinsons.parking.model.Garage;

/**
 * Created by Jille on 03/02/2017.
 */
public abstract class Simulator {

    public abstract void tick();

    public abstract Garage getGarage();

    public abstract void setGarage(Garage garage);

    public abstract int queueLength();

    public abstract void reset();

    public abstract boolean canCarExit();

    public abstract void setCanCarExit(boolean canCarExit);

    public abstract int getTickAmount();
}
