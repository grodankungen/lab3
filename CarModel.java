import src.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
/*
 * This class represents the Controller part in the MVC pattern.
 * It's responsibilities is to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarModel {
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    private HashMap<Car, DrawableObject> carEntities;
    private HashMap<CarWorkshop, DrawableObject> workshopEntities;

    // The frame that represents this instance View of the MVC pattern
    CarController frame;

    // methods
    static void main(String[] args) {
        // Instance of this class
        CarModel cc = new CarModel();

        cc.carEntities = new HashMap<>();
        cc.carEntities.put(new Volvo240(0, 300), new DrawableObject(new Point(0, 300), "pics/Volvo240.jpg"));
        cc.carEntities.put(new Saab95(0, 100), new DrawableObject(new Point(0, 100), "pics/Saab95.jpg"));
        cc.carEntities.put(new Scania(0, 200),  new DrawableObject(new Point(0, 200), "pics/Scania.jpg"));

        cc.workshopEntities = new HashMap<>();
        cc.workshopEntities.put(
                new CarWorkshop<Volvo240>(5, new Point(300, 300)),
                new DrawableObject(new Point(300, 300), "pics/VolvoBrand.jpg")
        );

        ArrayList<DrawableObject> drawableObjects = new ArrayList<>();
        drawableObjects.addAll(cc.carEntities.values());
        drawableObjects.addAll(cc.workshopEntities.values());

        // Start a new view and send a reference of self
        cc.frame = new CarController("CarSim 1.0", cc, drawableObjects);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     */
    private class TimerListener implements ActionListener {
        /**
         * Updates location of the Car entities
         * Checks if a car touched a wall -> turn 180 degrees
         * Checks if a car should be loaded into a workshop
         */
        public void actionPerformed(ActionEvent e) {
            for (Car car : carEntities.keySet()) {
                if (car.getX() > 800 || car.getX() < 0 || car.getY() > 560 || car.getY() < 0) {
                    car.turnRight(180);
                }

                for (CarWorkshop<?> workshop : workshopEntities.keySet()) {
                    if (car.getX() > workshop.getX() - 10
                            && car.getX() < workshop.getX() + 10
                            && car.getY() > workshop.getY() - 10
                            && car.getY() < workshop.getY() + 10) {

                        if (car instanceof Volvo240) {  // TODO hur ska vi fixa detta för alla typer av workshops?
                            CarWorkshop<Volvo240> volvoWorkshop = (CarWorkshop<Volvo240>) workshop;
                            volvoWorkshop.loadCar((Volvo240) car);
                        }
                    }
                }

            }

            for (Car car : carEntities.keySet()) {
                car.move();
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());
                frame.drawPanel.moveit(carEntities.get(car), x, y);

                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : carEntities.keySet()) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Car car : carEntities.keySet()) {
            car.brake(brake);
        }
    }

    void startCar() {
        for (Car car : carEntities.keySet()) {
            car.startEngine();
        }
    }

    void stopCar() {
        for (Car car : carEntities.keySet()) {
            car.stopEngine();
        }
    }

    void setTurboOn() {
        for (Car car : carEntities.keySet()) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOn();
            }
        }
    }

    void setTurboOff() {
        for (Car car : carEntities.keySet()) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff();
            }
        }
    }

    void liftBed(int amount) {
        for (Car car : carEntities.keySet()) {
            if (car instanceof Scania) {
                ((Scania) car).raise(amount);
            }
        }
    }

    void lowerBed(int amount) {
        for (Car car : carEntities.keySet()) {
            if (car instanceof Scania) {
                ((Scania) car).lower(amount);
            }
        }
    }
}
