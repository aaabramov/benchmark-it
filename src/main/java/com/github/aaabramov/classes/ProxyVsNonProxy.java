package com.github.aaabramov.classes;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.concurrent.TimeUnit;


// Run complete
//
// Benchmark                 Mode   Cnt  Score   Error  Units
// ProxyVsNonProxy.nonProxy  avgt    9  72.668 ± 4.351  ns/op
// ProxyVsNonProxy.proxy     avgt    9  71.439 ± 1.857  ns/op
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ProxyVsNonProxy {
    
    private static final String INPUT = "Some random string";
    
    @State(Scope.Benchmark)
    public static class ProxyHolder {
        private final ToUpperCaser uppercaser = String::toUpperCase;
        
        private final InvocationHandler handler = (proxy, method, args) -> uppercaser.toUpperCase((String) args[0]);
        
        private final ToUpperCaser proxy = (ToUpperCaser) Proxy.newProxyInstance(
                uppercaser.getClass().getClassLoader(), new Class[] {ToUpperCaser.class}, handler
        );
    }
    
    @Benchmark
    public void nonProxy(ProxyHolder holder, Blackhole bh) {
        bh.consume(holder.uppercaser.toUpperCase(INPUT));
    }
    
    @Benchmark
    public void proxy(ProxyHolder holder, Blackhole bh) {
        bh.consume(holder.proxy.toUpperCase(INPUT));
    }
    
    @FunctionalInterface
    interface ToUpperCaser {
        String toUpperCase(String s);
    }
    
}
