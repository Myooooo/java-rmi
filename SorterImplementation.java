/** 
* SorterImplementation.java
* This file implements the remote operations defined in Sorter.java
*/

package ds.assignment1;

import java.rmi.RemoteException;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import javax.swing.plaf.BorderUIResource.EmptyBorderUIResource;

import java.lang.Integer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EmptyStackException;

public class SorterImplementation implements Sorter {

    private Stack<Integer> stack;

    /**
    * constructor
    */
    public SorterImplementation() {
        // initialise stack
        stack = new Stack<Integer>();
    }

    /**
    * Obtain current timestamp
    * 
    * @return current timestamp formatted into string
    */
    public String getCurrentTimeStamp() {
        return new SimpleDateFormat("HH:mm:ss.SSS").format(new Date());
    }

    @Override
    public void pushValue(int val) {
        stack.push(val);
        System.out.println("[" + getCurrentTimeStamp() + "] Pushed: " + val);
    }

    @Override
    public void pushOperator(String operator) {

        // return if the stack is empty
        if(stack.empty()) return;

        // create temporary stack
        Stack<Integer> tmp = new Stack<Integer>();
        int val;

        // print original stack
        System.out.println("[" + getCurrentTimeStamp() + "] Original stack:" + stack);

        if(operator.equals("ascending")) {
            
            // sort original stack values in ascending order and store into temp stack
            while(!stack.empty()) {

                // pop from original stack
                val = stack.pop();

                // compare with top of temp stack
                while(!tmp.empty() && tmp.peek() > val) {
                    stack.push(tmp.pop());
                }

                tmp.push(val);
            }

            // copy from temp stack to original stack
            stack.addAll(tmp);

        } else if(operator.equals("descending")) {

            // sort original stack values in descending order and store into temp stack
            while(!stack.empty()) {

                // pop from original stack
                val = stack.pop();

                // compare with top of temp stack
                while(!tmp.empty() && tmp.peek() < val) {
                    stack.push(tmp.pop());
                }

                tmp.push(val);
            }

            // copy from temp stack to original stack
            stack.addAll(tmp);

        } else if(operator.equals("max")) {

            // initialise to minimum value
            int max = Integer.MIN_VALUE;

            while(!stack.empty()) {
                // replace max value
                val = stack.pop();
                if(val > max) max = val;
            }

            // write back to stack
            stack.push(max);

        } else if(operator.equals("min")) {
            
            // initialise to minimum value
            int min = Integer.MAX_VALUE;

            while(!stack.empty()) {
                // replace max value
                val = stack.pop();
                if(val < min) min = val;
            }

            // write back to stack
            stack.push(min);

        } else {
            operator = "(Unmodified)";
        }

        // print sorted stack
        System.out.println("[" + getCurrentTimeStamp() + "] Sorted stack " + operator + ": " + stack);
    }

    @Override
    public int pop() {
        // error handling for empty stack
        try {
            int val = stack.pop();
            System.out.println("[" + getCurrentTimeStamp() + "] Poped: " + val);
            return val;
        } catch (EmptyStackException e) {
            System.err.println("[" + getCurrentTimeStamp() + "] POP ERROR: stack is empty");
            return 0;
        }
    }

    @Override
    public boolean isEmpty() {
        return stack.empty();
    }

    @Override
    public int delayPop(int millis) {
        // sleep the thread for millis
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // error handling for empty stack
        try {
            int val = stack.pop();
            System.out.println("[" + getCurrentTimeStamp() + "] Poped after " + millis + "ms: " + val);
            return val;
        } catch (EmptyStackException e) {
            System.err.println("[" + getCurrentTimeStamp() + "] POP ERROR: stack is empty");
            return 0;
        }
    }

    @Override
    public String sayHello() {
        return "Hello World!";
    }
}