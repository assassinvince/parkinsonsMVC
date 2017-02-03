package net.Parkinsons.parking.controller;

import net.Parkinsons.parking.model.Car;
import net.Parkinsons.parking.model.CarImpl;
import net.Parkinsons.parking.model.Garage;
import net.Parkinsons.parking.model.GarageImpl;

import javax.swing.*;
import java.util.Random;

public class GarageController{
    protected Garage garage;
    private boolean running;
    private int multiplier;
    private Random random;

    public GarageController(Garage garage){
        this.garage = garage;
        multiplier = 60*30;
        random = new Random();
    }

    public void start() throws InterruptedException {
        running = true;
        while(running){
            Thread.sleep(1000);
            garage.tick(1000*multiplier);
            if (random.nextInt(128)<64) {
                garage.addCar(new CarImpl(1000*60*60*(1+random.nextInt(6)), random.nextBoolean()));
            }
            System.out.println(garage);
        }
    }

    public void stop(){
        running = false;
    }

    public static void main(String[] args) throws InterruptedException {
        new GarageController(new GarageImpl(5,5,1)).start();
    }
}
