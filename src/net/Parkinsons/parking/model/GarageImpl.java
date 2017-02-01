package net.Parkinsons.parking.model;

import java.util.Objects;

// TODO: Implement
public class GarageImpl extends Garage {
    private final int floors;
    private final int rows;
    private final int places;
    private Car[][][] cars;

    public GarageImpl(int floors, int rows, int places) {
        this.floors = floors;
        this.rows = rows;
        this.places = places;
        assert floors > 0 && rows > 0 && places > 0;
        cars = new Car[floors][rows][places];

    }

    private Location findSpot(Car car) {
        for (int i = 0; i < cars.length; i++) {
            for (int j = 0; j < cars[i].length; j++) {
                for (int k = 0; k < cars[i][j].length; k++) {
                    if (Objects.equals(car, cars[i][j][k])) {
                        return new Location(i, j, k);
                    }
                }
            }
        }
        return null;
    }

    private boolean setCarAt(Car car, Location location) {
        if (location != null) {
            cars[location.floor][location.row][location.place] = car;
            return true;
        } else {
            return false;
        }
    }

    public Location addCar(Car car) {
        Location location = findSpot(null);
        setCarAt(car, location);
        return location;
    }

    @Override
    public Location removeCar(Car car) {
        Location location = findSpot(car);
        if (location != null) {
            setCarAt(null, location);
        }
        return location;
    }

    @Override
    public int getNumberOfFloors() {
        return floors;
    }

    @Override
    public int getNumberOfRowsPerFloor() {
        return rows;
    }

    @Override
    public int getNumberOfPlacesPerRow() {
        return places;
    }

    @Override
    public Car getCarAt(Location location) {
        return cars[location.floor][location.row][location.place];

    }

    /**
     * Update the garage by the specified amount of time
     *
     * @param deltaTime Amount of time passed since the last update
     */
    public void tick(int deltaTime) {
        for (Car[][] carArrayArray : cars) {
            for (Car[] carArray : carArrayArray) {
                for (Car car : carArray) {
                    car.decrementTime(deltaTime);
                }
            }
        }




    }
}
