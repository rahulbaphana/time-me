package com.timeafunction.timers;

import com.timeafunction.timers.results.TimedResult;
import com.timeafunction.timers.test.data.ExpensiveDataFetcher;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.timeafunction.timers.ResultTimer.timeMe;
import static org.junit.jupiter.api.Assertions.*;

class ResultTimerTest {
    @Test
    void should_return_time_taken_for_a_void_function_in_millis() {
        ExpensiveDataFetcher dataFetcher = new ExpensiveDataFetcher();

        long timeToSayHello = 10L;
        TimedResult result = timeMe(() -> dataFetcher.sayHelloIn(timeToSayHello));

        assertTrue(result.getTimeTakenInMillis() >= timeToSayHello);
    }

    @Test
    void should_return_time_taken_for_a_non_void_function_in_millis() {
        ExpensiveDataFetcher dataFetcher = new ExpensiveDataFetcher();

        long timeToFetchData = 123L;
        TimedResult<List<String>> result = ResultTimer.timeMe(() -> dataFetcher.fetchDataIn(timeToFetchData));

        assertTrue(result.getTimeTakenInMillis() >= timeToFetchData);
    }

    @Test
    void should_return_time_taken_for_a_non_void_function_when_exception_is_thrown() {
        ExpensiveDataFetcher dataFetcher = new ExpensiveDataFetcher();

        long timeToFetchData = 123L;
        TimedResult<String> result = timeMe(() -> dataFetcher.throwException(timeToFetchData));

        assertTrue(result.getTimeTakenInMillis() >= timeToFetchData);
    }

    @Test
    void should_return_result_with_exception() {
        ExpensiveDataFetcher dataFetcher = new ExpensiveDataFetcher();

        long timeToFetchData = 123L;
        TimedResult<String> result = timeMe(() -> dataFetcher.throwException(timeToFetchData));

        assertTrue(result.hasException(), "Result should hold exception");
        Exception thrownException = assertThrows(Exception.class, result::getResult, "Should throw exception");
        assertEquals("A professional exception message.", thrownException.getMessage());
    }
}
