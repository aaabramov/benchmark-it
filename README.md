# benchmark-it
Performance measurements for common code samples

Existing benchmarks:

Cloning VS creating new objects
```
// Benchmark                       Mode  Cnt   Score   Error  Units
// CloneVsNewWithSetters.viaClone  avgt   10  10.041 ± 0.059  ns/op
// CloneVsNewWithSetters.viaNew    avgt   10   7.617 ± 0.113  ns/op
```

Reflection VS direct constructor call
```
// Benchmark                                            Mode  Cnt   Score     Error   Units
// ConstructorVsReflection.createPersonWithConstructor  avgt    9   5.323   ± 0.233   ns/op
// ConstructorVsReflection.createPersonWithReflection   avgt    9  12.911   ± 1.476   ns/op
```

HashMap VS LinkedHashMap VS Hashtable as local property storage (10 sequential insertions)
```
// Benchmark                             Mode  Cnt    Score    Error  Units
// HashMapVSLinkedHashMap.hashMap        avgt    9  101.183 ±  7.116  ns/op
// HashMapVSLinkedHashMap.hashtable      avgt    9  242.710 ± 88.953  ns/op
// HashMapVSLinkedHashMap.linkedHashMap  avgt    9  150.633 ± 15.713  ns/op
```

String concatenation VS StringBuilder VS StringBuffer
```
// Benchmark                             Mode  Cnt          Score           Error  Units
// StringConcat.concatWithStringBuffer   avgt   10     209801.407 ±      2555.216  ns/op
// StringConcat.concatWithStringBuilder  avgt   10     206855.786 ±      1913.781  ns/op
// StringConcat.concatWithStringConcat   avgt   10  259190789.987 ± 262685019.287  ns/op
```