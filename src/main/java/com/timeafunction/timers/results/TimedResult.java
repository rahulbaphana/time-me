package com.timeafunction.timers.results;

import java.util.Optional;

/**
 *
 * TimedResult
 * @param <T> is the return type of the result
 */
public final class TimedResult<T> {
    private T result;
    private Exception executionError;
    private Long timeTakenInMillis;

    /**
     *
     * @param timeTakenInMillis gives the time taken to get the result by a function
     */
    public TimedResult(Long timeTakenInMillis) {
        this.timeTakenInMillis = timeTakenInMillis;
    }

    /**
     *
     * @param result is the result returned by a non-void function
     * @param timeTakenInMillis is the time taken to return the result by a function
     */
    public TimedResult(T result, long timeTakenInMillis) {
        this.result = result;
        this.timeTakenInMillis = timeTakenInMillis;
    }

    /**
     *
     * @param timeTakenInMillis gives the time taken to get the result by a function
     */
    public TimedResult(Exception executionError, Long timeTakenInMillis) {
        this.executionError = executionError;
        this.timeTakenInMillis = timeTakenInMillis;
    }

    /**
     *
     * @return timeTakenInMillis
     */
    public Long getTimeTakenInMillis() {
        return timeTakenInMillis;
    }

    /**
     *
     * @return result
     */
    public T getResult() throws Exception {
        return hasException() ? throwException() : result;
    }

    /**
     *
     * @return string representation of the object
     */
    @Override
    public String toString() {
        return "TimedResult{" + "result=" + result + ", timeTakenInMillis=" + timeTakenInMillis + " millis}";
    }

    public boolean hasException() {
        return Optional.ofNullable(executionError).isPresent();
    }

    private T throwException() throws Exception {
        throw new Exception(executionError);
    }
}