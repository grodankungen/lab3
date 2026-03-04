public interface Observer<T, V> {

    public void update(T event, V type);
}
