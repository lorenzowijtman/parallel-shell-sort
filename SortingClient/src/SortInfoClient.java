import java.rmi.Naming;

public class SortInfoClient {
    public static void main(String[] args) {
        try {
            System.out.println("SortInfoClient> get product info with id '123'...");
            // looks up the registry by service name and returns a stub
            SortService sortInfo = (SortService) Naming.lookup(SortService.SERVICE_NAME);
            // invoke the remote method via the stub
            int[] integers = sortInfo.sort(new int[]{2, 3, 4});
            System.out.println("SortInfoClient> production info received: ");
            for (int x : integers) System.out.println(x + ", ");
        } catch (Exception e) {
            System.err.println("SortInfoClient> RemoteDate exception: " + e.getMessage());
            e.printStackTrace();

            System.out.println("You probably forgot to start the server...");
        }
    }
}