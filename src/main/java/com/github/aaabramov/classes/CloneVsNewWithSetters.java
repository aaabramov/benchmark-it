package com.github.aaabramov.classes;

import com.github.aaabramov.data.Person;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

// Run complete. Total time: 00:00:58
// 
// Benchmark                                  Mode  Cnt  Score   Error  Units
// CloneVsNewWithSetters.viaClone             avgt    9  4.672 ± 0.261  ns/op
// CloneVsNewWithSetters.viaCloneConstructor  avgt    9  4.557 ± 0.135  ns/op
// CloneVsNewWithSetters.viaNew               avgt    9  4.572 ± 0.177  ns/op

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

    @Benchmark
    public void viaCloneConstructor(PersonState state, Blackhole bh) {

        Person result = new Person(state.person);
        
        bh.consume(result);

    }
    
}
