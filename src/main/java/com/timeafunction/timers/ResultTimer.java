package com.timeafunction.timers;

import com.timeafunction.timers.results.TimedResult;
import com.timeafunction.timers.futures.TimedFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import static java.util.concurrent.Executors.callable;

/**
 * ResultTimer allows to time a function and always returns a result as TimedResult.
 *
 * @see TimedResult
 */
public enum ResultTimer {
    ;

    /**
     * @param runnable is a function with return type as 'void'
     * @return TimedResult that holds time taken to execute in millis
     * @see TimedResult
     */
    public static TimedResult timeMe(Runnable runnable) {
        return execute(callable(runnable));
    }

    /**
     * @param callableFunction is a function that returns a result of Type 'T'
     * @param <T>              is the type of object returned by function execution
     * @return TimedResult holds time taken and result of the 'callableFunction' param
     * @see TimedResult
     */
    public static <T> TimedResult<T> timeMe(Callable<T> callableFunction) {
        return execute(callableFunction);
    }

    /**
     *
     * @param future returns a value of type 'T'
     * @param <T> is the type of object returned by the future
     * @return future of type TimedResult, that holds the time taken and the result of the future execution
     */
    public static <T> Future<TimedResult<T>> timeMe(Future<T> future) {
        return new TimedFuture<>(future);
    }

    private static <T> TimedResult<T> execute(Callable<T> callableFunction) {
        long startTime = System.currentTimeMillis();
        try {
            return new TimedResult<>(callableFunction.call(), (System.currentTimeMillis() - startTime));
        } catch (Exception e) {
            e.printStackTrace();
            return new TimedResult<T>(new RuntimeException(e.getMessage(), e.getCause()), (System.currentTimeMillis() - startTime));
        }
    }
}
