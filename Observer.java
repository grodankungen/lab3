public interface Observer<T, V> {

    public void update(T data, V event);
}
