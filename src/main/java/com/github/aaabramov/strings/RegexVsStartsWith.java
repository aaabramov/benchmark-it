package com.github.aaabramov.strings;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

// Run complete.
//
// Benchmark                                    Mode   Cnt  Score   Error  Units
// RegexVsStartsWith.startsWith                 avgt    9   5.404 ± 0.188  ns/op
// RegexVsStartsWith.usingRegex                 avgt    9  88.533 ± 6.107  ns/op
// RegexVsStartsWith.usingRegexWithStartAndEnd  avgt    9  48.726 ± 1.669  ns/op
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class RegexVsStartsWith {
    
    private static final String TARGET_STRING = "123456789";
    
    @State(Scope.Benchmark)
    public static class RegexHolder {
        private Pattern startsWithPattern = Pattern.compile("12345.*");
        private Pattern startsWithPatternExt = Pattern.compile("$12345.*^");
    }
    
    @Benchmark
    public void startsWith(Blackhole bh) {
        bh.consume(TARGET_STRING.startsWith("12345"));
    }
    
    @Benchmark
    public void usingRegex(RegexHolder holder, Blackhole bh) {
        bh.consume(holder.startsWithPattern.matcher(TARGET_STRING).matches());
    }
    
    @Benchmark
    public void usingRegexWithStartAndEnd(RegexHolder holder, Blackhole bh) {
        bh.consume(holder.startsWithPatternExt.matcher(TARGET_STRING).matches());
    }
    
}
