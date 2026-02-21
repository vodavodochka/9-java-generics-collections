package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<T, N> {
    private final T firstValue;
    private final N secondValue;

    private Pair(T firstValue, N secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public static <T,N> Pair<T,N> of(T firstValue, N secondValue) {
        return new Pair<>(firstValue, secondValue);
    }

    public T getFirst() {
        return this.firstValue;
    }

    public N getSecond() {
        return this.secondValue;
    }

    public void ifPresent(BiConsumer<? super T, ? super N> action) {
        if (firstValue != null && secondValue != null) {
            action.accept(firstValue, secondValue);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Pair<?, ?> pair = (Pair<?, ?>) obj;

        return Objects.equals(firstValue, pair.firstValue) && Objects.equals(secondValue, pair.secondValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.firstValue, this.secondValue);
    }
}
