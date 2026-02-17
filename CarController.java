import src.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/*
 * This class represents the Controller part in the MVC pattern.
 * It's responsibilities is to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;

    //entities that move (e.g. can have a velocity)
    ArrayList<DrawableObject<Car>> CarEntities = new ArrayList<>();

    //entities that don't move (CarWorkshop)
    ArrayList<DrawableObject<CarWorkshop<? extends Car>>> WorkshopEntities = new ArrayList<>();


    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        //  then added into "Car" / "imCar" lists
        DrawableObject<CarWorkshop<? extends Car>> workshopDrawable = new DrawableObject<>(new CarWorkshop<Volvo240>(5), new Point(300, 300), "pics/VolvoBrand.jpg");
        cc.WorkshopEntities.add(workshopDrawable);

        DrawableObject<Car> volvoDrawable = new DrawableObject<>(new Volvo240(0, 0), new Point(0, 0), "pics/Volvo240.jpg");
        cc.CarEntities.add(volvoDrawable);


        DrawableObject<Car> saab95Drawable = new DrawableObject<>(new Saab95(0, 100), new Point(0, 100), "pics/Saab95.jpg");
        cc.CarEntities.add(saab95Drawable);


        DrawableObject<Car> scaniaDrawable = new DrawableObject<>(new Scania(0, 200), new Point(0, 200), "pics/Scania.jpg");
        cc.CarEntities.add(scaniaDrawable);

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc, cc.CarEntities, cc.WorkshopEntities);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //update location of Car entities;
            for (DrawableObject<Car> drawable : CarEntities) {
                drawable.object.move();
                int x = (int) Math.round(drawable.object.getX());
                int y = (int) Math.round(drawable.object.getY());
                frame.drawPanel.moveit(drawable, x, y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (DrawableObject<Car> drawable : CarEntities) {
            drawable.object.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (DrawableObject<Car> drawable : CarEntities) {
            drawable.object.brake(brake);
        }
    }

    void startCar() {
        for (DrawableObject<Car> drawable : CarEntities) {
            drawable.object.startEngine();
        }
    }

    void stopCar() {
        for (DrawableObject<Car> drawable : CarEntities) {
            drawable.object.stopEngine();
        }
    }

    void setTurboOn() {
        for (DrawableObject<Car> drawable : CarEntities) {
            if (drawable.object instanceof Saab95) {
                ((Saab95) drawable.object).setTurboOn();
            }
        }
    }

    void setTurboOff() {
        for (DrawableObject<Car> drawable : CarEntities) {
            if (drawable.object instanceof Saab95) {
                ((Saab95) drawable.object).setTurboOff();
            }
        }
    }

    void liftBed(int amount) {
        for (DrawableObject<Car> drawable : CarEntities) {
            if (drawable.object instanceof Scania) {
                ((Scania) drawable.object).raise(amount);
            }
        }
    }

    void lowerBed(int amount) {
        for (DrawableObject<Car> drawable : CarEntities) {
            if (drawable.object instanceof Scania) {
                ((Scania) drawable.object).lower(amount);
            }
        }
    }
}
