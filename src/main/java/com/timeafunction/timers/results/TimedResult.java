package com.timeafunction.timers.results;

import java.util.Optional;

/**
 * TimedResult
 *
 * @param <T> is the return type of the result
 */
public final class TimedResult<T> {
    private T result;
    private RuntimeException executionError;
    private Long timeTakenInMillis;

    /**
     * @param result            is the result returned by a non-void function
     * @param timeTakenInMillis is the time taken to return the result by a function
     */
    public TimedResult(T result, long timeTakenInMillis) {
        this.result = result;
        this.timeTakenInMillis = timeTakenInMillis;
    }

    /**
     * @param executionError    is the error thrown while executing a function
     * @param timeTakenInMillis is the time taken to return the result by a function
     */
    public TimedResult(RuntimeException executionError, Long timeTakenInMillis) {
        this.executionError = executionError;
        this.timeTakenInMillis = timeTakenInMillis;
    }

    /**
     * @return timeTakenInMillis
     */
    public Long getTimeTakenInMillis() {
        return timeTakenInMillis;
    }

    /**
     * @return result or throws exception
     */
    public T getResult() {
        if(hasException()) {
            throw executionError;
        }
        return result;
    }

    /**
     * @return boolean as True if there is an exception or else False
     */
    public boolean hasException() {
        return Optional
                .ofNullable(executionError)
                .isPresent();
    }

    /**
     * @return string representation of the object
     */
    @Override
    public String toString() {
        return "TimedResult{" + "result=" + result + ", timeTakenInMillis=" + timeTakenInMillis + " millis}";
    }
}
