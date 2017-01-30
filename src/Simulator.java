import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.*;
public class Simulator extends SimulatorModel {

    private static final String AD_HOC = "1";
    private static final String PASS = "2";


    private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;
    private SimulatorView simulatorView;

    private static int day = 0;
    private int hour = 0;
    private int minute = 0;

    protected static int tickDelay = 125;

    static int weekDayArrivals= 100; // average number of arriving cars per hour
    static int weekendArrivals = 200; // average number of arriving cars per hour
    static int weekDayPassArrivals= 50; // average number of arriving cars per hour
    static int weekendPassArrivals = 500; // average number of arriving cars per hour

    int enterSpeed = 3; // number of cars that can enter per minute
    int paymentSpeed = 7; // number of cars that can pay per minute
    int exitSpeed = 5; // number of cars that can leave per minute

    static int tickAmount = 0;
    static int maxTicks = 10000;

    protected static Timer tickTimer;

    static int carsEntered;         //houd bij hoeveel auto's ingereden zijn (precies: door de entrancequeue heengekomen zijn).
    static int carsLeft;

    public Simulator() {
        int floor = SimulatieStartGUI.floorAmount;
        int rows = SimulatieStartGUI.rowAmount;
        int places = SimulatieStartGUI.placeAmount;
        entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        simulatorView = new SimulatorView(floor, rows, places);
        carsEntered = 0;        //totaal aantal autos die zijn ingereden sinds start simulatie.
        carsLeft = 0;


        ////// TIMER CODE START
        tickTimer = new Timer(1, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tickAmount < maxTicks) {
                    tick();
                    tickAmount++;
                    simulatorView.updateTicks();
                    updateTicks();
                    simulatorView.updateCarsEntered();      //update CarsEntered method called bij elke tick, zodat hij in de view geupdate word.
                    simulatorView.updateCarsParked();       //update CarsParked. Berekent hoeveel autos er nu in staan.
                    simulatorView.updateOpenSpots();        //update de open spots.
                }
            }
        });
        /////   TIMER CODE END

        tickTimer.start();
        tickTimer.setDelay(tickDelay);
    }

    public static int getTicks() {
        return tickAmount;
    }

    public static int getCarsEntered() {        //zodat we de info in SimulatorView kunnen updaten.
        return carsEntered;
    }

    public static int getCarsParked(){          //berekent aantal auto's in de parkeergarage op dat moment, returned dit.
        return (carsEntered - carsLeft);
    }

    public void tick() {
        advanceTime();
        handleExit();
        updateViews();
        handleEntrance();
    }

    private void handleEntrance(){
        carsArriving();
        carsEntering(entrancePassQueue);
        carsEntering(entranceCarQueue);
    }

    private void handleExit(){
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }

    private void updateViews(){
        simulatorView.tick();
        // Update the car park view.
        simulatorView.updateView();
    }

    private void carsArriving(){
        int numberOfCars=getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, AD_HOC);
        numberOfCars=getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, PASS);
    }

    private void carsEntering(CarQueue queue){
        int i=0;
        // Remove car from the front of the queue and assign to a parking space.
        while (queue.carsInQueue()>0 &&
                simulatorView.getNumberOfOpenSpots()>0 &&
                i<enterSpeed) {
            LegacyCar legacyCar = queue.removeCar();
            Location freeLocation = simulatorView.getFirstFreeLocation();
            simulatorView.setCarAt(freeLocation, legacyCar);
            i++;
            carsEntered++;                  //increment voor de counter, totaal aantal autos die zijn ingereden sinds start simulatie.
        }
    }

    private void carsReadyToLeave(){
        // Add leaving cars to the payment queue.
        LegacyCar legacyCar = simulatorView.getFirstLeavingCar();
        while (legacyCar !=null) {
            if (legacyCar.getHasToPay()){
                legacyCar.setIsPaying(true);
                paymentCarQueue.addCar(legacyCar);
            }
            else {
                carLeavesSpot(legacyCar);
            }
            legacyCar = simulatorView.getFirstLeavingCar();
        }
    }

    private void carsPaying(){
        // Let cars pay.
        int i=0;
        while (paymentCarQueue.carsInQueue()>0 && i < paymentSpeed){
            LegacyCar legacyCar = paymentCarQueue.removeCar();
            // TODO Handle payment.
            carLeavesSpot(legacyCar);
            i++;
        }
    }

    private void carsLeaving(){
        // Let cars leave.
        int i=0;
        while (exitCarQueue.carsInQueue()>0 && i < exitSpeed){
            exitCarQueue.removeCar();
            i++;
            carsLeft++;
        }
    }

    public static int getNumberOfCars(int weekDay, int weekend){
        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = day < 5
                ? weekDay
                : weekend;

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.5;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int)Math.round(numberOfCarsPerHour / 60);
    }

    private void addArrivingCars(int numberOfCars, String type){
        // Add the cars to the back of the queue.
        switch(type) {
            case AD_HOC:
                for (int i = 0; i < numberOfCars; i++) {
                    entranceCarQueue.addCar(new AdHocCar());
                }
                break;
            case PASS:
                for (int i = 0; i < numberOfCars; i++) {
                    entrancePassQueue.addCar(new ParkingPassCar());
                }
                break;
        }
    }

    private void carLeavesSpot(LegacyCar legacyCar){
        simulatorView.removeCarAt(legacyCar.getLocation());
        exitCarQueue.addCar(legacyCar);
    }

}
