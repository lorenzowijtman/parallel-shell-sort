import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        SequentialShellSort sorter = new SequentialShellSort();
        ParrallelShellSort psorter = new ParrallelShellSort();
        int[] input = new InputGenerator(10).getDataset();
        printArray(input);
        printArray(psorter.sort(input));
    }

    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}
