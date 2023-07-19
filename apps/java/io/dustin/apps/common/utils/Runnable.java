package io.dustin.apps.common.utils;

@FunctionalInterface
public interface Runnable {
    /*
    () -> void
    Runnable을 이용하여 트랜잭션을 분리할 수 있다고 한다.
     */
    public abstract void run();
}
