#!/bin/bash

javac src/Parking.java || exit 1

for test in tests/input*.txt
do
    base=$(basename "$test" .txt)
    number=${base#input}

    expected="tests/expected${number}.txt"
    actual="tests/actual${number}.txt"

    java -cp src Parking "$test" > "$actual"

    if diff -q "$actual" "$expected" > /dev/null; then
        echo "Test ${number} passed"
    else
        echo "Test ${number} failed"
        diff "$expected" "$actual"
    fi
done
