package net.Parkinsons.parking.model;

import javax.swing.*;
import net.Parkinsons.parking.model.Garage;
import net.Parkinsons.parking.view.View;
import net.Parkinsons.parking.controller.GarageController;

/**
 * Created by vince on 31-1-2017.
 */
public class Main {
    private  Garage garage;
    private View view1;
    private View view2;
    private  GarageController controller;

    public Main(){
        garage = new GarageImpl( 5, 10,   20);
        controller = new GarageController(garage);
    }
}