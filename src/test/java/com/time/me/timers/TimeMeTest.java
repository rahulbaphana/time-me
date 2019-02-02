package com.time.me.timers;

import com.time.me.timers.test.data.ExpensiveDataFetcher;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.time.me.timers.Timer.timeMe;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeMeTest {

    @Test
    void should_time_given_function() {
        ExpensiveDataFetcher dataFetcher = new ExpensiveDataFetcher();

        timeMe(() -> System.out.println("foo"));

        String message = timeMe(() -> dataFetcher.greet());
        List<String> someDataReturned = timeMe(() -> dataFetcher.fetchData(123));

        assertEquals("Hello World!", message);
        assertEquals("[Hi, Hello]", someDataReturned.toString());
    }
}
