public class CarController {
    private final CarModel model;

    public CarController(CarModel model) {
        this.model = model;
    }

    public void gas(int amount) {
        model.gas(amount);
    }

    public void brake(int amount) {
        model.brake(amount);
    }

    public void startCar() {
        model.startCar();
    }

    public void stopCar() {
        model.stopCar();
    }

    public void setTurboOn() {
        model.setTurboOn();
    }

    public void setTurboOff() {
        model.setTurboOff();
    }

    public void liftBed(int amount) {
        model.liftBed(amount);
    }

    public void lowerBed(int amount) {
        model.lowerBed(amount);
    }
}
