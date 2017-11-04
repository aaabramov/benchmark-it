# benchmark-it
Performance measurements for common code samples ![benchmark-it-build-status](https://travis-ci.org/Abrasha/benchmark-it.svg?branch=feature%2Ftravis-support)

## Adding new benchmark
Find the following official examples of using JMH: [org/openjdk/jmh/samples/](http://hg.openjdk.java.net/code-tools/jmh/file/tip/jmh-samples/src/main/java/org/openjdk/jmh/samples/)

Also you can take a look at my own measurements. The simplest is [StringConcat.java](https://github.com/Abrasha/benchmark-it/blob/master/src/main/java/com/github/abrasha/StringConcat.java). It demonstrates why we actually need classes like `StringBuilder`.


## Running benchmarks

Then you can use [run-benchmarks.sh](https://github.com/Abrasha/benchmark-it/blob/master/run-benchmarks.sh) script to execute benchmarks (You have to have maven been installed):
```bash
./run-benchmarks.sh "com.github.abrasha.StringConcat"
```

## Existing benchmarks:

Cloning VS creating new objects:
```
Benchmark                       Mode  Cnt   Score   Error  Units
CloneVsNewWithSetters.viaClone  avgt   10  10.041 ± 0.059  ns/op
CloneVsNewWithSetters.viaNew    avgt   10   7.617 ± 0.113  ns/op
```

Reflection VS direct constructor call:
```
Benchmark                                            Mode  Cnt   Score     Error   Units
ConstructorVsReflection.createPersonWithConstructor  avgt    9   5.323   ± 0.233   ns/op
ConstructorVsReflection.createPersonWithReflection   avgt    9  12.911   ± 1.476   ns/op
```

HashMap VS LinkedHashMap VS Hashtable as local property storage (10 sequential insertions):
```
Benchmark                             Mode  Cnt    Score    Error  Units
HashMapVSLinkedHashMap.hashMap        avgt    9  101.183 ±  7.116  ns/op
HashMapVSLinkedHashMap.hashtable      avgt    9  242.710 ± 88.953  ns/op
HashMapVSLinkedHashMap.linkedHashMap  avgt    9  150.633 ± 15.713  ns/op
```

String concatenation VS StringBuilder VS StringBuffer:
```
Benchmark                             Mode  Cnt          Score           Error  Units
StringConcat.concatWithStringBuffer   avgt   10     209801.407 ±      2555.216  ns/op
StringConcat.concatWithStringBuilder  avgt   10     206855.786 ±      1913.781  ns/op
StringConcat.concatWithStringConcat   avgt   10  259190789.987 ± 262685019.287  ns/op
```

Iterating collection:
```
Benchmark                             Mode  Cnt    Score    Error  Units
HashMapVSLinkedHashMap.hashMap        avgt    9  101.183 ±  7.116  ns/op
HashMapVSLinkedHashMap.hashtable      avgt    9  242.710 ± 88.953  ns/op
HashMapVSLinkedHashMap.linkedHashMap  avgt    9  150.633 ± 15.713  ns/op
```