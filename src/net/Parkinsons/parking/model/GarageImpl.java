package net.Parkinsons.parking.model;

import java.util.Objects;

// TODO: Implement
public class GarageImpl implements Garage {
    private Car[][][] Cars;

    public GarageImpl(int floors, int rows, int places) {
        assert floors > 0 && rows > 0 && places > 0;
        Cars = new Car[floors][rows][places];

    }

    private Location findSpot(Car car) {
        for (int i = 0; i < Cars.length; i++) {
            for (int j = 0; j < Cars[i].length; j++) {
                for (int k = 0; k < Cars[i][j].length; k++) {
                    if (Objects.equals(car, Cars[i][j][k])) {
                        return new Location(i, j, k);
                    }
                }
            }
        }
        return null;
    }

    private boolean setCarAt(Car car, Location location) {
        if (location != null) {
            Cars[location.i][location.j][location.k] = car;
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
    private boolean getCarAt(Location location) {
        return null;
    }


}
}
