package rmi.logic;


import java.rmi.*;

public interface SortService extends Remote {

    /**
     * The name used in the RMI registry.
     */
    String SERVICE_NAME = "ProductInfoService";

    int[] sort(int[] numbers) throws RemoteException;

    int[] getSortingNumbers() throws RemoteException;

    void sendSorted(int[] sorted) throws RemoteException;
}

