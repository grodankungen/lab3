import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DrawableObject {
    public Point point;
    public BufferedImage image;

    public DrawableObject(Point point, String imgPath) {
        this.point = point;
        try {
            image = ImageIO.read(DrawableObject.class.getResourceAsStream(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
