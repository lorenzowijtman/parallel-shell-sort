package rmi.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientService extends Remote {
    public void setGap(int gap) throws RemoteException;
    public void setStartIndex(int startIndex) throws RemoteException;
    public void sort() throws RemoteException, InterruptedException;
    void testMessage() throws RemoteException;
}
