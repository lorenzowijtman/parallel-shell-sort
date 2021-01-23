package rmi.server;

import rmi.client.input.InputGenerator;

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
    private static ServerService serverService;
    private static InputGenerator inputGenerator;

    static {
        try {
            registry = LocateRegistry.createRegistry(PORT);
            serverService = new ServerImpl();
            inputGenerator = new InputGenerator(100, 114);
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

            System.out.println("To close the server type 'quit'");
            System.out.println("To sort array type 'sort'");
            System.out.println("To test client connection type 'test'");


            String input = waitForInput("> ");

            loop:
            while (true) {
                switch (input) {
                    case "quit":
                        break loop;
                    case "test":
                        service.testClients();
                        break;
                    case "sort":
                        service.sort(new int[]{10, 9, 8, 8, 7, 6, 5, 4, 3, 2, 1});
                        break;
                    case "sort random":
                        int[] dataset = inputGenerator.getDataset();
                        service.sort(dataset);
                        break;
                    default:
                        System.out.println("Could not recognize command.");
                        break;
                }
                input = waitForInput(">");
            }

            stopServer();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private static String waitForInput(String msg) {
        System.out.println(msg);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void stopServer() {
        try {
            // unbind the service object
            registry.unbind(ServerService.SERVICE_NAME);

            // remove the service object from the registry
            UnicastRemoteObject.unexportObject(serverService, true);
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
