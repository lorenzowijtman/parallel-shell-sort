package rmi.client;

import rmi.server.ServerService;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class SortInfoClient {

    private static final int PORT = 1099;
    private static Registry registry;
    private static ClientService clientService;
    private static ServerService serverService;

    static {
        try {
            registry = LocateRegistry.getRegistry(PORT);
            clientService = new RMIShellSort();
        } catch (RemoteException e) {
            e.printStackTrace();
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

    protected SortInfoClient() throws RemoteException {
        super();
    }

    public void startClient() {
        try {
            serverService = (ServerService) Naming.lookup(ServerService.SERVICE_NAME);

            serverService.registerClient(clientService);
            System.out.println("Waiting... Type anything to close client.");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();

            serverService.unregisterClient(clientService);
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("You probably forgot to start the server...");
        }
    }

    private static class RMIShellSort extends UnicastRemoteObject implements ClientService {
        private static final long serialVersionUID = 1190476516911661470L;

        private int gap;
        private int startIndex;

        protected RMIShellSort() throws RemoteException {
        }

        public void sort() throws RemoteException {
            System.out.println("Running on client, gap is " + gap + " and startIndex is " + startIndex);

            boolean isSorted;

            do {
                isSorted = true;
                System.out.println("x1");

                for (int i = startIndex; i < serverService.getSortedArrayLength() - gap; i += gap) {
                    int j = serverService.getSortedArrayIndex(i);
                    int k = serverService.getSortedArrayIndex(i + gap);
//                    System.out.println("x2: j="+j+"k="+k);
                    //System.out.println(name + " J: " + j + '\n' + name + " K: "  + k);
                    if (j > k) {
                        serverService.setSortedArr(i + gap, j);
                        serverService.setSortedArr(i, k);
                        isSorted = false;
                        System.out.println("x3");
                    }
                }
            } while (!isSorted);

            System.out.println("Finished running on client.");
        }

        @Override
        public void setGap(int gap) throws RemoteException {
            this.gap = gap;
        }

        @Override
        public void setStartIndex(int startIndex) throws RemoteException {
            this.startIndex = startIndex;
        }

        @Override
        public void testMessage() {
            System.out.println("This is the client test message. " +
                    "This function is called from the server and executed on the client.");
        }

    }
}