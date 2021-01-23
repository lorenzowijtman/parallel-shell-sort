package rmi.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class SortInfoServer {

    public static void main(String[] args) {
        startServer();
    }

    private static final int PORT = 1099;
    private static Registry registry;
    private static ServerService service;

    static {
        try {
            registry = LocateRegistry.createRegistry(PORT);
            service = new ServerImpl();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void startServer() {
        System.out.println("Server is starting...");

        try {
            System.out.println("Server is running on PORT " + PORT);
            ServerService service = new ServerImpl();
            registry.rebind(ServerService.SERVICE_NAME, service);

            waitForInput("To send message input anything.");

            service.executeClients();

            waitForInput("To close the server input anything.");

            stopServer();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private static void waitForInput(String msg) {
        System.out.println(msg);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void stopServer() {
        try {
            // unbind the service object
            registry.unbind(ServerService.SERVICE_NAME);

            // remove the service object from the registry
            UnicastRemoteObject.unexportObject(service, true);
            System.out.println("Shutting down the RMI registry...");
            // shut down the registry
            UnicastRemoteObject.unexportObject(registry, true);

            System.out.println("ProductInfoServer has stopped.");
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Stopping the server caused an error.");
        }
    }
}
