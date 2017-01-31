package net.Parkinsons.parking.view;
import net.Parkinsons.parking.model.Garage;

import javax.swing.*;


/**
 * Created by vince on 31-1-2017.
 */

public class View extends JPanel {
    protected Garage model;

    public View(Garage model){
        this.model = model;
        model.addView(this);
    }

    public Garage getModel(){
        return model;
    }

    public void updateView(){
        repaint();
    }
}
