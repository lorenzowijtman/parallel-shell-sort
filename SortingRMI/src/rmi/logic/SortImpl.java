package rmi.logic;

import rmi.logic.input.InputGenerator;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

public class SortImpl extends UnicastRemoteObject implements SortService {

    private InputGenerator inputGenerator = new InputGenerator();

    public SortImpl() throws RemoteException {
        super();
    }

    @Override
    public int[] sort(int[] numbers) throws RemoteException {
        return new SequentialShellSort().sort(numbers);
    }

    @Override
    public int[] getSortingNumbers() throws RemoteException {
        int[] numbers = inputGenerator.getDataset();

        return numbers;
    }

    @Override
    public void sendSorted(int[] sorted) throws RemoteException {
        System.out.println("Sorted list has been sent back: ");
        System.out.println("sorted = " + Arrays.toString(sorted));
    }

}
