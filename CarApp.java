import src.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CarApp {

    final static int window_x = 800;
    final static int window_y = 800;
    final static int canvas_x = window_x;
    final static int canvas_y = window_y - 240;

    static void main() {
        HashMap<Car, DrawableObject> carEntities = new HashMap<>();
        HashMap<CarWorkshop<Volvo240>, DrawableObject> workshopEntities = new HashMap<>();

        carEntities.put(CarFactory.createCarWithStartingPosition(CarType.VOLVO240, new Point(0, 300)), new DrawableObject(new Point(0, 300), "pics/Volvo240.jpg"));
        carEntities.put(CarFactory.createCarWithStartingPosition(CarType.SAAB95, new Point(0, 100)), new DrawableObject(new Point(0, 100), "pics/Saab95.jpg"));
        carEntities.put(CarFactory.createCarWithStartingPosition(CarType.SCANIA, new Point(0, 200)), new DrawableObject(new Point(0, 200), "pics/Scania.jpg"));
        workshopEntities.put(new CarWorkshop<Volvo240>(5, new Point(300, 300)), new DrawableObject(new Point(300, 300), "pics/VolvoBrand.jpg"));

        ArrayList<DrawableObject> drawableObjects = new ArrayList<>();
        drawableObjects.addAll(carEntities.values());
        drawableObjects.addAll(workshopEntities.values());


        CarModel model = new CarModel(carEntities, workshopEntities);
        CarController controller = new CarController(model);
        CarView carView = new CarView("carsim the best", window_x, window_y, canvas_x, canvas_y, controller, drawableObjects);

        model.addObserver(carView);

    }
}
