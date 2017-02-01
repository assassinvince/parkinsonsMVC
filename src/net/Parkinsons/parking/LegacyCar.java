package net.Parkinsons.parking;

import net.Parkinsons.parking.model.Location;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
@Deprecated
public abstract class LegacyCar {

    private Location location;
    private int minutesLeft;
    private boolean isPaying;
    private boolean hasToPay;

    /**
     * Constructor for objects of class net.Parkinsons.parking.LegacyCar
     */
    public LegacyCar() {

    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getMinutesLeft() {
        return minutesLeft;
    }

    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }
    
    public boolean getIsPaying() {
        return isPaying;
    }

    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    public boolean getHasToPay() {
        return hasToPay;
    }

    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }

    public void tick() {
        minutesLeft--;
    }

    public abstract Color getColor();
}