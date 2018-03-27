# benchmark-it
Performance measurements for common code samples ![benchmark-it-build-status](https://travis-ci.org/Abrasha/benchmark-it.svg?branch=feature%2Ftravis-support)

## Adding new benchmark
Find the following official examples of using JMH: [org/openjdk/jmh/samples/](http://hg.openjdk.java.net/code-tools/jmh/file/tip/jmh-samples/src/main/java/org/openjdk/jmh/samples/)

Also you can take a look at my own measurements. The simplest is [StringConcat.java](https://github.com/Abrasha/benchmark-it/blob/master/src/main/java/com/github/abrasha/strings/StringConcat.java). It demonstrates why we actually need classes like `StringBuilder`.


## Running benchmarks

Then you can use [run-benchmarks.sh](https://github.com/Abrasha/benchmark-it/blob/master/run-benchmarks.sh) script to execute benchmarks (You need to have maven been installed):
```bash
./run-benchmarks.sh "com.github.abrasha.strings.StringConcat"
```

## Existing benchmarks:

Cloning v. creating new objects:
```
Benchmark                       Mode  Cnt   Score   Error  Units
CloneVsNewWithSetters.viaClone  avgt   10  10.041 ± 0.059  ns/op
CloneVsNewWithSetters.viaNew    avgt   10   7.617 ± 0.113  ns/op
```

Reflection v. direct constructor call:
```
Benchmark                                            Mode  Cnt   Score     Error   Units
ConstructorVsReflection.createPersonWithConstructor  avgt    9   5.323   ± 0.233   ns/op
ConstructorVsReflection.createPersonWithReflection   avgt    9  12.911   ± 1.476   ns/op
```

HashMap v. LinkedHashMap v. Hashtable as local property storage (10 sequential insertions):
```
Benchmark                             Mode  Cnt    Score    Error  Units
HashMapVSLinkedHashMap.hashMap        avgt    9  101.183 ±  7.116  ns/op
HashMapVSLinkedHashMap.hashtable      avgt    9  242.710 ± 88.953  ns/op
HashMapVSLinkedHashMap.linkedHashMap  avgt    9  150.633 ± 15.713  ns/op
```

String concatenation v. StringBuilder v. StringBuffer:
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

Should we do manual boxing / unboxing?

```
Benchmark                          Mode   Cnt  Score   Error  Units
AutoboxingVsManual.autoUnboxing    avgt    9   2.338 ± 0.047  ns/op
AutoboxingVsManual.manualUnboxing  avgt    9   2.316 ± 0.088  ns/op
```

How we should parse `int` from `String`?
```
Benchmark                      Mode   Cnt    Score   Error  Units
IntegerParsing.integerParse    avgt    9    23.314 ± 1.402  ns/op
IntegerParsing.integerValueOf  avgt    9    25.892 ± 0.758  ns/op
```

`Integer.valueOf(int)` for cached `Integer`s.
```
Benchmark                         Mode  Cnt  Score   Error  Units
IntegerAutoBoxCache.getCached     avgt    9  2.869 ± 0.094  ns/op
IntegerAutoBoxCache.getNonCached  avgt    9  3.997 ± 0.107  ns/op
```

Proxy v. Non-Proxy class:
```
Benchmark                 Mode   Cnt  Score   Error  Units
ProxyVsNonProxy.nonProxy  avgt    9  72.668 ± 4.351  ns/op
ProxyVsNonProxy.proxy     avgt    9  71.439 ± 1.857  ns/op
```

`String#startsWith(xxx)` v. `Pattern.compile("xxx.*")`:
```
Benchmark                                    Mode   Cnt  Score   Error  Units
RegexVsStartsWith.startsWith                 avgt    9   5.404 ± 0.188  ns/op
RegexVsStartsWith.usingRegex                 avgt    9  88.533 ± 6.107  ns/op
RegexVsStartsWith.usingRegexWithStartAndEnd  avgt    9  48.726 ± 1.669  ns/op
```

`String#matches(xxx)` v. `Pattern.compile(xxx).matcher(yyy).matches()`
```
Benchmark                                  Mode   Cnt   Score    Error  Units
RegexMatcherVsStringMatches.regexMatcher   avgt    9   86.363 ±  3.194  ns/op
RegexMatcherVsStringMatches.stringMatcher  avgt    9  304.525 ± 17.621  ns/op
```

Creating new `Cipher` v. using `ThreadLocal<Cipher>`
```
Benchmark                       Mode  Cnt     Score     Error  Units
CryptoInit.withAvailableCipher  avgt   30  1795.563 ±  37.173  ns/op
CryptoInit.withNewCipher        avgt   30  6979.507 ± 144.121  ns/op
```