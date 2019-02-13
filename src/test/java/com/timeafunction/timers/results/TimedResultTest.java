package com.timeafunction.timers.results;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimedResultTest {
    @Test
    void should_give_result_without_exception() throws Exception {
        TimedResult<String> timedResult = new TimedResult<>("Hello World!", 1000L);

        assertEquals("Hello World!",timedResult.getResult());
    }

    @Test
    void should_throw_exception_when_result_holds_exception() {
        TimedResult<String> timedResult = new TimedResult<String>(new Exception("Something broke!"), 1000L);

        Exception exceptionResult = assertThrows(Exception.class, () -> timedResult.getResult(), "should throw exception");
        assertEquals("Something broke!", exceptionResult.getMessage());
    }

    @Test
    void has_exception() {
        TimedResult<String> timedResult = new TimedResult<String>(new Exception("Something broke!"), 1000L);

        assertTrue(timedResult.hasException());
    }

    @Test
    void has_no_exception() {
        TimedResult<String> timedResult = new TimedResult<>(null, 1000L);

        assertFalse(timedResult.hasException());
    }
}