/**
 * Created by wesle on 28-1-2017.
 */
public class SimulatorModel {
    private Simulator simulator;
    private SimulatorView simulatorView;

    public static void startTimer() { Simulator.tickTimer.start();

    }

    public static void stopTimer() {  Simulator.tickTimer.stop(); }


    public static void checkSpeed() {
          if (SimulatorView.currentSpeedStep == 1) {
              Simulator.tickDelay = 500;
            SimulatorView.currentSpeed = "0.25x ";
            SimulatorView.speedIndicator.setText("Huidige snelheid: " + SimulatorView.currentSpeed);
        }
          else if (SimulatorView.currentSpeedStep == 2) {
            Simulator.tickDelay = 250;
            SimulatorView.currentSpeed = "0.50x ";
            SimulatorView.speedIndicator.setText("Huidige snelheid: " + SimulatorView.currentSpeed);
        }
          else if (SimulatorView.currentSpeedStep == 3) {
              Simulator.tickDelay = 125;
              SimulatorView.currentSpeed = "1.0x ";
              SimulatorView.speedIndicator.setText("Huidige snelheid: " + SimulatorView.currentSpeed);
          }
          else if (SimulatorView.currentSpeedStep == 4) {
            Simulator.tickDelay = 50;
            SimulatorView.currentSpeed = "2.0x ";
            SimulatorView.speedIndicator.setText("Huidige snelheid: " + SimulatorView.currentSpeed);
        } else if (SimulatorView.currentSpeedStep == 5) {
            Simulator.tickDelay = 20;
            SimulatorView.currentSpeed = "5.0x ";
            SimulatorView.speedIndicator.setText("Huidige snelheid: " + SimulatorView.currentSpeed);
        } else if (SimulatorView.currentSpeedStep == 6) {
              Simulator.tickDelay = 5;
              SimulatorView.currentSpeed = "10.0x ";
              SimulatorView.speedIndicator.setText("Huidige snelheid: " + SimulatorView.currentSpeed);
        } else if (SimulatorView.currentSpeedStep == 7) {
              Simulator.tickDelay = 1;
            SimulatorView.currentSpeed = "50.0x ";
            SimulatorView.speedIndicator.setText("Huidige snelheid: " + SimulatorView.currentSpeed);
    }
        Simulator.tickTimer.setDelay(Simulator.tickDelay);
    }
    }

