package com.time.me.actions;

public interface Action<U> extends Timable<U> {
    U execute();
}