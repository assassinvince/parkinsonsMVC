import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulatorView extends JFrame implements ActionListener {
    private CarParkView carParkView;
    private Simulator simulator;
    private CarQueue carQueue;
    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;

    // -- BEPAALD BREEDTE & HOOGTE VAN CARPARKVIEW -- \\
    private int parkViewWidth;
    private int parkViewHeight;
    private int frameWidth;
    private int frameHeight;

    private LegacyCar[][][] legacyCars;

    public SimulatorView(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfOpenSpots =numberOfFloors*numberOfRows*numberOfPlaces;

        parkViewWidth = 900;
        parkViewHeight = 400;
        frameWidth = parkViewWidth;
        frameHeight = parkViewHeight + 100;
        legacyCars = new LegacyCar[numberOfFloors][numberOfRows][numberOfPlaces];
        setEssentials();

        carParkView = new CarParkView();


        Container contentPane = getContentPane();
        contentPane.add(carParkView, BorderLayout.CENTER);
        carParkView.setBounds(0, 0, parkViewWidth, parkViewHeight ); // TODO: ERVOOR ZORGEN DAT DE GROOTE ZICH AANPAST OP DE HOEVEELHEID VERDIEPINGEN / FLOORS / PLACES.
        pack();
        addControlPanel(); // VOEGT DE KNOPPEN TOE
        addSpeedController(); // VOEGT SNELHEIDS REGELAARS TOE
        // addStats(); // VOEGT STATUSSEN TOE AAN DE BOVENKANT

        updateView();
    }

    // ---- CONTROLLER section ---- //


    // ---- end CONTROLLER section ---- //



    // ---- MODEL section ----//


    // ---- end MODEL section ---- //



    // ---- VIEW  section ---- //
    private JLabel backGround;
    private JButton resumeButton;
    protected JLabel statusLabel;
    protected JLabel tickCounter;
    protected static JLabel speedIndicator;
    protected String tickAmountString;
    protected static int currentSpeedStep;
    protected static String currentSpeed;

    public void setEssentials() { // Bepaald de window size gebasseerd op de grote van de carParkView + 100.
        backGround = new JLabel();
        setTitle("Parking Simulator v0.1");
        setResizable(false);
        add(backGround);
        backGround.setBounds(0, 0, frameWidth, frameHeight);
        setLayout(null);
        this.setPreferredSize(new Dimension(frameWidth, frameHeight));
        setVisible(true);
    }

    public void addControlPanel() { // Voegt de control panel toe die zicht schaalt aan de grote van het interface
        tickAmountString = Integer.toString(Simulator.getTicks());

        int panelSizeHeight = 150;

        int panelHeightPos = parkViewHeight;
        int panelWidthPos = 0;



        JPanel buttonPanel = new JPanel();
        buttonPanel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBounds(panelWidthPos, panelHeightPos, 800, panelSizeHeight);


        JButton pauseButton = new JButton("Stop");
        pauseButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            stopButtonPressed();
        } } );

        JButton resumeButton = new JButton("Resume");
        resumeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resumeButtonPressed();
            } } );

        statusLabel = new JLabel("");
        statusLabel.setText("Actief!");


        tickCounter = new JLabel(tickAmountString);

        buttonPanel.add(statusLabel);
        buttonPanel.add(pauseButton);
        buttonPanel.add(resumeButton);
        buttonPanel.add(tickCounter);

        add(buttonPanel);
    }

    public void addSpeedController() {
        int speedPosX = parkViewWidth -275;
        int speedPosY = parkViewHeight;
        currentSpeedStep = 3;
        currentSpeed = "1x";

        JPanel speedPanel = new JPanel();
        speedPanel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        speedPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        speedPanel.setBounds(speedPosX, speedPosY, 250, 150);

        JButton speedUp = new JButton(">>");
        speedUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                speedUpButtonPressed();
            } } );

        JButton speedDown = new JButton("<<");
        speedDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                speedDownButtonPressed();
            } } );

        speedIndicator = new JLabel("Huidige snelheid: " + currentSpeed);

        speedPanel.add(speedDown);
        speedPanel.add(speedIndicator);
        speedPanel.add(speedUp);

        add(speedPanel);
    }

    public void addStats() {

        JPanel statusIndicators = new JPanel();

        statusIndicators.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        statusIndicators.setLayout(new FlowLayout(FlowLayout.LEFT));
        statusIndicators.setBounds(700, 0, 200, 600);
        statusIndicators.setBackground(Color.WHITE);
        add(statusIndicators);
        }

    // ---- end VIEW section ---- //

    // ---- CONTROLLER section ---- //


    public void resumeButtonPressed() {
        SimulatorModel.startTimer();
        setStatus("Actief!");
        statusLabel.setForeground(new java.awt.Color(0, 155, 0));
    }

    public void stopButtonPressed() {
        SimulatorModel.stopTimer();
        setStatus("Gepauseerd!");
        statusLabel.setForeground(new java.awt.Color(255, 0, 0));
    }

    public void setStatus(String string) {
        statusLabel.setText("Status: " + string);
    }

    public void updateTicks() {
        tickAmountString = Integer.toString(Simulator.getTicks());
        tickCounter.setText("Aantal ticks: " + tickAmountString);
    }
    public void speedDownButtonPressed() {
            currentSpeedStep--;
            SimulatorModel.checkSpeed();
        if (currentSpeedStep == 0) {
            currentSpeedStep = currentSpeedStep +1;
        }
    }

    public void speedUpButtonPressed() {
            currentSpeedStep++;
            SimulatorModel.checkSpeed();
            if (currentSpeedStep == 8) {
                currentSpeedStep = currentSpeedStep -1;
        }
    }

    // ---- end CONTROLLER section ---- \\

    public void updateView() {
        carParkView.updateView();
    }
    
	public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public int getNumberOfOpenSpots(){
    	return numberOfOpenSpots;
    }
    
    public LegacyCar getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return legacyCars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    public boolean setCarAt(Location location, LegacyCar legacyCar) {
        if (!locationIsValid(location)) {
            return false;
        }
        LegacyCar oldLegacyCar = getCarAt(location);
        if (oldLegacyCar == null) {
            legacyCars[location.getFloor()][location.getRow()][location.getPlace()] = legacyCar;
            legacyCar.setLocation(location);
            numberOfOpenSpots--;
            return true;
        }
        return false;
    }

    public LegacyCar removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        LegacyCar legacyCar = getCarAt(location);
        if (legacyCar == null) {
            return null;
        }
        legacyCars[location.getFloor()][location.getRow()][location.getPlace()] = null;
        legacyCar.setLocation(null);
        numberOfOpenSpots++;
        return legacyCar;
    }

    public Location getFirstFreeLocation() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }

    public LegacyCar getFirstLeavingCar() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    LegacyCar legacyCar = getCarAt(location);
                    if (legacyCar != null && legacyCar.getMinutesLeft() <= 0 && !legacyCar.getIsPaying()) {
                        return legacyCar;
                    }
                }
            }
        }
        return null;
    }

    public void tick() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    LegacyCar legacyCar = getCarAt(location);
                    if (legacyCar != null) {
                        legacyCar.tick();
                    }
                }
            }
        }
    }

    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private class CarParkView extends JPanel {

        private Dimension size;
        private Image carParkImage;

        /**
         * Constructor for objects of class CarPark
         */
        public CarParkView() {
            size = new Dimension(0, 0);
        }

        private JLabel Status;

        public Dimension getPreferredSize() {
            return new Dimension(800, 500);
        }



        /**
         * Overriden. The car park view component needs to be redisplayed. Copy the
         * internal image to screen.
         */
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
            for(int floor = 0; floor < getNumberOfFloors(); floor++) {
                for(int row = 0; row < getNumberOfRows(); row++) {
                    for(int place = 0; place < getNumberOfPlaces(); place++) {
                        Location location = new Location(floor, row, place);
                        LegacyCar legacyCar = getCarAt(location);
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
                    location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                    60 + location.getPlace() * 10,
                    20 - 1,
                    10 - 1); // TODO use dynamic size or constants
        }
    }

}
