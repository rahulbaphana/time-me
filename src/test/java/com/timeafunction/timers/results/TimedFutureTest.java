package com.timeafunction.timers.results;

import com.timeafunction.timers.futures.TimedFuture;
import com.timeafunction.timers.test.data.ExpensiveDataFetcher;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

class TimedFutureTest {
    private ExpensiveDataFetcher dataFetcher = new ExpensiveDataFetcher();
    private Callable<String> task = () -> dataFetcher.greet();
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

    @Test
    void should_return_result_of_wrapped_future() {
        Future<String> future = service.submit(() -> "result");
        TimedFuture<String> timedFuture = new TimedFuture<>(future);

        assertEquals("result", timedFuture.get().getResult());
        assertEquals("result", timedFuture.get(1, TimeUnit.MILLISECONDS).getResult());
    }

    @Test
    void should_throw_exception_of_wrapped_future() {
        Future<String> future = service.submit(() -> dataFetcher.throwException(0));
        TimedFuture<String> timedFuture = new TimedFuture<>(future);

        assertThrows(RuntimeException.class, () -> timedFuture.get().getResult());
        assertThrows(RuntimeException.class, () -> timedFuture.get(1, TimeUnit.MILLISECONDS).getResult());
    }
}