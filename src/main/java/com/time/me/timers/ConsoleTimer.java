package com.time.me.timers;

import com.time.me.timers.results.TimedResult;

import java.util.concurrent.Callable;

/**
 * Console timer is used to print time take to System out.
 */
public enum ConsoleTimer {
    ;

    /**
     *
     * @param callableFunction
     * @param <T>
     * @return object of type T
     */
    public static <T> T timeMe(Callable<T> callableFunction) {
        TimedResult<T> timedResult = ResultTimer.timeMe(callableFunction);
        System.out.println("Timed result :: "+timedResult.toString());
        return timedResult.getResult();
    }

    /**
     *
     * @param runnable
     */
    public static void timeMe(Runnable runnable) {
        TimedResult timedResult = ResultTimer.timeMe(runnable);
        System.out.println("Timed result :: "+timedResult.toString());
    }

}
