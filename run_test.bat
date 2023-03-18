javac -d .\ *.java
start java ds.assignment1.SorterServer
timeout /t 1
java ds.assignment1.SorterClient input.txt >output.txt
FC output.txt expected.txt
pause