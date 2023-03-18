#!/bin/bash

echo Starting RMI registry...
pkill -f "rmiregistry"
rmiregistry &
sleep 1

echo Starting sorter server...
java ds.assignment1.SorterServer &
sleep 1

echo Starting sorter client 1...
java ds.assignment1.SorterClient input.txt >/dev/null & 

echo Starting sorter client 2...
java ds.assignment1.SorterClient input.txt >/dev/null & 

echo Starting sorter client 3...
java ds.assignment1.SorterClient input.txt >output.txt

echo Test completed
echo Comparing output with expected...
diff output.txt expected.txt >test_result.txt
echo Comparison completed
echo Results stored in test_result.txt
cat test_result.txt
pkill -f "java"