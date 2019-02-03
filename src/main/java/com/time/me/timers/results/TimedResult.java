package com.time.me.timers.results;

/**
 * TimedResult
 * @param <T>
 */
public class TimedResult<T> {
    private T result;
    private Long timeTakenInMillis;

    /**
     *
     * @param timeTakenInMillis
     */
    public TimedResult(Long timeTakenInMillis) {
        this.timeTakenInMillis = timeTakenInMillis;
    }

    /**
     *
     * @param result
     * @param timeTakenInMillis
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
