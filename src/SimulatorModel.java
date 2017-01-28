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
            Simulator.tickTimer.setDelay(500);
            SimulatorView.currentSpeed = "0.25x ";
            SimulatorView.speedIndicator.setText("Huidige snelheid: " + SimulatorView.currentSpeed);
        }
          else if (SimulatorView.currentSpeedStep == 2) {
            Simulator.tickTimer.setDelay(250);
            SimulatorView.currentSpeed = "0.50x ";
            SimulatorView.speedIndicator.setText("Huidige snelheid: " + SimulatorView.currentSpeed);
        }
          else if (SimulatorView.currentSpeedStep == 3) {
              Simulator.tickTimer.setDelay(125);
              SimulatorView.currentSpeed = "1.0x ";
              SimulatorView.speedIndicator.setText("Huidige snelheid: " + SimulatorView.currentSpeed);
          }
          else if (SimulatorView.currentSpeedStep == 4) {
            Simulator.tickTimer.setDelay(100);
            SimulatorView.currentSpeed = "1.5x ";
            SimulatorView.speedIndicator.setText("Huidige snelheid: " + SimulatorView.currentSpeed);
        } else if (SimulatorView.currentSpeedStep == 5) {
            Simulator.tickTimer.setDelay(75);
            SimulatorView.currentSpeed = "2.0x ";
            SimulatorView.speedIndicator.setText("Huidige snelheid: " + SimulatorView.currentSpeed);
        } else if (SimulatorView.currentSpeedStep == 6) {
              Simulator.tickTimer.setDelay(25);
              SimulatorView.currentSpeed = "4.0x ";
              SimulatorView.speedIndicator.setText("Huidige snelheid: " + SimulatorView.currentSpeed);
        } else if (SimulatorView.currentSpeedStep == 7) {
            Simulator.tickTimer.setDelay(1);
            SimulatorView.currentSpeed = "10.0x ";
            SimulatorView.speedIndicator.setText("Huidige snelheid: " + SimulatorView.currentSpeed);
    }
    }
    }

