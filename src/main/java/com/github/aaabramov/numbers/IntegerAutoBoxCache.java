package com.github.aaabramov.numbers;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

// Run complete
//
// Benchmark                         Mode  Cnt  Score   Error  Units
// IntegerAutoBoxCache.getCached     avgt    9  2.869 ± 0.094  ns/op
// IntegerAutoBoxCache.getNonCached  avgt    9  3.997 ± 0.107  ns/op
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class IntegerAutoBoxCache {
    
    @Benchmark
    public void getCached(Blackhole bh) {
        bh.consume(Integer.valueOf(0));
    }
    
    @Benchmark
    public void getNonCached(Blackhole bh) {
        bh.consume(Integer.valueOf(49477482));
    }
    
}
