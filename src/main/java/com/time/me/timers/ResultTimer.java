package com.time.me.timers;

import com.time.me.actions.Action;
import com.time.me.actions.NoAction;
import com.time.me.timers.results.TimedResult;

import java.util.concurrent.Callable;

public enum ResultTimer {
    ;

    public static TimedResult timeMe(Runnable runnable) {
        long startTime = System.currentTimeMillis();
        NoAction noAction = () -> {
            runnable.run();
            return null;
        };
        noAction.time();
        return new TimedResult<Void>((System.currentTimeMillis() - startTime));
    }

    public static <T> TimedResult<T> timeMe(Callable<T> callableFunction) {
        Action<TimedResult<T>> timedAction = () -> {
            long startTime = System.currentTimeMillis();
            long timeTakenForExecution;
            try {
                T result = callableFunction.call();
                timeTakenForExecution = (System.currentTimeMillis() - startTime);
                return new TimedResult<>(result, timeTakenForExecution);
            } catch (Exception e) {
                e.printStackTrace();
                timeTakenForExecution = (System.currentTimeMillis() - startTime);
                return new TimedResult<>(timeTakenForExecution);
            }
        };
        return timedAction.time();
    }
}
