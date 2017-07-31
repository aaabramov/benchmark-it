package com.github.abrasha;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

// # Run complete.
//
// Benchmark                             Mode  Cnt          Score           Error  Units
// StringConcat.concatWithStringBuffer   avgt   10     209801.407 ±      2555.216  ns/op
// StringConcat.concatWithStringBuilder  avgt   10     206855.786 ±      1913.781  ns/op
// StringConcat.concatWithStringConcat   avgt   10  259190789.987 ± 262685019.287  ns/op

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class StringConcat {
    
    @State(Scope.Benchmark)
    public static class StringToConcat {
        
        private String[] strings = IntStream.iterate(0, i -> i + 1)
                .limit(10_000)
                .mapToObj(i -> "String#" + i + "\n")
                .toArray(String[]::new);
        
    }
    
    @Benchmark
    public void concatWithStringBuilder(StringToConcat state, Blackhole bh) {
        
        StringBuilder res = new StringBuilder();
        
        for (String s : state.strings) {
            res.append(s);
        }
        
        bh.consume(res.toString());
        
    }
    
    @Benchmark
    public void concatWithStringBuffer(StringToConcat state, Blackhole bh) {
        
        StringBuffer res = new StringBuffer();
        
        for (String s : state.strings) {
            res.append(s);
        }
        
        bh.consume(res.toString());
        
    }
    
    @Benchmark
    public void concatWithStringConcat(StringToConcat state, Blackhole bh) {
        
        String res = "";
        
        for (String s : state.strings) {
            res += s;
        }
        
        bh.consume(res);
        
    }
    
}
