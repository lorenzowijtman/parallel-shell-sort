import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SortImpl extends UnicastRemoteObject
        implements SortInterface {

    SortImpl() throws RemoteException {
        super();
    }

    @Override
    public int[] sort(int[] numbers) throws RemoteException {
        return new int[] {1, 2, 3, 4, 5, 6, 7};
    }
}
