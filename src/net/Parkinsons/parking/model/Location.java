package net.Parkinsons.parking.model;

public class Location {
    public final int floor;
    public final int row;
    public final int place;

    public Location(int floor, int row, int place) {
        this.floor = floor;
        this.row = row;
        this.place = place;
    } public boolean equals(Object obj) {
        if(obj instanceof Location) {
            Location other = (Location) obj;
            return floor == other.floor && row == other.row && place == other.place;
        }
        else {
            return false;
        }
    }

    /**
     * Return a string of the form floor,row,place.
     * @return A string representation of the location.
     */
    public String toString() {
        return floor + "," + row + "," + place;
    }

    /**
     * Use the 10 bits for each of the floor, row and place
     * values. Except for very big car parks, this should give
     * a unique hash code for each (floor, row, place) tupel.
     * @return A hashcode for the location.
     */
    public int hashCode() {
        return (floor << 20) + (row << 10) + place;
    }

}
