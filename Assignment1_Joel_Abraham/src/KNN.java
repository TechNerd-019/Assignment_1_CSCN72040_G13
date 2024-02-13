/**
 * @file KNN.java
 * @brief Class implementing the K-Nearest Neighbor (KNN) algorithm, extending NN and implementing MainClassifier.
 */

import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @class KNN
 * @brief Class implementing the K-Nearest Neighbor (KNN) algorithm, extending NN and implementing MainClassifier.
 */
public class KNN extends NN implements MainClassifier {
    private int k;

    /**
     * @brief Constructor for KNN class.
     * Initializes the class with a specified file name and the value of k.
     * @param fileName The name of the file used for training (passed to the superclass constructor).
     * @param k The number of nearest neighbors to consider.
     */
    public KNN(String fileName, int k) {
        super(fileName);
        this.k = k;
    }

    /**
     * @brief Predicts the orientation label for the given test data using the KNN algorithm.
     * @param testData An array of doubles representing the test data.
     * @return The predicted orientation label.
     */
    @Override
    public int predict(double[] testData) {
        // Priority queue to store the k-nearest neighbors based on distance
        PriorityQueue<double[]> queue = new PriorityQueue<>(k, new Comparator<double[]>() {
            @Override
            public int compare(double[] a, double[] b) {
                double distanceA = 0.0;
                double distanceB = 0.0;
                for (int i = 0; i < a.length - 1; i++) {
                    distanceA += Math.pow(a[i] - testData[i], 2);
                    distanceB += Math.pow(b[i] - testData[i], 2);
                }
                return Double.compare(distanceB, distanceA);
            }
        });

        // Populate the queue with training samples
        for (double[] trainSample : getTrainingData()) {
            queue.offer(trainSample);
            if (queue.size() > k) {
                queue.poll();
            }
        }

        // HashMap to count the occurrences of each label in the k-nearest neighbors
        HashMap<Integer, Integer> labelCount = new HashMap<>();
        int maxCount = 0;
        int orientationLabel = -1;

        // Iterate through the k-nearest neighbors and count occurrences of each label
        for (double[] sample : queue) {
            int label = (int) sample[sample.length - 1];
            labelCount.put(label, labelCount.getOrDefault(label, 0) + 1);
            if (labelCount.get(label) > maxCount) {
                maxCount = labelCount.get(label);
                orientationLabel = label;
            }
        }

        return orientationLabel;
    }
}
