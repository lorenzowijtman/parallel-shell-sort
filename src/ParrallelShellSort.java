import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParrallelShellSort {
    // Volatile list to work with concurrency.
    private volatile int[] sorted = {};
    private int amountOfThreads = 0;

    public ParrallelShellSort() {
        amountOfThreads = Runtime.getRuntime().availableProcessors();
    }

    public ParrallelShellSort(int AOT) {
        amountOfThreads = AOT;
    }

   public int[] sort(int[] list) {
       this.sorted = list;

       //create list of threads
       ArrayList<ShellComparator> threads = new ArrayList<>();
       for(int i = 0; i < amountOfThreads; i++) {
           String name = "t" + i;
           threads.add(new ShellComparator(name, i));
       }

       ExecutorService pool;
       pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()); // walter's pc = 4, lorenzo's = 8

       for (int i = threads.size(); i >= 1; i--) {

           List<Callable<Object>> tasks = new ArrayList<>();

           for (int k = 0; k < i; k++) {
               threads.get(k).gap = i;
               tasks.add(Executors.callable(threads.get(k)));
           }

           try {
//               System.out.println("begin");
               pool.invokeAll(tasks);
//               System.out.println("end");
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }

       pool.shutdown();
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
        boolean isSorted;
        do {
            isSorted = true;
            for (int i = startIndex ; i < sorted.length - gap; i += gap) {
                int j = sorted[i];
                int k = sorted[i + gap];

                //System.out.println(name + " J: " + j + '\n' + name + " K: "  + k);
                if (j > k) {
                    sorted[i + gap ] = j;
                    sorted[i] = k;
                    isSorted = false;
                }
            }
        } while(!isSorted);

    }
}
}
