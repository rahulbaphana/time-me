package com.time.me.actions;

public interface Action<U> extends Timeable<U> {
    U time();
}