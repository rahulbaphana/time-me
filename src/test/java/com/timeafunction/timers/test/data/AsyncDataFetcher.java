package com.timeafunction.timers.test.data;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AsyncDataFetcher {
  private ExecutorService executor = Executors.newSingleThreadExecutor();

  public Future<String> fetchAsync() {
    return executor.submit(() -> {
      Thread.sleep(1000);
      return "something async";
    });
  }
}
