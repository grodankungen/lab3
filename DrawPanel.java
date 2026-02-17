import src.Car;
import src.CarWorkshop;
import src.Volvo240;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel {

    ArrayList<DrawableObject<Car>> carEntities = new ArrayList<>();
    ArrayList<DrawableObject<CarWorkshop<Volvo240>>> workshopEntities = new ArrayList<>();

    // TODO: Make this general for all cars
    void moveit(DrawableObject<?> drawable, int x, int y) {
        drawable.point.x = x;
        drawable.point.y = y;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, ArrayList<DrawableObject<Car>> carEntities, ArrayList<DrawableObject<CarWorkshop<Volvo240>>> workshopEntities) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block

        this.carEntities = carEntities;
        this.workshopEntities = workshopEntities;
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        for (DrawableObject<Car> dr : carEntities) {

            //very readable
            if (dr.object.getX() > 800 || dr.object.getX() < 0 || dr.object.getY() > 560 || dr.object.getY() < 0) {
                dr.object.turnRight(180);
            }

            for (DrawableObject<CarWorkshop<Volvo240>> drW : workshopEntities) {
                if (dr.object.getX() > drW.point.x - 10 && dr.object.getX() < drW.point.x + 10 && dr.object.getY() > drW.point.y - 10 && dr.object.getY() < drW.point.y + 10) {

                    if (dr.object instanceof Volvo240) {

                        drW.object.loadCar(((Volvo240) dr.object));
                    }
                }
            }

            g.drawImage(dr.image, dr.point.x, dr.point.y, null);
        }

        for (DrawableObject<CarWorkshop<Volvo240>> dr : workshopEntities) {
            g.drawImage(dr.image, dr.point.x, dr.point.y, null);
        }
    }
}
