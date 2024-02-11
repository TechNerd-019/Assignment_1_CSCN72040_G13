package assignment1_group13_cscn72040;

public class NearestNeighbor {
	
    private double[][] trainingData;   // Implement function for it.
    private int[] labels;
    
    // This parameterized constructor sets training data and labels for
    // use with the function.
    public NearestNeighbor(double[][] trainingData, int[] labels) {
        this.trainingData = trainingData;
        this.labels = labels;
    }
	
	// Method to calculate Euclidean distance between two points
    private static double calculateDistance(double[] point1, double[] point2) {
        double sum = 0;
        for (int i = 0; i < point1.length; i++) {
            sum += Math.pow(point1[i] - point2[i], 2);
        }
        return Math.sqrt(sum);
    }

    // Method to find the nearest neighbor's label
    public static int findNearestNeighbor(double[][] coordinates, int[] labels, double[] target) {
        double minDistance = Double.MAX_VALUE;
        int nearestLabel = -1;

        for (int i = 0; i < coordinates.length; i++) {
            double distance = calculateDistance(coordinates[i], target);
            if (distance < minDistance) {
                minDistance = distance;
                nearestLabel = labels[i];
            }
        }

        return nearestLabel;
    }

}
