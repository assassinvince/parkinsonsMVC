package net.Parkinsons.parking.model;

// TODO: Implement
public class CarImpl implements Car {

    private int totalTime;
    private final boolean parkingPass;

    public CarImpl(int totalTime, boolean parkingPass) {

        this.totalTime = totalTime;
        this.parkingPass = parkingPass;
    }


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

    @Override
    public boolean decrementTime(int deltaTime) {
        totalTime -= deltaTime;
        return totalTime < 0;
    }
}
