package com.timeafunction.timers;

import com.timeafunction.timers.results.TimedResult;
import com.timeafunction.timers.test.data.ExpensiveDataFetcher;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
    void should_return_timed_result_with_exception() {
        ExpensiveDataFetcher dataFetcher = new ExpensiveDataFetcher();

        long timeToFetchData = 123L;
        TimedResult<String> result = timeMe(() -> dataFetcher.throwException(timeToFetchData));

        assertTrue(result.hasException(), "Result should hold exception");
        Exception thrownException = assertThrows(Exception.class, result::getResult, "Should throw exception");
        assertEquals("A professional exception message.", thrownException.getMessage());
    }

    @Test
    void should_return_timed_result_without_exception() throws Exception {
        ExpensiveDataFetcher dataFetcher = new ExpensiveDataFetcher();

        TimedResult<String> result = timeMe(dataFetcher::greet);

        assertFalse(result.hasException(), "Result should not hold any exception");
        assertEquals("Hello World!", result.getResult());
    }

    @Test
    void should_return_time_taken_for_future_execution_in_millis() throws Exception {
        ExpensiveDataFetcher dataFetcher = new ExpensiveDataFetcher();

        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<TimedResult<String>> future1 = ResultTimer.timeMe(service.submit(dataFetcher::greet));
        Future<TimedResult<String>> future2= ResultTimer.timeMe(service.submit(dataFetcher::greet));

        TimedResult<String> result1 = future1.get();
        assertEquals("Hello World!", result1.getResult());
        assertTrue(result1.getTimeTakenInMillis() >= 1);

        TimedResult<String> result2 = future2.get();
        assertEquals("Hello World!", result2.getResult());
        assertTrue(result2.getTimeTakenInMillis() >= 1);
    }
}
