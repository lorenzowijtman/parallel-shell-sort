import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        SequentialShellSort sorter = new SequentialShellSort();
        int[] input = new InputGenerator(100).getDataset();
        printArray(input);
        printArray(sorter.sort(input));
    }


    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}
