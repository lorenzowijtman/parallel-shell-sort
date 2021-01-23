package rmi.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientService extends Remote {
    public int[] sort(int[] sort) throws RemoteException;

    void testMessage() throws RemoteException;
}
