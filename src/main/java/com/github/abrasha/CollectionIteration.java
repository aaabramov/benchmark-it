package com.github.abrasha;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

// Run complete

// Benchmark                                    Mode   Cnt     Score       Error    Units
// CollectionIteration.withForEachMethods       avgt    9    59121.808 ±  2069.414  ns/op
// CollectionIteration.withForEachSyntax        avgt    9    51944.153 ±  8074.440  ns/op
// CollectionIteration.withGetByIndex           avgt    9    42642.811 ±  9273.787  ns/op
// CollectionIteration.withGetByIndexCacheSize  avgt    9    44441.258 ± 11430.206  ns/op
// CollectionIteration.withGetByIndexReverse    avgt    9    43631.495 ± 10928.136  ns/op
// CollectionIteration.withIterator             avgt    9    44387.451 ±  9110.722  ns/op
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class CollectionIteration {
    
    @State(Scope.Benchmark)
    public static class StringsToIterate {
        
        private List<String> strings = IntStream.iterate(0, i -> i + 1)
                .limit(10_000)
                .mapToObj(i -> "String#" + i + "\n")
                .collect(toList());
        
    }
    
    @Benchmark
    public void withForEachSyntax(StringsToIterate strings, Blackhole bh) {
        for (String string : strings.strings) bh.consume(string);
    }
    
    @Benchmark
    public void withForEachMethods(StringsToIterate strings, Blackhole bh) {
        strings.strings.forEach(bh::consume);
    }
    
    @Benchmark
    public void withIterator(StringsToIterate strings, Blackhole bh) {
        Iterator<String> it = strings.strings.iterator();
        while (it.hasNext()){
            bh.consume(it.next());
        }
    }
    
    @Benchmark
    public void withGetByIndex(StringsToIterate strings, Blackhole bh) {
        List<String> list = strings.strings;
        for (int i = 0; i < list.size(); i++) bh.consume(list.get(i));
    }
    
    @Benchmark
    public void withGetByIndexCacheSize(StringsToIterate strings, Blackhole bh) {
        List<String> list = strings.strings;
        int size = list.size();
        for (int i = 0; i < size; i++) bh.consume(list.get(i));
    }
    
    @Benchmark
    public void withGetByIndexReverse(StringsToIterate strings, Blackhole bh) {
        List<String> list = strings.strings;
        int size = list.size() - 1;
        for (int i = size; i >= 0; i--) bh.consume(list.get(i));
    }
    
    @Benchmark
    public void withSequentialStream(StringsToIterate strings, Blackhole bh) {
        strings.strings.stream().forEach(bh::consume);
    }
    
    @Benchmark
    public void withParallelStream(StringsToIterate strings, Blackhole bh) {
        strings.strings.stream().forEach(bh::consume);
    }
    
}
