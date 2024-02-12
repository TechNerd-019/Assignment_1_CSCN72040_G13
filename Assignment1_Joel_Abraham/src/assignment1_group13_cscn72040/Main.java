package assignment1_group13_cscn72040;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Which classifier would you like to use? (1=NN, 2=KNN, 3=AnotherClassifier)");
            int choice;

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input for the classifier choice. Please enter a numeric value (1, 2, or 3).");
                return;
            }

            System.out.println("Choose a data file (enter the corresponding number):");
            System.out.println("1. testingData1.txt");
            System.out.println("2. trainingData1.txt");
            System.out.println("3. unknownData1.txt");

            int fileChoice;

            try {
                fileChoice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input for the file choice. Please enter a numeric value (1, 2, or 3).");
                return;
            }

            String filename;
            switch (fileChoice) {
                case 1:
                    filename = "testingData1.txt";
                    break;
                case 2:
                    filename = "trainingData1.txt";
                    break;
                case 3:
                    filename = "unknownData1.txt";
                    break;
                default:
                    System.out.println("Invalid file choice. Please enter 1, 2, or 3.");
                    return;
            }

            double[][] data = File_IO.readData(filename);
            int[] labels = File_IO.readLabels(filename);

            NearestNeighbor nn = null;
            KNNClassifier knn = null;

            if (choice == 1) {
                nn = new NearestNeighbor(data, labels);
            } else if (choice == 2) {
                System.out.println("Enter the value of k for KNN:");
                int k = scanner.nextInt();
                knn = new KNNClassifier(data, labels);
            } else if (choice == 3) {
                // Instantiate AnotherClassifier here if needed
            } else {
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                return;
            }

            double[][] unknownData = File_IO.readData("unknownData1.txt");

            PrintWriter writer = new PrintWriter("result.txt");

            for (double[] dataPoint : unknownData) {
                int label;
                if (choice == 1) {
                    // Use NearestNeighbor to make predictions
                    label = nn.classify(dataPoint);
                } else if (choice == 2) {
                    // Use KNNClassifier to make predictions
                    label = knn.classify(new DataPoint(dataPoint, ""));
                } else {
                    // Implement logic for AnotherClassifier if needed
                    label = 0; // Placeholder value, replace with actual logic
                }

                writer.println(dataPoint[0] + "," + dataPoint[1] + "," + dataPoint[2] + "," + label);
            }

            writer.close();

            System.out.println("Predictions for the unknown data have been written to result.txt.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
