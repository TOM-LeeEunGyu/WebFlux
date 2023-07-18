package io.dustin.apps.common.utils;

@FunctionalInterface
public interface Supplier<T> {
    // () -> T
    T get();
}
