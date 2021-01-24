package rmi.server;


import rmi.client.ClientService;

import java.rmi.*;

public interface ServerService extends Remote {
    /**
     * The name used in the RMI registry.
     */
    String SERVICE_NAME = "ProductInfoService";

    void registerClient(ClientService sortClient) throws RemoteException;

    void unregisterClient(ClientService service) throws RemoteException;

    void testClients() throws RemoteException;

    void sort(int[] list) throws RemoteException;

    void setSortedArr(int index, int value) throws RemoteException;

    int getSortedArrayIndex(int index) throws RemoteException;

    int getSortedArrayLength() throws RemoteException;
}

