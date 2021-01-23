package rmi.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientService extends Remote {
    void setGap(int gap) throws RemoteException;

    void setStartIndex(int startIndex) throws RemoteException;

    void sort() throws RemoteException, InterruptedException;

    void testMessage() throws RemoteException;
}
