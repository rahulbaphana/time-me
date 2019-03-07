package com.timeafunction.timers.results;

import com.timeafunction.timers.ResultTimer;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

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
