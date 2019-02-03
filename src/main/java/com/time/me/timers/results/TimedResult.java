package com.time.me.timers.results;

/**
 *
 * TimedResult
 * @param <T> is the return type of the result
 */
public class TimedResult<T> {
    private T result;
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
     * @return timeTakenInMillis
     */
    public Long getTimeTakenInMillis() {
        return timeTakenInMillis;
    }

    /**
     *
     * @return result
     */
    public T getResult() {
        return result;
    }

    /**
     *
     * @return string representation of the object
     */
    @Override
    public String toString() {
        return "TimedResult{" + "result=" + result + ", timeTakenInMillis=" + timeTakenInMillis + " millis}";
    }
}
