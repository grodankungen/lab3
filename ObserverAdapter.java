import java.util.ArrayList;
import java.util.function.Function;

public class ObserverAdapter<T1, T2, V> implements Observer<T1, V>, Observable<T2, V> {


    private final Function<T1, T2> mapper;

    private final ArrayList<Observer<T2, V>> dest_observers = new ArrayList<>();

    public ObserverAdapter(Function<T1, T2> mapper) {
        this.mapper = mapper;
    }

    @Override
    public void addObserver(Observer<T2, V> observer) {
        dest_observers.add(observer);
    }

    @Override
    public void removeObserver(Observer<T2, V> observer) {
        dest_observers.remove(observer);
    }

    @Override
    public void notifyObservers(T2 data, V eventType) {
        for (Observer<T2, V> observer : dest_observers) {
            observer.update(data, eventType);
        }
    }

    @Override
    public void update(T1 data, V event) {

        T2 mapped = this.mapper.apply(data);

        notifyObservers(mapped, event);
    }
}
