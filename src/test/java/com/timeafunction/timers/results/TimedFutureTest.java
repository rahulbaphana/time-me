package com.timeafunction.timers.results;

import com.timeafunction.timers.test.data.ExpensiveDataFetcher;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TimedFutureTest {
    private Callable<String> task = () -> new ExpensiveDataFetcher().greet();
    private ExecutorService service = Executors.newSingleThreadExecutor();

    @Test
    void should_delegate_cancellation_to_wrapped_future() {
        Future<String> future = service.submit(task);
        TimedFuture<String> timedFuture = new TimedFuture<>(future);
        assertFalse(future.isCancelled());

        timedFuture.cancel(true);

        assertTrue(future.isCancelled());
        assertTrue(timedFuture.isCancelled());
    }

    @Test
    void wrapped_future_is_done_when_timedFuture_executes() {
        Future<String> future = service.submit(task);
        TimedFuture<String> timedFuture = new TimedFuture<>(future);
        assertFalse(future.isDone());

        timedFuture.get();

        assertTrue(future.isDone());
        assertTrue(timedFuture.isDone());
    }
}