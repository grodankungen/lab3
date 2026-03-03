public class CarController implements ICarController {
    private final CarModel model;

    public CarController(CarModel model) {
        this.model = model;
    }

    @Override
    public void gas(int amount) {
        model.gas(amount);
    }

    @Override
    public void brake(int amount) {
        model.brake(amount);
    }

    @Override
    public void startCar() {
        model.startCar();
    }

    @Override
    public void stopCar() {
        model.stopCar();
    }

    @Override
    public void setTurboOn() {
        model.setTurboOn();
    }

    @Override
    public void setTurboOff() {
        model.setTurboOff();
    }

    @Override
    public void liftBed(int amount) {
        model.liftBed(amount);
    }

    @Override
    public void lowerBed(int amount) {
        model.lowerBed(amount);
    }


}
