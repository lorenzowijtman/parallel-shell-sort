package rmi.client.input;

import java.util.Random;

/* Class that will generate input for the program */
public class InputGenerator {
    private Random r = new Random();
    private long seed = 114;
    private int[] dataset;

    //General constructor, no input size specified
    public InputGenerator() {
        r.setSeed(this.seed);
        int size = 100000;
        dataset = new int[size];
        generateDataset();
    }

    //Specify size of generated input and the seed to be used
    public InputGenerator(int size, long seed) {
        this.seed = seed;
        r.setSeed(seed);
        dataset = new int[size];
        generateDataset();
    }

    public int[] getDataset() {
        return this.dataset;
    }

    private void generateDataset() {
        // Maximum int to be generated
        int max = 1000000;
        for (int i = 0; i < dataset.length; i++) {
            dataset[i] = r.nextInt(max);
        }
    }
}
