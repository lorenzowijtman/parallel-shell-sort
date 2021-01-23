package rmi.client;

import rmi.server.ServerImpl;
import rmi.server.ServerService;

import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class SortInfoClient  {

   private static final int PORT = 1099;
   private static Registry registry;
   private static ClientService service;

    static {
        try {
            registry = LocateRegistry.getRegistry(PORT);
            service = new SequentialShellSort();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    protected SortInfoClient() throws RemoteException {
        super();
    }

    public void startClient() {
        try {
            ServerService sortInfo = (ServerService) Naming.lookup(ServerService.SERVICE_NAME);

            sortInfo.registerClient(service);
            System.out.println("Waiting... Type anything to close client.");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();

            sortInfo.unregisterClient(service);
            System.exit(0);
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