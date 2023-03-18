/** 
* SorterClient.java
* This file implements a test client that connect to the server and test its operation
*/

package ds.assignment1;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Integer;
import java.util.Stack;

public class SorterClient {

    private Registry registry;
    private Sorter stub;

    /**
    * constructor
    */
    private SorterClient() {
        try {
            // get host stub from rmi registry
            registry = LocateRegistry.getRegistry(null);
            stub = (Sorter) registry.lookup("Sorter");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    /**
    * invoke a function from the server stub
    * according to the input command line
    *
    * @param line one of [pop,push,op,empty,dpop,hello], others ignored
    */
    public void invoke(String line) {

        // split line by white space
        String[] strings = line.split("\\s+");

        try {
            if(strings[0].equals("push")) {
                // push val
                int val = Integer.parseInt(strings[1]);
                stub.pushValue(val);
                System.out.println("Pushed: " + val);
            } else if(strings[0].equals("op")) {
                // push operator
                String op = strings[1];
                stub.pushOperator(op);
                System.out.println("Operator: " + op);
            } else if(strings[0].equals("pop")) {
                // pop
                int val = stub.pop();
                System.out.println("Poped: " + val);
            } else if(strings[0].equals("empty")) {
                // is empty?
                boolean empty = stub.isEmpty();
                System.out.println("Empty: " + empty);
            } else if(strings[0].equals("dpop")) {
                // delay pop
                int delay = Integer.parseInt(strings[1]);
                int val = stub.delayPop(delay);
                System.out.println("Poped after " + delay + "ms: " + val);
            } else if(strings[0].equals("hello")) {
                // hello world!
                String response = stub.sayHello();
                System.out.println(response);
            } else {
                System.out.println("unknown command");
            }
        } catch(Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    /**
    * main function for the client
    */
    public static void main(String[] args) {

        // input file path if there is any
        String inputPath = (args.length < 1) ? null : args[0];

        // instantiate client object
        SorterClient client = new SorterClient();

        if(inputPath != null) {
            try {
                // read from input path
                File file = new File(inputPath);
                Scanner scanner = new Scanner(file);

                // invoke command line by line
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    // invoke function
                    client.invoke(line);
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            // read from command line input
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();

            // invoke command line by line
            while(!line.equals("quit")) {
                // invoke function
                client.invoke(line);
                line = scanner.nextLine();
            }
            scanner.close();
        }
    }
}