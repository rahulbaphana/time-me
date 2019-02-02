package com.time.me.timers;

import com.time.me.actions.Action;
import com.time.me.actions.NoAction;

import java.util.concurrent.Callable;

public enum Timer {
    ;

    public static void timeMe(Runnable runnable) {
        long startTime = System.currentTimeMillis();
        long timeTakenForExecution;
        NoAction noAction = () -> {
            runnable.run();
            return null;
        };
        noAction.time();
        timeTakenForExecution = (System.currentTimeMillis() - startTime);
        System.out.println("Time taken :: " + timeTakenForExecution + " mills");
    }

    public static <T> T timeMe(Callable<T> callableFunction) {
        Action<T> tAction = () -> {
            long startTime = System.currentTimeMillis();
            long timeTakenForExecution;
            try {
                T result = callableFunction.call();
                timeTakenForExecution = (System.currentTimeMillis() - startTime);
                System.out.println("Time taken :: " + timeTakenForExecution + " mills");
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                timeTakenForExecution = (System.currentTimeMillis() - startTime);
                System.out.println("Time taken :: " + timeTakenForExecution + " mills");
                return null;
            }
        };
        return tAction.time();
    }
}
