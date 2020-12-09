import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        SequentialShellSort sorter = new SequentialShellSort();
        int[] input = new InputGenerator(100000, 114).getDataset();
        long time = 0;

        //run x amount of times
        int x = 10;

        for(int i = 0; i < x; i++) {
            //start timer
            long start = System.currentTimeMillis();

            //start sorting
            sorter.sort(input);

            //end timer
            long end = System.currentTimeMillis();


            //get time difference
            time = end - start;

            System.out.println(time);
        }

        //devide by amount of times it ran for average time
        time = time / x;

        //print time result
        System.out.println("Dataset was sorted " + x + " times");
        System.out.println("Average Time used to sort array in millis: " + time);
    }

    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}
