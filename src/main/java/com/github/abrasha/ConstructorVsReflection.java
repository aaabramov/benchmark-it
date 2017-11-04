package com.github.abrasha;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.lang.reflect.Constructor;
import java.util.concurrent.TimeUnit;

// Run complete.
//
// Benchmark                                            Mode  Cnt   Score     Error   Units
// ConstructorVsReflection.createPersonWithConstructor  avgt    9   5.323   ± 0.233   ns/op
// ConstructorVsReflection.createPersonWithReflection   avgt    9  12.911   ± 1.476   ns/op
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ConstructorVsReflection {
    
    private static final String name = "Andrii";
    private static final int age = 20;
    
    @State(Scope.Benchmark)
    public static class PersonConstructor {
        private final Constructor<Person> personConstructor;
        
        
        public PersonConstructor() {
            try {
                personConstructor = Person.class.getDeclaredConstructor(String.class, int.class);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("Should not happen, but as you now it is still possible: " + e.getMessage(), e);
            }
        }
    }
    
    @Benchmark
    public void createPersonWithReflection(PersonConstructor state, Blackhole bh) throws Exception {
        
        bh.consume(state.personConstructor.newInstance(name, age));
        
    }
    
    @Benchmark
    public void createPersonWithConstructor(Blackhole bh) {
        bh.consume(new Person(name, age));
        
    }
    
}
