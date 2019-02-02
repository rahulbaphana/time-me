package com.time.me.timers;

import com.time.me.timers.results.TimedResult;

import java.util.concurrent.Callable;

public enum ConsoleTimer {
    ;

    public static <T> T timeMe(Callable<T> callableFunction) {
        TimedResult<T> timedResult = ResultTimer.timeMe(callableFunction);
        System.out.println("Timed result :: "+timedResult.toString());
        return timedResult.getResult();
    }

    public static void timeMe(Runnable runnable) {
        TimedResult timedResult = ResultTimer.timeMe(runnable);
        System.out.println("Timed result :: "+timedResult.toString());
    }

}
