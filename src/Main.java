import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        //available processors
        System.out.println(Runtime.getRuntime().availableProcessors());

        //SequentialShellSort sorter = new SequentialShellSort();

        ParrallelShellSort psorter = new ParrallelShellSort(1);
        int[] input = new InputGenerator(10, 114).getDataset();
        printArray(input);



        //start timer
        long start = System.currentTimeMillis();

        //start sorting
        int[] output = psorter.sort(input);

        //end timer
        long end = System.currentTimeMillis();

        //get time difference
        long time = end - start;

        System.out.println(time);
        printArray(output);

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
