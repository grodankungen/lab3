import src.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CarApp {
    static void main() {

        HashMap<Car, DrawableObject> carEntities = new HashMap<>();
        HashMap<CarWorkshop<Volvo240>, DrawableObject> workshopEntities = new HashMap<>();

        carEntities.put(new Volvo240(0, 300), new DrawableObject(new Point(0, 300), "pics/Volvo240.jpg"));
        carEntities.put(new Saab95(0, 100), new DrawableObject(new Point(0, 100), "pics/Saab95.jpg"));
        carEntities.put(new Scania(0, 200), new DrawableObject(new Point(0, 200), "pics/Scania.jpg"));
        workshopEntities.put(new CarWorkshop<Volvo240>(5, new Point(300, 300)), new DrawableObject(new Point(300, 300), "pics/VolvoBrand.jpg"));

        ArrayList<DrawableObject> drawableObjects = new ArrayList<>();
        drawableObjects.addAll(carEntities.values());
        drawableObjects.addAll(workshopEntities.values());


        CarModel model = new CarModel(carEntities, workshopEntities);
        CarWidget controller = new CarWidget("carsim the best", model, drawableObjects);

        model.addObserver(controller);

    }
}
