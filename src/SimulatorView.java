import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.text.NumberFormat;
import java.awt.event.KeyEvent;

import static java.lang.Boolean.TRUE;

public class SimulatorView extends javax.swing.JFrame implements ActionListener {
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
        int totalSize = numberOfFloors + numberOfRows;
        
        if (totalSize < 12) {
            parkViewWidth = 900;
        } else {
            parkViewWidth = numberOfFloors * numberOfRows * 33;
        }
        
        if (numberOfPlaces < 25) {
            parkViewHeight = 400;  
        } else {
            parkViewHeight = numberOfPlaces * 13;
        }
        
        System.out.println("Floor = " + numberOfFloors +
                            "\n rows = " + numberOfRows +
                            "\n places = " + numberOfPlaces);
        
        frameWidth = parkViewWidth + 165;
        frameHeight = parkViewHeight + 70;
        legacyCars = new LegacyCar[numberOfFloors][numberOfRows][numberOfPlaces];
        setEssentials();

        carParkView = new CarParkView();


        Container contentPane = getContentPane();
        contentPane.add(carParkView, BorderLayout.CENTER);
        carParkView.setBounds(0, 0, parkViewWidth, parkViewHeight ); // TODO: ERVOOR ZORGEN DAT DE GROOTE ZICH AANPAST OP DE HOEVEELHEID VERDIEPINGEN / FLOORS / PLACES.
        pack();
        addControlPanel(); // VOEGT DE KNOPPEN TOE
        addSpeedController(); // VOEGT SNELHEIDS REGELAARS TOE
        addStats(); // VOEGT STATUSSEN TOE AAN DE BOVENKANT
        updateView();
    }

    // ---- CONTROLLER section ---- //


    // ---- end CONTROLLER section ---- //



    // ---- MODEL section ----//

    // ZIE SIMULATORMODEL \\

    // ---- end MODEL section ---- //



    // ---- VIEW  section ---- //
    protected JLabel statusLabel;
    private JLabel ticksToReach;
    private JButton statusChanger;
    private JFormattedTextField customTickAmount;
    private JButton executeTicks;
    private int ticksToReachInt = 100;
    private Boolean customTicksActive = false;
    private Boolean whichStatus = false;
    protected JLabel tickCounter;
    protected static JLabel parkedCars;
    protected static JLabel time;
    protected static JLabel speedIndicator;
    protected String tickAmountString;
    protected static int currentSpeedStep;
    protected static String currentSpeed;

    private void setEssentials() { // Bepaald de window size gebasseerd op de grote van de carParkView + 100.
        setTitle("Parking Simulator v0.1");
        setResizable(false);
        setBackground(new Color(112, 112, 112));
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


        statusChanger = new JButton("Pauzeer");
        statusChanger.addActionListener(e -> statusChangerButtonPressed());
        statusChanger.setBackground(new Color(92, 92, 92));
        statusChanger.setForeground(new Color( 255, 255, 255));

        statusLabel = new JLabel("Status: Actief   ");
        statusLabel.setForeground(new Color(0, 155, 0));

        executeTicks = new JButton("100 tick");
        executeTicks.addActionListener(e -> hundredTicksButtonPressed());
        executeTicks.setBackground(new Color(92, 92, 92));
        executeTicks.setForeground(new Color( 255, 255, 255));

        ticksToReach = new JLabel(tickAmountString);
        tickCounter = new JLabel(tickAmountString);

        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(1);
        formatter.setMaximum(100000);
        formatter.setAllowsInvalid(false);
        // If you want the value to be committed on each keystroke instead of focus lost
        formatter.setCommitsOnValidEdit(false);
        customTickAmount = new JFormattedTextField(formatter);
        customTickAmount.setColumns(7);
        customTickAmount.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                customTickAmountKeyTyped(evt);
            }
        });;
        buttonPanel.add(statusLabel);
        buttonPanel.add(statusChanger);
        buttonPanel.add(tickCounter);
        buttonPanel.add(customTickAmount);
        buttonPanel.add(executeTicks);
        buttonPanel.add(ticksToReach); ticksToReach.setVisible(false);

        add(buttonPanel);
    }

    private void addSpeedController() {
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
        speedUp.setBackground(new Color(92, 92, 92));
        speedUp.setForeground(new Color( 255, 255, 255));

        JButton speedDown = new JButton("<<");
        speedDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                speedDownButtonPressed();
            } } );
        speedDown.setBackground(new Color(92, 92, 92));
        speedDown.setForeground(new Color( 255, 255, 255));

        speedIndicator = new JLabel("Huidige snelheid: " + currentSpeed);

        speedPanel.add(speedDown);
        speedPanel.add(speedIndicator);
        speedPanel.add(speedUp);

        add(speedPanel);
    }

    private void addStats() {

        int sideBarLocationY = parkViewHeight - 390;
        int sideBarLocationX = parkViewWidth + 10;

        JPanel sideBar = new JPanel();
        sideBar.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        sideBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        sideBar.setBounds(sideBarLocationX, sideBarLocationY, 150, 400);
        sideBar.setBackground(Color.WHITE);

        JButton configButton = new JButton("Configuratie Menu");
        configButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statsButtonPressed();
            } } );
        configButton.setBackground(new Color(92, 92, 92));
        configButton.setForeground(new Color( 255, 255, 255));

        time = new JLabel("");
        parkedCars = new JLabel("geparkeerde auto's:  ");
        JLabel freeSpots = new JLabel("vrije plekken: <>    ");
        JLabel carQueue = new JLabel("In de rij:  <>   ");
        sideBar.add(time);
        sideBar.add(parkedCars);
        sideBar.add(freeSpots);
        sideBar.add(carQueue);
        sideBar.add(configButton);
        add(sideBar);
    }

    // ---- end VIEW section ---- //

    // ---- CONTROLLER section ---- //

    public void customTickAmountKeyTyped(java.awt.event.KeyEvent evt) {
        char enter = evt.getKeyChar();
        if(!(Character.isAlphabetic(enter)) && customTicksActive == TRUE){
            ticksToReachInt = 1;
            ticksToReach.setVisible(false);
            customTicksActive = false;
            if (whichStatus == false) {
                statusChangerButtonPressed();
                customTicksActive = false;
            }
        }
    }

    public static void setTime(String string) {
        time.setText(string);
    }

    public void statusChangerButtonPressed() {
        whichStatus =! whichStatus;
        if (whichStatus == false) {
            SimulatorModel.startTimer();
            setStatus("Actief!  ");
            statusLabel.setForeground(new java.awt.Color(0, 155, 0));
            statusChanger.setText("Pauzeer");
        } else {
            SimulatorModel.stopTimer();
            setStatus("Gestopt!");
            statusLabel.setForeground(new java.awt.Color(155, 0, 0));
            statusChanger.setText("Hervat  ");
        }

    }

    private void statsButtonPressed() {
        System.out.println("Werkt nog niet!");
    }

    public void setStatus(String string) {
        statusLabel.setText("Status: " + string);
    }

    public void updateTicks() {
        tickAmountString = Integer.toString(Simulator.getTicks());
        tickCounter.setText("Aantal ticks: " + tickAmountString + " || Max ticks: " + Simulator.maxTicks);

        if (customTicksActive == TRUE) {
            String ticksToReachString = Integer.toString(ticksToReachInt);
            ticksToReachInt--;
            ticksToReach.setText(ticksToReachString);
            if (ticksToReachInt < 0) {
                customTicksActive = false;
                ticksToReach.setVisible(false);
                SimulatorModel.stopTimer();
                if (whichStatus == false) {
                    statusChangerButtonPressed();
                }
            }
        }
    }
    private void speedDownButtonPressed() {
        currentSpeedStep--;
        SimulatorModel.checkSpeed();
        if (currentSpeedStep == 0) {
            currentSpeedStep = currentSpeedStep +1;
        }
    }

    private void speedUpButtonPressed() {
        currentSpeedStep++;
        SimulatorModel.checkSpeed();
        if (currentSpeedStep == 8) {
            currentSpeedStep = currentSpeedStep -1;
        }
    }

    private void hundredTicksButtonPressed() {
        if (!customTicksActive) {
            if (whichStatus == true) {
                statusChangerButtonPressed();
            }
            SimulatorModel.startTimer();
            ticksToReachInt = (Integer) customTickAmount.getValue() -1;
            customTicksActive = true;
            ticksToReach.setVisible(true);
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
