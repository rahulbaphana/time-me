package com.time.me.timers;

import com.time.me.timers.results.TimedResult;
import com.time.me.timers.test.data.ExpensiveDataFetcher;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.time.me.timers.ResultTimer.timeMe;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResultTimerTest {
    @Test
    void should_return_time_taken_for_a_void_function_in_millis() {
        ExpensiveDataFetcher dataFetcher = new ExpensiveDataFetcher();

        long timeToSayHello = 10l;
        TimedResult<Void> result = timeMe(() -> dataFetcher.sayHelloIn(timeToSayHello));

        assertTrue(result.getTimeTakenInMillis() >= timeToSayHello);
    }

    @Test
    void should_return_time_taken_for_a_non_void_function_in_millis() {
        ExpensiveDataFetcher dataFetcher = new ExpensiveDataFetcher();

        long timeToFetchData = 123l;
        TimedResult<List<String>> result = ResultTimer.timeMe(() -> dataFetcher.fetchDataIn(timeToFetchData));

        assertTrue(result.getTimeTakenInMillis() >= timeToFetchData);
    }
}
