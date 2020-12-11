import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().availableProcessors());
        SequentialShellSort sorter = new SequentialShellSort();
        ParrallelShellSort psorter = new ParrallelShellSort();
        int[] input = new InputGenerator(20, 114).getDataset();
        printArray(input);
        printArray(psorter.sort(input));
        int[] input = new InputGenerator(500000, 114).getDataset();

        //start timer
        long start = System.currentTimeMillis();

        //start sorting
        sorter.sort(input);

        //end timer
        long end = System.currentTimeMillis();

        //get time difference
        long time = end - start;

        System.out.println(time);

//        int result[] = {165, 165, 167, 168, 167, 173, 168, 169, 175, 179};
//
//        calcAverage(result);

    }

    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void calcAverage(int[] results) {
        long time = 0;

        for(int i = 0; i < results.length; i++) {
            time += results[i];
        }

        time = time / results.length;

        //print time result
        System.out.println("Dataset was sorted " + results.length + " times");
        System.out.println("Average Time used to sort array in millis: " + time);
    }

}
