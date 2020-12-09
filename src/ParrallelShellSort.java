import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParrallelShellSort {
    // Volatile list to work with concurrency.
    private volatile int[] sorted = {};

   public int[] sort(int[] list) {
       this.sorted = list;

       // Put 4 threads in a list. These threads handle the comparisons of the indices.
       //
       ArrayList<ShellComparator> threads = new ArrayList<>(Arrays.asList(
               new ShellComparator("t1", 0),
               new ShellComparator("t2", 1),
               new ShellComparator("t3", 2),
               new ShellComparator("t4", 3)));

       ExecutorService pool;

       for (int i = threads.size(); i >= 1; i--) {
           pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()); // walter's pc = 4, lorenzo's = 8

           List<Callable<Object>> tasks = new ArrayList<>();

           for (int k = 0; k < i; k++) {
               threads.get(k).gap = i;
               tasks.add(Executors.callable(threads.get(k)));
           }

           try {
               System.out.println("begin");
               pool.invokeAll(tasks);
               System.out.println("end");
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }

       return this.sorted;
   }

    class ShellComparator implements Runnable {

    private String name;
    private int startIndex;

    public int gap;

    ShellComparator(String name, int startIndex) {
        this.name = name;
        this.startIndex = startIndex;
    }

    @Override
    public void run() {
        for (int i = startIndex ; i < sorted.length - gap; i += gap) {
            int j = sorted[i];
            int k = sorted[i + gap];

            if (this.name.equals("t1")) System.out.println("J: " + j + '\n' + "K: "  + k);

            if (j > k) {
                sorted[i + gap ] = j;
                sorted[i] = k;
           }
        }

    }
}
}
