package com.timeafunction.timers.futures;

import com.timeafunction.timers.ResultTimer;
import com.timeafunction.timers.results.TimedResult;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * A wrapper class over a Future to hold its execution time and result
 *
 * @param <T> is the type of the Future
 */
public class TimedFuture<T> implements Future<TimedResult<T>> {
  private Future<T> future;

  public TimedFuture(Future<T> future) {
    this.future = future;
  }

  @Override
  public boolean cancel(boolean mayInterruptIfRunning) {
    return future.cancel(mayInterruptIfRunning);
  }

  @Override
  public boolean isCancelled() {
    return future.isCancelled();
  }

  @Override
  public boolean isDone() {
    return future.isDone();
  }

  @Override
  public TimedResult<T> get() {
    return ResultTimer.timeMe(() -> future.get());
  }

  @Override
  public TimedResult<T> get(long timeout, TimeUnit unit) {
    return ResultTimer.timeMe(() -> future.get(timeout, unit));
  }
}
