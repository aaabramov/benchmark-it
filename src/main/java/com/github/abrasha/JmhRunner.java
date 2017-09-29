package com.github.abrasha;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class JmhRunner {
    
    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder()
                .warmupIterations(3)
                .measurementIterations(3)
                .forks(3)
                .build();
        
        new Runner(options).run();
    }
    
}
