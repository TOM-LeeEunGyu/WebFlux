package io.dustin.apps.common.utils;

@FunctionalInterface
public interface Comparator<T> {
    // (T, T) -> int
    int compare(T o1, T o2);
}
