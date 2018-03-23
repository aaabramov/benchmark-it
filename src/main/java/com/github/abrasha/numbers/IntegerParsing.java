package com.github.abrasha.numbers;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

// Run complete
//
// Benchmark                      Mode   Cnt    Score   Error  Units
// IntegerParsing.integerParse    avgt    9    23.314 ± 1.402  ns/op
// IntegerParsing.integerValueOf  avgt    9    25.892 ± 0.758  ns/op
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class IntegerParsing {
    
    @State(Scope.Benchmark)
    public static class NumberHolder {
        
        private String num = "95857331";
        
    }
    
    @Benchmark
    public void integerParse(NumberHolder state, Blackhole bh) {
        bh.consume(Integer.parseInt(state.num));
    }
    
    @Benchmark
    public void integerValueOf(NumberHolder state, Blackhole bh) {
        bh.consume(Integer.valueOf(state.num));
    }
    
}
