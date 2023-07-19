package io.dustin.apps.common.utils;

@FunctionalInterface
public interface Callable<V> {
    // () -> T
    V call() throws Exception;
}
