package net.Parkinsons.parking.model;

public interface Car {
    boolean hasSubscribtion();
    long getParkTime();
    long getTimeLeft();

    Location getLocation();

    boolean decrementTime(int deltaTime);
}
