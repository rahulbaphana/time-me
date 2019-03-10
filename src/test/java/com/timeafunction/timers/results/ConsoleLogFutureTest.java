package com.timeafunction.timers.results;

import com.timeafunction.timers.futures.ConsoleLogFuture;
import com.timeafunction.timers.test.data.ExpensiveDataFetcher;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleLogFutureTest {
    private ExpensiveDataFetcher dataFetcher = new ExpensiveDataFetcher();
    private Callable<String> task = () -> dataFetcher.greet();
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

    @Test
    void should_return_result_of_wrapped_future() {
        Future<String> future = service.submit(() -> "result");
        ConsoleLogFuture<String> logFuture = new ConsoleLogFuture<>(future);

        assertEquals("result", logFuture.get());
        assertEquals("result", logFuture.get(1, TimeUnit.MILLISECONDS));
    }

    @Test
    void should_throw_exception_of_wrapped_future() {
        Future<String> future = service.submit(() -> dataFetcher.throwException(0));
        ConsoleLogFuture<String> logFuture = new ConsoleLogFuture<>(future);

        assertThrows(RuntimeException.class, () -> logFuture.get());
        assertThrows(RuntimeException.class, () -> logFuture.get(1, TimeUnit.MILLISECONDS));
    }
}