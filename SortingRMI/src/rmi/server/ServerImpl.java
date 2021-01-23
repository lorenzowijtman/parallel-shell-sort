package rmi.server;

import rmi.client.ClientService;
import rmi.client.SequentialShellSort;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

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
    public void executeClients() throws RemoteException {
        sortingClients.forEach(clientService -> {
            try {
                clientService.testMessage();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }
}
