package com.github.abrasha;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

//# Run complete. Total time: 00:01:00
//
//  Benchmark                       Mode  Cnt   Score   Error  Units
//  CloneVsNewWithSetters.viaClone  avgt   10  10.041 ± 0.059  ns/op
//  CloneVsNewWithSetters.viaNew    avgt   10   7.617 ± 0.113  ns/op

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class CloneVsNewWithSetters {
    
    @State(Scope.Benchmark)
    public static class PersonState {
        private Person person = new Person("James", 30);
    }
    
    @Benchmark
    public void viaClone(PersonState state, Blackhole bh) throws CloneNotSupportedException {
        
        bh.consume(state.person.clone());
        
    }
    
    @Benchmark
    public void viaNew(PersonState state, Blackhole bh) {
        
        Person result = new Person();
        result.setName(state.person.getName());
        result.setAge(state.person.getAge());
        
        bh.consume(result);
        
    }
    
}
