package roulette.game.dto;
public class GenericWrapper<T> {
    private T value;
    public GenericWrapper() {
        super();
    }
    public GenericWrapper(T value) {
        this.value = value;
    }
    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }
}
