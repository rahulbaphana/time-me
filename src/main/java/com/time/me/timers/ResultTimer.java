package com.time.me.timers;

import com.sun.istack.internal.NotNull;
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
     * @param runnable
     * @return TimedResult
     * @see com.time.me.timers.results.TimedResult
     */
    public static TimedResult timeMe(@NotNull Runnable runnable) {
        long startTime = System.currentTimeMillis();
        NoAction noAction = () -> {
            runnable.run();
            return null;
        };
        noAction.time();
        return new TimedResult<Void>((System.currentTimeMillis() - startTime));
    }

    /**
     * @param callableFunction
     * @param <T>
     * @return TimedResult<T>
     * @see com.time.me.timers.results.TimedResult
     */
    public static <T> TimedResult<T> timeMe(@NotNull Callable<T> callableFunction) {
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
