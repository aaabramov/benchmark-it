package com.github.abrasha;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;
import java.util.concurrent.TimeUnit;

// Run complete.
//
// Benchmark                             Mode  Cnt    Score    Error  Units
// HashMapVSLinkedHashMap.hashMap        avgt    9  104.324 ± 10.539  ns/op
// HashMapVSLinkedHashMap.linkedHashMap  avgt    9  170.261 ± 45.108  ns/op

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class HashMapVSLinkedHashMap {
    
    @Benchmark
    public void hashMap(Blackhole bh) throws CloneNotSupportedException {
        
        Map<Integer, String> map = new HashMap<>();
        
        map.put(0, "zero");
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.put(6, "six");
        map.put(7, "seven");
        map.put(8, "eight");
        map.put(9, "nine");
        
        bh.consume(map);
        
    }
    
    @Benchmark
    public void linkedHashMap(Blackhole bh) {
    
        Map<Integer, String> map = new LinkedHashMap<>();
    
        map.put(0, "zero");
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.put(6, "six");
        map.put(7, "seven");
        map.put(8, "eight");
        map.put(9, "nine");
    
        bh.consume(map);
        
    }
    
}
