/**
 * @file Main.java
 * @brief Main class for the Phone Orientation App, providing user interaction and classifier execution.
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @class Main
 * @brief Main class for the Phone Orientation App, handling user interaction and classifier execution.
 */
public class Main {

    /**
     * @brief Main method to run the Phone Orientation App.
     * Users choose a classifier, input test file names, and view predictions.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Display welcome message and classifier options
        System.out.println("Welcome to the Phone Orientation App!");
        System.out.println("Please choose one of the following classifiers:");
        System.out.println("1. Nearest Neighbor (NN)");
        System.out.println("2. K-Nearest Neighbor (KNN)");
        System.out.println("3. Another Classifier");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Assuming this is your training data file
        String trainingFileName = "trainingData1.txt";

        // Initialize variables for test file and result file
        String testFileName = "";
        String resultFileName = "result.txt";

        if (choice == 1) {
            // Instantiate NN class and execute prediction
            NN nn = new NN(trainingFileName);

            // Get user input for the test file
            System.out.println("Please enter the test file name:");
            testFileName = scanner.nextLine();
            predictAndWrite(nn, testFileName, resultFileName);
            System.out.println("Prediction done using NN classifier. Check the result file.");
        } else if (choice == 2) {
            // Get user input for the value of k
            System.out.println("Please enter the value of k:");
            int k = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Instantiate KNN class with the specified k and execute prediction
            KNN knn = new KNN(trainingFileName, k);

            // Get user input for the test file
            System.out.println("Please enter the test file name:");
            testFileName = scanner.nextLine();
            predictAndWrite(knn, testFileName, resultFileName);
            System.out.println("Prediction done using KNN classifier. Check the result file.");
        } else if (choice == 3) {
            // Instantiate AnotherClassifier class and execute training and prediction
            AnotherClassifier ac = new AnotherClassifier();
            ac.train(trainingFileName); // Just printing function name, no actual training
            ac.predict(null); // Just printing function name, no actual prediction
            System.out.println("Printing done using Another Classifier.");
        } else {
            System.out.println("Invalid choice. Please try again.");
        }

        scanner.close();
    }

    /**
     * @brief Executes the classifier and writes predictions to the result file.
     * @param classifier The chosen classifier.
     * @param testFileName The name of the test file.
     * @param resultFileName The name of the result file.
     */
    private static void predictAndWrite(MainClassifier classifier, String testFileName, String resultFileName) {
        try (Scanner scanner = new Scanner(new FileInputStream(testFileName)); // Use FileInputStream
             PrintWriter writer = new PrintWriter(resultFileName)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                double[] testData = new double[values.length];
                for (int i = 0; i < values.length; i++) {
                    testData[i] = Double.parseDouble(values[i]);
                }
                int orientationLabel = classifier.predict(testData);
                writer.println(line + " " + orientationLabel + " " + getOrientationName(orientationLabel));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Gets the orientation name based on the orientation label.
     * @param orientationLabel The orientation label.
     * @return The corresponding orientation name.
     */
    public static String getOrientationName(int orientationLabel) {
        switch (orientationLabel) {
            case 1:
                return "Face up";
            case 2:
                return "Face down";
            case 3:
                return "Portrait";
            case 4:
                return "Portrait upside down";
            case 5:
                return "Landscape Left";
            case 6:
                return "Landscape Right";
            default:
                return "Unknown";
        }
    }
}
