package rmi.server;


import rmi.client.ClientService;
import rmi.client.SequentialShellSort;

import java.rmi.*;

public interface ServerService extends Remote {
    /**
     * The name used in the RMI registry.
     */
    String SERVICE_NAME = "ProductInfoService";

    void registerClient(ClientService sortClient) throws RemoteException;
    void unregisterClient(ClientService service) throws RemoteException;
    void executeClients() throws RemoteException;


}

