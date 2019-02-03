package com.timeafunction.actions;

/**
 * Any function that is capable of being timed
 * @param <T> is the type of result returned after a function is timed
 */
public interface Timeable<T> {
    T time();
}
