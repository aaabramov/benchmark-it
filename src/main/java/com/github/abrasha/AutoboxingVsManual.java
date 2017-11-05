package com.github.abrasha;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

// Run complete
//
// Benchmark                          Mode   Cnt  Score   Error  Units
// AutoboxingVsManual.autoUnboxing    avgt    9   2.338 ± 0.047  ns/op
// AutoboxingVsManual.manualUnboxing  avgt    9   2.316 ± 0.088  ns/op
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class AutoboxingVsManual {
    
    @State(Scope.Benchmark)
    public static class BoxedIntHolder {
        private Integer num = Integer.valueOf(100000);
    }
    
    @Benchmark
    public void autoUnboxing(BoxedIntHolder holder, Blackhole bh) {
        consume(bh, holder.num);
    }
    
    @Benchmark
    public void manualUnboxing(BoxedIntHolder holder, Blackhole bh) {
        consume(bh, holder.num.intValue());
    }
    
    private void consume(Blackhole bh, int number) {
        bh.consume(number);
    }
    
}
