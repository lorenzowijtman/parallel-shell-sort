import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //available processors
        int available = Runtime.getRuntime().availableProcessors();
        int AOT = available;

        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        System.out.println("Welcome to our parallel Shell Sort implementation\nYou have " + available +
                " threads available to run the program.\nPlease enter the amount of threads you want to use: ");
        int input = scanner.nextInt();

        if(AOT > available){
            // Too many threads specified
            System.out.println("You entered more threads than available so we will use all the available threads");
        } else {
            // Legitimate number
            AOT = input;
            System.out.println("running with " + AOT + " threads");
        }

        ParrallelShellSort psorter = new ParrallelShellSort(AOT);

        System.out.println("Please enter the dataset size you want to use");
        int dataSize = scanner.nextInt();
        int[] data = new InputGenerator(dataSize, 114).getDataset();
        System.out.println("Using dataset size: " + dataSize);

        System.out.println("Start sorting");

        long start = System.currentTimeMillis();

        int[] sorted = psorter.sort(data);

        long end = System.currentTimeMillis();

        //get time difference
        long time = end - start;

        printArray(sorted);

        System.out.println("Sorted array!\nSize: " + dataSize + "\n" + "Time: " + time + "milliseconds");

        //SequentialShellSort sorter = new SequentialShellSort();
    }

    public static boolean isInteger(String s) {
        return s.matches("^-?\\\\d+$");
    }

    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void calcAverage(int[] results) {
        long time = 0;

        for (int i = 0; i < results.length; i++) {
            time += results[i];
        }

        time = time / results.length;

        //print time result
        System.out.println("Dataset was sorted " + results.length + " times");
        System.out.println("Average Time used to sort array in millis: " + time);
    }

}
