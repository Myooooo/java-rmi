# java-rmi
A simple JAVA RMI system

Compile the files
```bash
$javac -d ./ *.java
```

### Run automated test scripts

For one client
```bash
$./run_test.sh
```

For multiple clients
```bash
$./run_test_multiple.sh
```

### Test manually

Start RMI registry
```bash
$rmiregistry &
```

If port 1099 alreadly used, try with other ports
```bash
$rmiregistry PORT &
```

Start the server
```bash
$java ds.assignment1.SorterServer &
```

To start the client with command line input testing
```bash
$java ds.assignment1.SorterClient
```
Supported commands: 
- pop: pop a value from stack
- dpop val: pop after val ms
- push val: push val onto the stack
- op cmd: perform an operation, one of [ascending, descending, max, min]
- empty: check if stack is emptu
- hello: Hello World!

To start the client with a test file at PATH
```bash
$java ds.assignment1.SorterClient PATH
```

To test with sample input amd compare with expected outputs
```bash
$java ds.assignment1.SorterClient input.txt >output.txt
$diff -w output.txt expected.txt
```
