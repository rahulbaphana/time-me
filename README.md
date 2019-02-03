***

<div align="center">
    <b><em>Time Me</em></b><br>
    The simple java methods timer;
</div>

<div align="center">

[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)
[![Coverage](https://codecov.io/gh/rahulbaphana/time-me/branch/master/graph/badge.svg)](https://codecov.io/gh/rahulbaphana/time-me)
[![Build Status](https://travis-ci.com/rahulbaphana/time-me.svg?branch=master)](https://travis-ci.com/rahulbaphana/time-me)

</div>

***

## Latest news
* Keep an eye on this, loads of features coming soon :
    * Timing inside futures
    * Timing in nano-seconds
    * Allowing timing messages to be used

and many more...

## What is time-me? 
* This small library helps time a java function.
* Checking timing for functions where there are no frameworks involved inspired to write this library.
* This library also allows not just time ```void``` functions but also functions that return ```result``` and also retains result after execution.

## Core features
 * Lightweight library and easy to learn API
 * Timing ```void``` functions
 * Timing ```result``` oriented functions
 * Timing in milliseconds
 * Console printer timer with/without results
 * Non-console printer timer with/without results 
 
## Example

### First, choose the type of timer you need..

#### 1. Console Timer:

##### Below class[```DataFetcher```] function[```fetchDataIn(long time)```] needs to be tested for how much time it took to fetch:
```java
public class DataFetcher {
    public List<String> fetchDataIn(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Arrays.asList("Hi", "Hello");
    }
    
    public void greet() {
       System.out.println("Hello!");
    }
}
```
Timing the above function ```fetchDataIn(..)``` will look as below:
```java
List<String> someDataFetched = ConsoleTimer.timeMe(() -> new DataFetcher().fetchDataIn(1000));
```
The output on console will look as below:
```
Timed result :: TimedResult{result=[Hi, Hello], timeTakenInMillis=1003 millis}
```
The same can be used on ```void``` functions as below:
```java
ConsoleTimer.timeMe(() -> new DataFetcher().greet());
```

#### 2. Result Timer:

##### Below class[```DataFetcher```] function[```fetchDataIn(long time)```] needs to be tested for how much time it took to fetch:
```java
public class DataFetcher {
    public List<String> fetchDataIn(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Arrays.asList("Hi", "Hello");
    }
    
    public void greet() {
       System.out.println("Hello!");
    }
}
```
Timing the above function ```fetchDataIn(..)``` will look as below:
```java
TimedResult<List<String>> timedResult = ResultTimer.timeMe(() -> new DataFetcher().fetchDataIn(1000));
System.out.println("The function took :: "+timedResult.getTimeTakenInMillis()+ " millis")
System.out.println("The function result :: "+timedResult.getResult().toString())
```
The output on console will look as below:
```
The function took :: 1003 millis
The function result :: [Hi, Hello]
```
The same can be used on ```void``` functions as below:
```java
TimedResult result = ResultTimer.timeMe(() -> new DataFetcher().greet());
System.out.println("The function took :: "+timedResult.getTimeTakenInMillis()+ " millis")
```
The output on console will look as below:```
```java
The function took :: 3 millis
```
