package io.dustin.apps.common.utils;

@FunctionalInterface
public interface Funtion<T,R> {
    // T -> R
    R apply(T t);
}
