package com.timeafunction.timers;

import com.timeafunction.timers.results.TimedResult;

import java.util.Optional;
import java.util.concurrent.Callable;

/**
 * Console timer is used to print time taken in millis to System out.
 */
public enum ConsoleTimer {
    ;

    /**
     *
     * @param callableFunction is a function that returns a result of Type 'T'
     * @param <T> is the type of result
     * @return object of type T
     */
    public static <T> T timeMe(Callable<T> callableFunction) {
        TimedResult<T> timedResult = ResultTimer.timeMe(callableFunction);
        System.out.println("Timed result :: "+timedResult.toString());
        try {
            return timedResult.getResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param runnable is a function with return type as 'void'
     */
    public static void timeMe(Runnable runnable) {
        TimedResult timedResult = ResultTimer.timeMe(runnable);
        System.out.println("Timed result :: "+timedResult.toString());
    }

}
