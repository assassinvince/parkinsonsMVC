package net.Parkinsons.parking.model;

import net.Parkinsons.parking.view.View;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class Garage {
    /**
     * Add a car to the garage
     * @param car
     * @return
     */
   public abstract Location addCar(Car car);

    /**
     * Remove a car from the garage
     * @param car
     * @return
     */
  public  abstract Location removeCar(Car car);

    /**
     * Get the number of floors in this garage
     * @return
     */
  public  abstract int getNumberOfFloors();

    /**
     * Get the number of rows per floor of the garage
     * @return
     */
  public  abstract int getNumberOfRowsPerFloor();

    /**
     * Get the number of places per row of the garage
     * @return
     */
   public  abstract int getNumberOfPlacesPerRow();

    /**
     * Gets the car at the specified Location
     * @param location Location to look up
     * @return Car at the location or null if the place specified by this location is empty
     */
  public abstract   Car getCarAt(Location location);



    private List<View> views;

    public Garage(){
        views = new ArrayList<View>();
    }

    public void addView(View view){
        views.add(view);
}

    public void notifyViews(){
        for(View v: views) v.updateView();
    }
    /**
     * Update the garage by the specified amount of time
     * @param deltaTime Amount of time passed since the last update
     */
   public abstract void tick(int deltaTime);
}
