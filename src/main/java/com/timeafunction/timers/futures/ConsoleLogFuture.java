package com.timeafunction.timers.futures;

import com.timeafunction.timers.results.TimedResult;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * A wrapper class over a Future whose execution time should be logged to console.
 *
 * @param <T> is the type of the Future
 */
public final class ConsoleLogFuture<T> implements Future<T> {
  private TimedFuture<T> timedFuture;

  public ConsoleLogFuture(Future<T> future) {
    this.timedFuture = new TimedFuture<>(future);
  }

  @Override
  public boolean cancel(boolean mayInterruptIfRunning) {
    return timedFuture.cancel(mayInterruptIfRunning);
  }

  @Override
  public boolean isCancelled() {
    return timedFuture.isCancelled();
  }

  @Override
  public boolean isDone() {
    return timedFuture.isDone();
  }

  @Override
  public T get() {
    TimedResult<T> timedResult = timedFuture.get();
    System.out.println("Timed result :: " + timedResult.toString());
    return timedResult.getResult();
  }

  @Override
  public T get(long timeout, TimeUnit unit) {
    TimedResult<T> timedResult = timedFuture.get(timeout, unit);
    System.out.println("Timed result :: " + timedResult.toString());
    return timedResult.getResult();
  }
}
