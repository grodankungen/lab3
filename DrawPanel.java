import src.Car;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel {

    ArrayList<DrawableObject<Car>> Carables = new ArrayList<>();

    // TODO: Make this general for all cars
    void moveit(DrawableObject<?> drawable, int x, int y) {
        drawable.point.x = x;
        drawable.point.y = y;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, ArrayList<DrawableObject<Car>> drawables) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block

        this.Carables = drawables;
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        for (DrawableObject<Car> dr : Carables) {

            //very readable
            if (dr.object.getX() > 800 || dr.object.getX() < 0 || dr.object.getY() > 560 || dr.object.getY() < 0) {
                dr.object.turnRight(180);
            }
            g.drawImage(dr.image, dr.point.x, dr.point.y, null);
        }
    }
}
