package assignment1_group13_cscn72040;




import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class KNNClassifier {

    private List<DataPoint> trainingData;
   

    public KNNClassifier(List<DataPoint> trainingData) {
        this.trainingData = trainingData;
        
    }


    public String classify(DataPoint testPoint, int k) {
        // Calculate distances to all training points
        List<DistanceLabelPair> distances = new ArrayList<>();
        for (DataPoint trainingPoint : trainingData) {
            double distance = calculateDistance(testPoint, trainingPoint);
            distances.add(new DistanceLabelPair(distance, trainingPoint.getLabel()));
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


    public static List<DataPoint> readDataFromFile(String filePath) throws IOException {
        List<DataPoint> dataPoints = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                double[] features = new double[values.length - 1];
                for (int i = 0; i < features.length; i++) {
                    features[i] = Double.parseDouble(values[i]);
                }
                String label = values[values.length - 1];
                DataPoint dataPoint = new DataPoint(features, label);
                dataPoints.add(dataPoint);
            }
        }
        return dataPoints;
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
