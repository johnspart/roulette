package roulette.game.dto;

import org.springframework.web.bind.annotation.RestController;

public class GenericValue<T> {
    private T value;

    public GenericValue() {
        super();
    }

    public GenericValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
