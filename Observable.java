public interface Observable<T, V> {

    void addObserver(Observer<T, V> observer);

    void removeObserver(Observer<T, V> observer);

    void notifyObservers(T data, V eventType);
}
