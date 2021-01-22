package rmi.client;

import rmi.logic.SortService;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SortInfoClient {

    protected SortInfoClient() throws RemoteException {
        super();
    }

    public void startClient() {
        try {
            SortService sortInfo = (SortService) Naming.lookup(SortService.SERVICE_NAME);

            int[] sorted = sortInfo.sort(sortInfo.getSortingNumbers());
            sortInfo.sendSorted(sorted);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("You probably forgot to start the server...");
        }
    }

    public static void main(String[] args) {
        try {
            SortInfoClient client = new SortInfoClient();
            client.startClient();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}