import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel {

    ArrayList<DrawableObject<?>> drawables = new ArrayList<>();

    // TODO: Make this general for all cars
    void moveit(DrawableObject<?> drawable, int x, int y) {
        drawable.point.x = x;
        drawable.point.y = y;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, ArrayList<DrawableObject<?>> drawables) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block

        this.drawables = drawables;
//        try {
//            // You can remove the "pics" part if running outside of IntelliJ and
//            // everything is in the same main folder.
//            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));
//
//            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
//            // if you are starting in IntelliJ.
//            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
//            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (DrawableObject<?> dr : drawables) {
            g.drawImage(dr.image, dr.point.x, dr.point.y, null);
        }
    }
}
