package rmi.server;

import rmi.client.ClientService;
import rmi.client.input.InputGenerator;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerImpl extends UnicastRemoteObject implements ServerService {
    private final List<ClientService> sortingClients = new ArrayList<>();

    public ServerImpl() throws RemoteException {
        super();
    }

    @Override
    public void registerClient(ClientService sortClient) throws RemoteException {
        sortingClients.add(sortClient);
        System.out.println("New client registered. Current clients: " + sortingClients.size());
    }

    @Override
    public void unregisterClient(ClientService service) throws RemoteException {
        sortingClients.remove(service);
        System.out.println("Client unregistered. Current clients: " + sortingClients.size());
    }

    @Override
    public void testClients() throws RemoteException {
        System.out.println("Executing message on clients...");

        sortingClients.forEach(clientService -> {
            try {
                clientService.testMessage();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

    private volatile int[] sorted = {};

    @Override
    public void setSortedArr(int index, int value) throws RemoteException {
        sorted[index] = value;
    }

    @Override
    public int getSortedArrayIndex(int index) throws RemoteException {
        return sorted[index];
    }

    @Override
    public int getSortedArrayLength() throws RemoteException {
        return sorted.length;
    }

    @Override
    public void sort(int[] list) {
        System.out.println("Setting list to volatile list");
        this.sorted = list;

        try {
            final int amountOfClients = sortingClients.size();

            for (int i = 0; i < amountOfClients; i++) {
                sortingClients.get(i).setStartIndex(i);
            }

            System.out.println("setting gaps");
            for (int i = amountOfClients; i >= 1; i--) {
                for (int k = 0; k < 1; k++) {
                    sortingClients.get(k).setGap(i);
                }
            }
            System.out.println("done setting gaps");

            sortingClients.forEach(sortingClient -> {
                try {
                    sortingClient.sort();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });

            for (int x: sorted) {
                System.out.print(x + ", ");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

//
//class OneShotTask implements Runnable {
//    final ClientService client;
//
//    OneShotTask(ClientService client) {this.client = client;}
//
//    @Override
//    public void run() {
//        try {
//            client.sort();
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }
//}
