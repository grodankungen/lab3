import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel {
    ArrayList<DrawableObject> drawableObjects;

    void moveit(DrawableObject drawable, int x, int y) {
        drawable.point.x = x;
        drawable.point.y = y;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, ArrayList<DrawableObject> drawableObjects) {
        this.setDoubleBuffered(true);

        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);

        this.drawableObjects = drawableObjects;
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (DrawableObject dr : drawableObjects) {
            g.drawImage(dr.image, dr.point.x, dr.point.y, null);
            g.drawImage(dr.image, dr.point.x, dr.point.y, null);
        }
    }
}
