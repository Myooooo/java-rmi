/** 
* SorterServer.java
* This file implements the sorter server
*/

package ds.assignment1;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
        
public class SorterServer {
    
    /**
    * main function for the server
    */
    public static void main(String args[]) {
        try {
            SorterImplementation sorter = new SorterImplementation();
            Sorter stub = (Sorter) UnicastRemoteObject.exportObject(sorter, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            //Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Sorter", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}