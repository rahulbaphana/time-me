package com.timeafunction.timers;

import com.timeafunction.timers.test.data.ExpensiveDataFetcher;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleTimerTest {
    @Test
    void should_print_to_console_time_taken_by_any_function_in_millis() {
        ExpensiveDataFetcher dataFetcher = new ExpensiveDataFetcher();

        ConsoleTimer.timeMe(() -> System.out.println("foo"));
        String message = ConsoleTimer.timeMe(dataFetcher::greet);
        List<String> someDataReturned = ConsoleTimer.timeMe(() -> dataFetcher.fetchDataIn(123L));

        assertEquals("Hello World!", message);
        assertEquals("[Hi, Hello]", someDataReturned.toString());
    }
}
