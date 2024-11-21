package com.example.task01;

import java.util.function.BiConsumer;

public class Pair<T, U> {
    private T first;
    private U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return  second;
    }

    public boolean equals(Pair<T, U> p) {
        return first.equals(p.getFirst()) && second.equals(p.getSecond());
    }

    public int hashCode() {
        return first.hashCode() + second.hashCode();
    }

    public static <T, U> Pair<T, U> of(T first, U second) {
        return new Pair<T, U>(first, second);
    }

    public void ifPresent(BiConsumer<? super T, ? super U> consumer) {
        if (first != null && second != null) {
            consumer.accept(first, second);
        }
    }
    // TODO напишите реализацию
}
