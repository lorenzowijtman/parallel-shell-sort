package rmi.client;// Author: github.com/rboyd/algorithms

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SequentialShellSort extends UnicastRemoteObject implements ClientService {
    private static final long serialVersionUID = 1190476516911661470L;

    static int[] gaps = {701, 301, 132, 57, 23, 10, 4, 1};
    static int[] unsorted = {80, 62, 45, 12, 52, 61, 76, 22, 95, 7, 2, 16, 8, 85, 17, 38, 25, 48, 60, 96};

    protected SequentialShellSort() throws RemoteException {
    }

    /* Shell sort is so named after the method was published by
    computer scientist Donald Shell in 1959.
    Shell sort is a generalization of insertion sort whereby the
    comparison operation is against two elements with an introduced
    "gap" in between.
    The gap is reduced until eventually it equals 1, which is
    equivalent to standard insertion sort.
    The idea here is that elements that are wildly out of sorted order
    can make large jumps in the earlier steps, greatly reducing the
    number of swap operations.
    The time complexity varies with the gap sequence, which may be
    pre-calculated experimentally or by using a generating function.
    Popular functions yield Theta(n^(3/2)) (Knuth 1973) or O(N^(4/3))
    (Sedgewick 1986).

    "For many practical variants, determining their time complexity
    remains an open problem." [Wikipedia]
    */
    @Override
    public int[] sort(int[] list) {
        System.out.println("Running on client");

        for(int gap: gaps) {
            int k = 0;
            for (int i = gap; i < list.length; i+=1) {

                System.out.println("("+ ++k +")G: " + gap);
                int j = i;
                int current = list[i];
                for (; j >= gap && list[j-gap] > current; j-=gap) {
                    list[j] = list[j-gap];
                }
                list[j] = current;
            }
        }
        return list;
    }

    @Override
    public void testMessage() {
        System.out.println("This is the client testMessage. " +
                "This function is called from the server and executed on the client.");
    }
}
