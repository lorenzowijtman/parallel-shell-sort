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
                System.out.println("running with " + AOT + " threads");
            }


        //SequentialShellSort sorter = new SequentialShellSort();

        ParrallelShellSort psorter = new ParrallelShellSort(AOT);
        int[][] inputs = {
                new InputGenerator(100000, 114).getDataset(),
                new InputGenerator(200000, 114).getDataset(),
                new InputGenerator(300000, 114).getDataset(),
                new InputGenerator(400000, 114).getDataset(),
                new InputGenerator(500000, 114).getDataset()
        };

        for (int i = 0; i < inputs.length; i++) {
            //start timer
            long start = System.currentTimeMillis();

            //start sorting
            int[] output = psorter.sort(inputs[i]);

            //end timer
            long end = System.currentTimeMillis();

            //get time difference
            long time = end - start;

            System.out.println("Array of size: " + inputs[i].length + "\n" + "Time: " + time);
        }

        //printArray(output);

//        int result[] = {165, 165, 167, 168, 167, 173, 168, 169, 175, 179};
//
//        calcAverage(result);

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
