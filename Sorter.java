/** 
* Sorter.java
* This file defines the remote operation interfaces that is to be implemented by the remote service. 
*/

package ds.assignment1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Sorter extends Remote {
    /** 
    * Takes val and push it on to the top of the stack
    * 
    * @param val an integer to be pushed
    */
    void pushValue(int val) throws RemoteException;

    /** 
    * This method will push a String containing an operator 
    * ("ascending", "descending", "max", "min") to the stack, 
    * which will cause the server to pop all the values on the stack and: 
    * i) for ascending operation - push all the popped values in the descending order; 
    * ii) for descending - push all the popped values in ascending order; 
    * iii) for max - push the max value; 
    * iv) for min - push the min value of all the popped values.
    * 
    * @param operator one of ("ascending", "descending", "max", "min")
    */
    void pushOperator(String operator) throws RemoteException;

    /** 
    * This method will pop the top of the stack and return it to the client
    * @return the value at the top of the stack
    */
    int pop() throws RemoteException;

    /** 
    * This method will return true if the stack is empty, false otherwise
    * @return true if the stack is empty
    */
    boolean isEmpty() throws RemoteException;

    /** 
    * This method will wait millis milliseconds before carrying out the pop operation as above
    * 
    * @param millis time in milliseconds
    * @return the value at the top of the stack
    */
    int delayPop(int millis) throws RemoteException;

    /** 
    * This method is for testing purpose
    * 
    * @return some string
    */
    String sayHello() throws RemoteException;
}