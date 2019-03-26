***

<div align="center">
    <b><em>Time Me</em></b><br>
    A simple java functions timer;
</div>

<div align="center">

[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)
[![Coverage](https://codecov.io/gh/rahulbaphana/time-me/branch/master/graph/badge.svg)](https://codecov.io/gh/rahulbaphana/time-me)
[![Build Status](https://travis-ci.com/rahulbaphana/time-me.svg?branch=master)](https://travis-ci.com/rahulbaphana/time-me)

</div>

***

## Table of contents:
* [Motivation](https://github.com/rahulbaphana/time-me#motivation)
* [Latest news](https://github.com/rahulbaphana/time-me#latest-news)
* [What is time-me?](https://github.com/rahulbaphana/time-me#what-is-time-me)
* [Core features](https://github.com/rahulbaphana/time-me#core-features)
* [Project setup after cloning](https://github.com/rahulbaphana/time-me#project-setup-after-cloning)
* [Example](https://github.com/rahulbaphana/time-me#example)
    * [Console Timer](https://github.com/rahulbaphana/time-me#1-console-timer)
    * [Result Timer](https://github.com/rahulbaphana/time-me#2-result-timer)
* [Contributors](https://github.com/rahulbaphana/time-me#contributors)
* [License](https://github.com/rahulbaphana/time-me#license)


## Motivation
Below code snippet in all the code base I worked on made me write this library:
```
long startTime = System.currentTimeMillis();
object.doExpensiveOperation();
System.out.println("Time taken :: " + (System.currentTimeMillis() - startTime) + " millis");
```

## Latest news
* Recent Releases:
    * Timing futures has been released [here](https://github.com/rahulbaphana/time-me/releases/tag/v0.1)
* Keep an eye on this, loads of features coming soon :
    * Timing in nano-seconds
    
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
 
## Project setup after cloning
* Build 
```
$ ./gradlew build
```

* Run tests
```
$ ./gradlew test
```

* Generate java docs
```
$ ./gradlew javadoc
```
Open java doc in browser from location : 
```
<PATH_TO_PROJECT>/build/docs/javadoc/index.html
```
 
## Example

### First, choose the type of timer you need..

#### 1. Console Timer

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
To time a [Future](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html):
```java
ExecutorService service = Executors.newSingleThreadExecutor();
Future<String> future = ConsoleTimer.timeMe(service.submit(() -> new DataFetcher().greet())));
future.get();
```

#### 2. Result Timer

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
A [Future](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html) can be timed as:
```java
ExecutorService service = Executors.newSingleThreadExecutor();
Future<TimedResult<String>> future = ResultTimer.timeMe(service.submit(() -> new DataFetcher().greet())));
TimedResult<String> result = future.get();
```
## Contributors
* [Rahul Baphana](https://github.com/rahulbaphana)
* [Mausam Yede](https://github.com/mausamyede) 
* [Sojjwal Kelkar](https://github.com/sskelkar)

## License
MIT License

Copyright (c) 2019 Rahul Baphana

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.