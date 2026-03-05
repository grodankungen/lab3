import src.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CarApp {

    //Variables that dictate size the visible JFrame (CarView)
    final static int window_x = 800;
    final static int window_y = 800;

    //variables that dictate size of the "canvas", e.g. the JFrame
    //  where images are being moved around
    final static int canvas_x = window_x;
    final static int canvas_y = window_y - 240;

    static void main() {
        HashMap<Car, DrawableObject> carEntities = new HashMap<>();
        HashMap<CarWorkshop<Volvo240>, DrawableObject> workshopEntities = new HashMap<>();

        carEntities.put(CarFactory.createCarWithStartingPosition(CarType.VOLVO240, new Point(0, 300)), new DrawableObject(new Point(0, 300), CarType.VOLVO240.getImgPath()));
        carEntities.put(CarFactory.createCarWithStartingPosition(CarType.SAAB95, new Point(0, 100)), new DrawableObject(new Point(0, 100), CarType.SAAB95.getImgPath()));
        carEntities.put(CarFactory.createCarWithStartingPosition(CarType.SCANIA, new Point(0, 200)), new DrawableObject(new Point(0, 200), CarType.SCANIA.getImgPath()));
        workshopEntities.put(new CarWorkshop<Volvo240>(5, new Point(300, 300)), new DrawableObject(new Point(300, 300), "pics/VolvoBrand.jpg"));

        ArrayList<DrawableObject> drawableObjects = new ArrayList<>();
        drawableObjects.addAll(carEntities.values());
        drawableObjects.addAll(workshopEntities.values());


        //pass canvas_size so that we can register (bounce-collision and randomized vehicle spawn)
        CarModel model = new CarModel(canvas_x, canvas_y, carEntities, workshopEntities);
        CarController controller = new CarController(model);
        CarView carView = new CarView("carsim the best", window_x, window_y, canvas_x, canvas_y, controller, drawableObjects);


        ObserverAdapter<Car, DrawableObject, CarEventType> adapter = new ObserverAdapter<>((Car c) -> carEntities.get(c));
        model.addObserver(adapter);

        adapter.addObserver(carView);


    }
}
