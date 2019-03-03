package com.timeafunction.timers;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class TimerFuture<T> implements Future<T> {
  private Future<T> future;

  public TimerFuture(Future<T> future) {
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
  public T get() throws InterruptedException, ExecutionException {
    try {
      return ConsoleTimer.timeMe(() -> future.get());
    } catch (InterruptedException | ExecutionException e) {
      throw e;
    } catch (Exception e) {
      throw new ExecutionException(e.getMessage(), e.getCause());
    }
  }

  @Override
  public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
    try {
      return ConsoleTimer.timeMe(() -> future.get(timeout, unit));
    } catch (InterruptedException | ExecutionException | TimeoutException e) {
      throw e;
    } catch (Exception e) {
      throw new ExecutionException(e.getMessage(), e.getCause());
    }
  }
}
