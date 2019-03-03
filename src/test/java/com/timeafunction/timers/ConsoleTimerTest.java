package com.timeafunction.timers;

import com.timeafunction.timers.test.data.AsyncDataFetcher;
import com.timeafunction.timers.test.data.ExpensiveDataFetcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConsoleTimerTest {
    @Test
    void should_print_to_console_time_taken_by_any_function_in_millis() throws Exception {
        ExpensiveDataFetcher dataFetcher = new ExpensiveDataFetcher();

        ConsoleTimer.timeMe(() -> System.out.println("foo"));
        String message = ConsoleTimer.timeMe(dataFetcher::greet);
        List<String> someDataReturned = ConsoleTimer.timeMe(() -> dataFetcher.fetchDataIn(123L));

        assertEquals("Hello World!", message);
        assertEquals("[Hi, Hello]", someDataReturned.toString());
    }

    @Test
    void should_print_to_console_time_taken_by_function_that_throws_exception_in_millis() {
        ExpensiveDataFetcher dataFetcher = new ExpensiveDataFetcher();

        Executable executableBlockException = () -> ConsoleTimer.timeMe(() -> dataFetcher.throwException(100l));

        Exception runtimeException = assertThrows(Exception.class, executableBlockException, "Should throw runtime exception!");
        assertEquals("A professional exception message.", runtimeException.getMessage());
    }

    @Test
    void should_print_to_console_time_taken_by_future_execution_in_millis() throws Exception {
        AsyncDataFetcher dataFetcher = new AsyncDataFetcher();

        assertEquals("something async", ConsoleTimer.timeMe(dataFetcher.fetchAsync()).get());
        assertThrows(TimeoutException.class, () -> ConsoleTimer.timeMe(dataFetcher.fetchAsync()).get(200, MILLISECONDS));
    }
}
