import java.rmi.*;

public interface SortInterface extends Remote {
    /**
     * The name used in the RMI registry.
     */
    static final String SERVICE_NAME = "ProductInfoService";

    public int[] sort(int[] numbers) throws RemoteException;
}
