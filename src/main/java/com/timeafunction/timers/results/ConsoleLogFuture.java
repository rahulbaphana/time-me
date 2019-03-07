package com.timeafunction.timers.results;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ConsoleLogFuture<T> implements Future<T> {
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
  public T get() throws InterruptedException, ExecutionException {
    TimedResult<T> timedResult = timedFuture.get();
    System.out.println("Timed result :: " + timedResult.toString());
    try {
      return timedResult.getResult();
    } catch (InterruptedException e) {
      throw e;
    } catch (Exception e) {
      throw new ExecutionException(e.getMessage(), e.getCause());
    }
  }

  @Override
  public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
    TimedResult<T> timedResult = timedFuture.get(timeout, unit);
    System.out.println("Timed result :: " + timedResult.toString());
    try {
      return timedResult.getResult();
    } catch (InterruptedException | TimeoutException e) {
      throw e;
    } catch (Exception e) {
      throw new ExecutionException(e.getMessage(), e.getCause());
    }
  }
}
