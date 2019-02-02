package com.time.me.timers;

import com.time.me.timers.test.data.ExpensiveDataFetcher;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeMeTest {
    @Test
    void should_time_given_function() {
        ExpensiveDataFetcher dataFetcher = new ExpensiveDataFetcher();

        Timer.timeMe(() -> System.out.println("foo"));
        String message = Timer.timeMe(dataFetcher::greet);
        List<String> someDataReturned = Timer.timeMe(() -> dataFetcher.fetchData(123));

        assertEquals("Hello World!", message);
        assertEquals("[Hi, Hello]", someDataReturned.toString());
    }
}
