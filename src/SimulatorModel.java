import javax.swing.*;

/**
 * Created by wesle on 28-1-2017.
 */
public class SimulatorModel {
    private Simulator simulator;
    private SimulatorView simulatorView;
    private CarQueue carQueue;

    private int day = 1;
    private int hour = 12;
    private int minute = 0;

    private static int totalTicks;
    private static int ticksToReach;

    protected static String cDay;

    public SimulatorModel() {
    }

    public static void startTimer() { Simulator.tickTimer.start();

    }

    public static void stopTimer() {  Simulator.tickTimer.stop(); }

    public static void eOneTick() {

    }

    public void updateTicks() {
        ticksToReach++;
    }


    public static void checkSpeed() {
          if (SimulatorView.currentSpeedStep == 1) {
              Simulator.tickDelay = 500;
            SimulatorView.currentSpeed = "0.25x ";
        }
          else if (SimulatorView.currentSpeedStep == 2) {
            Simulator.tickDelay = 250;
            SimulatorView.currentSpeed = "0.50x ";
        }
          else if (SimulatorView.currentSpeedStep == 3) {
              Simulator.tickDelay = 125;
              SimulatorView.currentSpeed = "1.0x ";
          }
          else if (SimulatorView.currentSpeedStep == 4) {
            Simulator.tickDelay = 50;
            SimulatorView.currentSpeed = "2.0x ";
        } else if (SimulatorView.currentSpeedStep == 5) {
            Simulator.tickDelay = 20;
            SimulatorView.currentSpeed = "5.0x ";
        } else if (SimulatorView.currentSpeedStep == 6) {
              Simulator.tickDelay = 5;
              SimulatorView.currentSpeed = "10.0x ";
        } else if (SimulatorView.currentSpeedStep == 7) {
              Simulator.tickDelay = 1;
            SimulatorView.currentSpeed = "50.0x ";
    }
        SimulatorView.speedIndicator.setText("Huidige snelheid: " + SimulatorView.currentSpeed);
        Simulator.tickTimer.setDelay(Simulator.tickDelay);
    }

    public void advanceTime(){
        cDay = "";
        String cMinute = Integer.toString(minute);
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
        }


        if (day == 1) {
            cDay = "Maandag";
        } else if (day == 2) {
            cDay = "Dinsdag";
        } else if (day == 3) {
            cDay = "Woensdag";
        } else if (day == 4) {
            cDay = "Donderdag";
        } else if (day == 5) {
            cDay = "Vrijdag";
        } else if (day == 6) {
            cDay = "Zaterdag";
        } else if (day == 7) {
            cDay = "Zondag";
        }

        if (minute <= 10) { // Zorgt ervoor dat er een 0 bij staat als de minuut nog een enkel getal is
            cMinute = "0" + cMinute;
        }

        SimulatorView.setTime(("Tijd : " + cDay + " " + hour + ":" + cMinute));

    }

    }

