import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        //available processors
        System.out.println(Runtime.getRuntime().availableProcessors());

        //SequentialShellSort sorter = new SequentialShellSort();

        ParrallelShellSort psorter = new ParrallelShellSort(512);
        int[][] inputs = {
        new InputGenerator(100000, 114).getDataset(),
        new InputGenerator(200000, 114).getDataset(),
        new InputGenerator(300000, 114).getDataset(),
        new InputGenerator(400000, 114).getDataset(),
        new InputGenerator(500000, 114).getDataset()
        };

        for(int i = 0; i < inputs.length; i ++) {
            //start timer
            long start = System.currentTimeMillis();

            //start sorting
            int[] output = psorter.sort(inputs[i]);

            //end timer
            long end = System.currentTimeMillis();

            //get time difference
            long time = end - start;

            System.out.println("Array of size: "+ inputs[i].length + "\n" +"Time: "+ time);
        }

        //printArray(output);

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
