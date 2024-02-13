/**
 * @file NN.java
 * @brief Class implementing the Nearest Neighbor (NN) algorithm, implementing MainClassifier.
 */

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @class NN
 * @brief Class implementing the Nearest Neighbor (NN) algorithm, implementing MainClassifier.
 */
public class NN implements MainClassifier {
    private List<double[]> trainingData;

    /**
     * @brief Constructor for NN class.
     * Initializes the class and loads training data from a specified file.
     * @param fileName The name of the file used for training.
     */
    public NN(String fileName) {
        this.trainingData = new ArrayList<>();
        loadTrainingData(fileName);
    }

    /**
     * @brief Loads training data from the specified file into the trainingData list.
     * @param fileName The name of the file containing training data.
     */
    private void loadTrainingData(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                double[] data = new double[values.length];
                for (int i = 0; i < values.length; i++) {
                    data[i] = Double.parseDouble(values[i]);
                }
                trainingData.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Predicts the orientation label for the given test data using the NN algorithm.
     * @param testData An array of doubles representing the test data.
     * @return The predicted orientation label.
     */
    public int predict(double[] testData) {
        double minDistance = Double.MAX_VALUE;
        int orientationLabel = -1;

        for (double[] trainSample : trainingData) {
            double distance = 0.0;
            for (int i = 0; i < trainSample.length - 1; i++) {
                distance += Math.pow(trainSample[i] - testData[i], 2);
            }

            if (distance < minDistance) {
                minDistance = distance;
                orientationLabel = (int) trainSample[trainSample.length - 1];
            }
        }

        return orientationLabel;
    }

    /**
     * @brief Getter method for retrieving the training data.
     * @return The list of double arrays representing the training data.
     */
    public List<double[]> getTrainingData() {
        return trainingData;
    }
}
