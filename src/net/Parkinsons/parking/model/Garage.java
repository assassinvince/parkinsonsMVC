package net.Parkinsons.parking.model;

public interface Garage {
    /**
     * Add a car to the garage
     * @param car
     * @return
     */
    boolean addCar(Car car);

    /**
     * Remove a car from the garage
     * @param car
     * @return
     */
    boolean removeCar(Car car);

    /**
     * Get the number of floors in this garage
     * @return
     */
    int getNumberOfFloors();

    /**
     * Get the number of rows per floor of the garage
     * @return
     */
    int getNumberOfRowsPerFloor();

    /**
     * Get the number of places per row of the garage
     * @return
     */
    int getNumberOfPlacesPerRow();

    /**
     * Gets the car at the specified Location
     * @param location Location to look up
     * @return Car at the location or null if the place specified by this location is empty
     */
    Car getCarAt(Location location);
}
