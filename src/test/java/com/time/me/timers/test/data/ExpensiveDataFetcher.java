package com.time.me.timers.test.data;

import java.util.Arrays;
import java.util.List;

public class ExpensiveDataFetcher {
    public List<String> fetchData(Integer data) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Arrays.asList("Hi", "Hello");
    }

    public String greet() {
        try {
            Thread.sleep(1234);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello World!";
    }
}
