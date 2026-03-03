public interface ICarController {
    void gas(int amount);

    void brake(int amount);

    void startCar();

    void stopCar();

    void setTurboOn();

    void setTurboOff();

    void liftBed(int amount);

    void lowerBed(int amount);
}
