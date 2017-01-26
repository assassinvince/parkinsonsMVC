package net.Parkinsons.parking.model;

// TODO: Implement
public class CarImpl implements Car {
    @Override
    public boolean hasSubscribtion() {
        return false;
    }

    @Override
    public long getParkTime() {
        return 0;
    }

    @Override
    public long getTimeLeft() {
        return 0;
    }

    @Override
    public Location getLocation() {
        return null;
    }
}
