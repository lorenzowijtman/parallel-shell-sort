import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParrallelShellSort {
    static int[] gaps = {701, 301, 132, 57, 23, 10, 4, 1};

    volatile int[] sorted = {};

    final int MAX_T = 4;


    public int[] sort(int[] list) {
        this.sorted = list;

        // decide the interval
        int interval = 3;

        Runnable t1 = new ShellComparator("t1",0, interval);
        Runnable t2 = new ShellComparator("t2", 1, interval);
        Runnable t3 = new ShellComparator("t3",2, interval);

        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);

        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);

        pool.shutdown();

        return this.sorted;
    }


    class ShellComparator implements Runnable {

        private String name;
        private int startIndex;
        private int gap;

        ShellComparator(String name, int startIndex, int gap) {
            this.name = name;
            this.startIndex = startIndex;
            this.gap = gap;
        }

        @Override
        public void run() {
            int prevNumber = sorted[startIndex];
            for (int i = startIndex ; i < sorted.length; i += gap) {
                sorted[startIndex]
            }
        }
    }

}
