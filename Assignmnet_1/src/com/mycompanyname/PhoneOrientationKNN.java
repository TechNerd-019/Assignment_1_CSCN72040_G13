package com.mycompanyname;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class PhoneOrientationKNN extends PhoneOrientationNN implements PhoneOrientationClassifier {
    private int k;

    public PhoneOrientationKNN(String fileName, int k) {
        super(fileName);
        this.k = k;
    }

    @Override
    public int predict(double[] testData) {
        PriorityQueue<double[]> queue = new PriorityQueue<>(k, new Comparator<double[]>() {
            @Override
            public int compare(double[] a, double[] b) {
                double distanceA = 0.0;
                double distanceB = 0.0;
                for (int i = 0; i < a.length - 1; i++) { // Exclude label
                    distanceA += Math.pow(a[i] - testData[i], 2);
                    distanceB += Math.pow(b[i] - testData[i], 2);
                }
                return Double.compare(distanceB, distanceA); // Reverse order
            }
        });

        for (double[] trainSample : getTrainingData()) {
            queue.offer(trainSample);
            if (queue.size() > k) {
                queue.poll();
            }
        }

        HashMap<Integer, Integer> labelCount = new HashMap<>();
        int maxCount = 0;
        int orientationLabel = -1;

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
