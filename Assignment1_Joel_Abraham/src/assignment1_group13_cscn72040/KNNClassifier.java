package assignment1_group13_cscn72040;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class KNNClassifier {

    private double[][] rawData;
    private int[] labels;

    public KNNClassifier(double[][] rawData, int[] labels) {
        this.rawData = rawData;
        this.labels = labels;
    }

    public String classify(double[] testPoint, int k) {
        // Convert testPoint to a DataPoint object
        DataPoint testDataPoint = new DataPoint(testPoint, "");
        
        // Calculate distances to all training points
        List<DistanceLabelPair> distances = new ArrayList<>();
        for (int i = 0; i < rawData.length; i++) {
            double distance = calculateDistance(testDataPoint, new DataPoint(rawData[i], Integer.toString(labels[i])));
            distances.add(new DistanceLabelPair(distance, Integer.toString(labels[i])));
        }

        // Sort distances in ascending order
        Collections.sort(distances);

        // Count occurrences of each class in the k-nearest neighbors
        Map<String, Integer> classCounts = new HashMap<>();
        for (int i = 0; i < k; i++) {
            String label = distances.get(i).getLabel();
            classCounts.put(label, classCounts.getOrDefault(label, 0) + 1);
        }

        // Find the class with the majority vote
        String predictedClass = null;
        int maxCount = 0;
        for (Entry<String, Integer> entry : classCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                predictedClass = entry.getKey();
            }
        }

        return predictedClass;
    }
    
    private double calculateDistance(DataPoint point1, DataPoint point2) {
        // Implement your distance metric (e.g., Euclidean distance)
        double[] features1 = point1.getFeatures();
        double[] features2 = point2.getFeatures();
        double sum = 0.0;
        for (int i = 0; i < features1.length; i++) {
            sum += Math.pow(features1[i] - features2[i], 2);
        }
        return Math.sqrt(sum);
    }
}


class DataPoint {
    private double[] features;
    private String label;

    public DataPoint(double[] features, String label) {
        this.features = features;
        this.label = label;
    }

    public double[] getFeatures() {
        return features;
    }

    public String getLabel() {
        return label;
    }
}

class DistanceLabelPair implements Comparable<DistanceLabelPair> {
    private double distance;
    private String label;

    public DistanceLabelPair(double distance, String label) {
        this.distance = distance;
        this.label = label;
    }

    public double getDistance() {
        return distance;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public int compareTo(DistanceLabelPair other) {
        return Double.compare(this.distance, other.distance);
    }
}