package com.timeafunction.actions;

public interface Action<U> extends Timeable<U> {
    U time();
}