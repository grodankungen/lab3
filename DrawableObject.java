import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DrawableObject<T> {

    public final T object;
    public Point point;

    public BufferedImage image;

    public DrawableObject(T ref, Point point, String imgPath) {
        object = ref;
        this.point = point;
        try {
            image = ImageIO.read(DrawableObject.class.getResourceAsStream(imgPath));
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
