package com.time.me.timers;

import com.time.me.actions.Action;
import com.time.me.actions.NoAction;
import com.time.me.timers.results.TimedResult;

import java.util.concurrent.Callable;

/**
 * ResultTimer allows to time a function and always returns a result as TimedResult.
 * @see com.time.me.timers.results.TimedResult
 */
public enum ResultTimer {
    ;

    /**
     * @param runnable is a function with return type as 'void'
     * @return TimedResult that holds time taken to execute in millis
     * @see com.time.me.timers.results.TimedResult
     */
    public static TimedResult timeMe(Runnable runnable) {
        long startTime = System.currentTimeMillis();
        NoAction noAction = () -> {
            runnable.run();
            return null;
        };
        noAction.time();
        return new TimedResult<Void>((System.currentTimeMillis() - startTime));
    }

    /**
     * @param callableFunction is a function that returns a result of Type 'T'
     * @param <T> is the type of object returned by function execution
     * @return TimedResult holds time taken and result of the 'callableFunction' param
     * @see com.time.me.timers.results.TimedResult
     */
    public static <T> TimedResult<T> timeMe(Callable<T> callableFunction) {
        Action<TimedResult<T>> timedAction = () -> {
            long startTime = System.currentTimeMillis();
            try {
                return new TimedResult<>(callableFunction.call(), (System.currentTimeMillis() - startTime));
            } catch (Exception e) {
                e.printStackTrace();
                return new TimedResult<>((System.currentTimeMillis() - startTime));
            }
        };
        return timedAction.time();
    }
}
