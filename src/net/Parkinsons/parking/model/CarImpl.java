package net.Parkinsons.parking.model;


// TODO: Implement
public class CarImpl implements Car {

    private int totalTime;
    private final boolean parkingPass;
    private int timeElapsed;
    private Status status;
    private Location location;

    public CarImpl(int totalTime, boolean parkingPass) {

        this.totalTime = totalTime;
        this.parkingPass = parkingPass;
        timeElapsed = 0;
        status = Status.ENTERING;
    }

    @Override
    public boolean hasSubscribtion() {
        return parkingPass;
    }

    @Override
    public long getParkTime() {
        return timeElapsed;
    }

    @Override
    public long getTimeLeft() {
        return (totalTime - timeElapsed);
    }

    @Override
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     *
     */
    @Override
    public boolean decrementTime(int deltaTime) {
        timeElapsed += deltaTime;
        return totalTime - timeElapsed < 0;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    @Override
    public Status getStatus(){
        return status;
    }


    public enum Status {
        ENTERING, PARKED, EXIT_QUEUE, EXIT_WAIT
    }

    public String toString() {
        String s = "";
        if (hasSubscribtion()) {
            s += "S:";
        } else {
            s += "P:";
        }
        s += ((double) (getTimeLeft() / 1000)/(60*60));
        return s;
    }
}
