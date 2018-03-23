#!/bin/sh

# you can optionally pass parameter which will be
# a selector for JMH to run specific test
# Example:
# ./run-benchmarks.sh "com.github.abrasha.classes.CloneVsNewWithSetters.viaNew"
mvn clean package
java -jar target/benchmarks.jar -wi 3 -f 3 -i 3 "$@" | tee run-results.txt