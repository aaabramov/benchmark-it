package com.github.abrasha;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

// Run complete.
//
// Benchmark                                  Mode   Cnt   Score    Error  Units
// RegexMatcherVsStringMatches.regexMatcher   avgt    9   86.363 ±  3.194  ns/op
// RegexMatcherVsStringMatches.stringMatcher  avgt    9  304.525 ± 17.621  ns/op
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class RegexMatcherVsStringMatches {
    
    private static final String TARGET_STRING = "123456789";
    
    @State(Scope.Benchmark)
    public static class RegexHolder {
        private String regex = "12345.*";
        private Pattern pattern = Pattern.compile(regex);
    }
    
    @Benchmark
    public void regexMatcher(RegexHolder holder, Blackhole bh) {
        bh.consume(holder.pattern.matcher(TARGET_STRING).matches());
    }
    
    @Benchmark
    public void stringMatcher(RegexHolder holder, Blackhole bh) {
        bh.consume(TARGET_STRING.matches(holder.regex));
    }
    
}
