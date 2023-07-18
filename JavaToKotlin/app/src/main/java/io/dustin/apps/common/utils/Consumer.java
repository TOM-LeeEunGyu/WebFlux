package io.dustin.apps.common.utils;

@FunctionalInterface
public interface Consumer<T> {
    // T -> void
    void accept(T t);
}
