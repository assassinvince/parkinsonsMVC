package net.Parkinsons.parking.controller;

import net.Parkinsons.parking.model.Garage;

import javax.swing.*;

public class GarageController extends JPanel{
    protected Garage garage;

    public GarageController(Garage garage){
        this.garage = garage;}

    /**
     * Update the garage by the specified amount of time
     * @param deltaTime Amount of time passed since the last update
     */
    public void tick(int deltaTime) {

    }
}
