package net.Parkinsons.parking.model;

// TODO: Implement


import java.util.Objects;

public class GarageImpl implements Garage {
    private Car[][][] Cars;

    private Location findSpot (Car Car) {
        for (int i = 0; i < Cars.length; i++) {
            for (int j = 0; j <Cars[i].length ; j++) {
                for (int k = 0; k < Cars[i][j].length; k++) {
                   if (Objects.equals(Car, Cars[i][j][k])){
                       return new Location(i, j, k);
                   }
                }
            }
        }
        return null;
    }

    @Override
    public boolean addCar(Car car) {


            return false;
    }

    @Override
    public boolean removeCar(Car car) {
        return false;
    }

    @Override
    public int getNumberOfFloors() {
        return 0;
    }

    @Override
    public int getNumberOfRowsPerFloor() {
        return 0;
    }

    @Override
    public int getNumberOfPlacesPerRow() {
        return 0;
    }

    @Override
    public Car getCarAt(Location location) {
        return null;
    }

    @Override
    public Location  addCar (Car car ) {
        Location Location = findSpot  (null);
        setCarAt(car, Location);
        return Location;
    }
}
