package com.time.me.timers.results;

public class TimedResult<T> {
    private T result;
    private Long timeTakenInMillis;

    public TimedResult(Long timeTakenInMillis) {
        this.timeTakenInMillis = timeTakenInMillis;
    }

    public TimedResult(T result, long timeTakenInMillis) {
        this.result = result;
        this.timeTakenInMillis = timeTakenInMillis;
    }

    public Long getTimeTakenInMillis() {
        return timeTakenInMillis;
    }

    public T getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "TimedResult{" + "result=" + result + ", timeTakenInMillis=" + timeTakenInMillis + " millis}";
    }
}
