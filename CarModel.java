import src.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
/*
 * This class represents the Controller part in the MVC pattern.
 * It's responsibilities is to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarModel implements Observable<Car, CarEventType> {

    private final int drivable_space_x;
    private final int drivable_space_y;
    private final HashMap<Car, DrawableObject> carEntities;
    private final HashMap<CarWorkshop<Volvo240>, DrawableObject> workshopEntities;

    private final ArrayList<Observer<Car, CarEventType>> observers = new ArrayList<>();

    public CarModel(int drivable_x, int drivable_y, HashMap<Car, DrawableObject> carEntities, HashMap<CarWorkshop<Volvo240>, DrawableObject> workshopEntities) {


        drivable_space_x = drivable_x;
        drivable_space_y = drivable_y;
        this.carEntities = carEntities;
        this.workshopEntities = workshopEntities;

        // Start the timer
        // The timer is started with a listener (see below) that executes the statements
        // each step between delays.
        // The delay (ms) corresponds to 20 updates a sec (hz)
        int delay = 50;
        Timer timer = new Timer(delay, new TimerListener());
        timer.start();
    }


    @Override
    public void addObserver(Observer<Car, CarEventType> observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer<Car, CarEventType> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Car car, CarEventType type) {
        for (Observer<Car, CarEventType> obs : observers) {
            obs.update(car, type);
        }
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
                if (car.getX() > drivable_space_x || car.getX() < 0 || car.getY() > drivable_space_y || car.getY() < 0) {
                    car.turnRight(180);
                }

                for (CarWorkshop<Volvo240> workshop : workshopEntities.keySet()) {
                    if (car.getX() > workshop.getX() - 10 && car.getX() < workshop.getX() + 10 && car.getY() > workshop.getY() - 10 && car.getY() < workshop.getY() + 10) {

                        if (car instanceof Volvo240) {
                            CarWorkshop<Volvo240> volvoWorkshop = workshop;
                            volvoWorkshop.loadCar((Volvo240) car);
                        }
                    }
                }
            }

            for (Car car : carEntities.keySet()) {
                car.move();
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());
                updatePoint(carEntities.get(car), x, y);

                notifyObservers(car, CarEventType.REPAINT);
            }
        }
    }

    private void updatePoint(DrawableObject drawable, int x, int y) {
        drawable.point.x = x;
        drawable.point.y = y;
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

    void addCar() {
        if (carEntities.size() > 10) return;

        Random r = new Random();


        int x = r.nextInt(0, this.drivable_space_x);
        int y = r.nextInt(0, this.drivable_space_y);

        int car_number = r.nextInt(CarType.values().length);
        CarType carType = CarType.values()[car_number];

        Car c = CarFactory.createCarWithStartingPosition(carType, new Point(x, y));

        carEntities.put(c, new DrawableObject(new Point(x, y), carType.getImgPath()));

        notifyObservers(c, CarEventType.ADD);
    }

    void removeCar() {
        if (carEntities.isEmpty()) return;

        Random r = new Random();
        int i = r.nextInt(0, carEntities.size());

        Car c = (new ArrayList<>(carEntities.keySet())).get(i);

        for (CarWorkshop<Volvo240> workshop : workshopEntities.keySet()) {
            if (c instanceof Volvo240) {

                workshop.offloadCar((Volvo240) c);
            }
        }

        notifyObservers(c, CarEventType.REMOVE);
        carEntities.remove(c);
    }
}
