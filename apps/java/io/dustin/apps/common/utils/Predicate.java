package io.dustin.apps.common.utils;

@FunctionalInterface
public interface Predicate<T> {
    // T -> boolean
    boolean test(T t);

}
