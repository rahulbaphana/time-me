package com.timeafunction.timers.results;

import com.timeafunction.timers.futures.ConsoleLogFuture;
import com.timeafunction.timers.test.data.ExpensiveDataFetcher;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsoleLogFutureTest {
    private Callable<String> task = () -> new ExpensiveDataFetcher().greet();
    private ExecutorService service = Executors.newSingleThreadExecutor();

    @Test
    void should_delegate_cancellation_to_wrapped_future() {
        Future<String> future = service.submit(task);
        ConsoleLogFuture<String> logFuture = new ConsoleLogFuture<>(future);
        assertFalse(future.isCancelled());

        logFuture.cancel(true);

        assertTrue(future.isCancelled());
        assertTrue(logFuture.isCancelled());
    }

    @Test
    void wrapped_future_is_done_when_consoleLogFuture_executes() throws Exception {
        Future<String> future = service.submit(task);
        ConsoleLogFuture<String> logFuture = new ConsoleLogFuture<>(future);
        assertFalse(future.isDone());

        logFuture.get();

        assertTrue(future.isDone());
        assertTrue(logFuture.isDone());
    }
}