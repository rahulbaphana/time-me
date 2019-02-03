package com.timeafunction.timers.test.data;

import java.util.Arrays;
import java.util.List;

public class ExpensiveDataFetcher {
    public List<String> fetchDataIn(long time) {
        try {
            Thread.sleep(time);
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

    public void sayHelloIn(long timeToSayHello) {
        try {
            Thread.sleep(timeToSayHello);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hello");
    }

    public String throwException(long inTime) {
        try {
            Thread.sleep(inTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("A professional exception message.");
    }
}
