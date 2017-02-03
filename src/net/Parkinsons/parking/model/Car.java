package net.Parkinsons.parking.model;

public interface Car {
    boolean hasSubscribtion();
    long getParkTime();
    CarImpl.Status getStatus();
    long getTimeLeft();
    Location getLocation();


    boolean decrementTime(int deltaTime);


}
