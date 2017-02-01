package net.Parkinsons.parking;

import net.Parkinsons.parking.model.Location;

import javax.swing.*;
import java.awt.*;

/**
 * Created by wesle on 27-1-2017.
 */
class CarParkView extends JPanel {

    private Dimension size;
    private Image carParkImage;
    private SimulatorView simulatorView;


    /**
     * Constructor for objects of class CarPark
     */

    private JPanel panel;


    public CarParkView() {
        size = new Dimension(0, 0);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 500);
    }


    public void paintComponent(Graphics g) {
        if (carParkImage == null) {
            return;
        }

        Dimension currentSize = getSize();
        if (size.equals(currentSize)) {
            g.drawImage(carParkImage, 0, 0, null);
        }
        else {
            // Rescale the previous image.
            g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
        }
    }

    public void updateView() {
        // Create a new car park image if the size has changed.
        if (!size.equals(getSize())) {
            size = getSize();
            carParkImage = createImage(size.width, size.height);
        }
        Graphics graphics = carParkImage.getGraphics();
        for(int floor = 0; floor < simulatorView.getNumberOfFloors(); floor++) {
            for(int row = 0; row < simulatorView.getNumberOfRows(); row++) {
                for(int place = 0; place < simulatorView.getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    LegacyCar legacyCar = simulatorView.getCarAt(location);
                    Color color = legacyCar == null ? Color.white : legacyCar.getColor();
                    drawPlace(graphics, location, color);
                }
            }
        }
        repaint();
    }

    /**
     * Paint a place on this car park view in a given color.
     */
    private void drawPlace(Graphics graphics, Location location, Color color) {
        graphics.setColor(color);
        graphics.fillRect(
                location.floor * 260 + (1 + (int)Math.floor(location.row * 0.5)) * 75 + (location.row % 2) * 20,
                60 + location.place * 10,
                20 - 1,
                10 - 1); // TODO use dynamic size or constants
    }
}
