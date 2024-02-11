package assignment1_group13_cscn72040;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // Create a Scanner for user input
            Scanner scanner = new Scanner(System.in);

            // Ask the user to choose a classifier
            System.out.println("Which classifier would you like to use? (1=NN, 2=KNN, 3=AnotherClassifier)");
            int choice;

            try {
                // Handle non-numeric input for the classifier choice
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input for the classifier choice. Please enter a numeric value (1, 2, or 3).");
                return;
            }

            // Provide a list of data file options
            System.out.println("Choose a data file (enter the corresponding number):");
            System.out.println("1. testingData1.txt");
            System.out.println("2. trainingData1.txt");
            System.out.println("3. unknownData1.txt");

            // Get the user's choice for the data file
            int fileChoice;

            try {
                // Handle non-numeric input for the file choice
                fileChoice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input for the file choice. Please enter a numeric value (1, 2, or 3).");
                return;
            }

            // Map the user's file choice to the actual file name
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

            // Read the data and labels from the file using File_IO class
            double[][] data = File_IO.readData(filename);
            int[] labels = File_IO.readLabels(filename);

            // Create the chosen classifier
            NearestNeighbor nn = new NearestNeighbor();     // replace with the actual names
            KNearestNeighbors knn = null; // replace with the actual names
            AnotherClassifier ac = null; // replace with the actual names

            if (choice == 1) {
                nn = new NearestNeighbor(data, labels); // replace with the actual names
            } else if (choice == 2) {
                knn = new KNearestNeighbors(data, labels, 3); // let's say k=3 // replace with the actual names
            } else if (choice == 3) {
                ac = new AnotherClassifier();
            } else {
                System.out.println("Invalid choice. Please enter 1, 2, or 3."); // replace with the actual names
                return;
            }

            // Read the unknown data
            double[][] unknownData = File_IO.readData("unknownData1.txt"); // replace with the actual class names

            // Create a PrintWriter to write to a file
            PrintWriter writer = new PrintWriter("result.txt");

            // Use the chosen classifier to make predictions for the unknown data
            for (double[] dataPoint : unknownData) {
                int label;
                if (choice == 1) {
                    label = nn.classify(dataPoint);
                } else if (choice == 2) {
                    label = knn.classify(dataPoint);
                } else { // choice == 3
                    label = ac.predict(dataPoint);
                }

                // Write the data point and its predicted label to the file
                writer.println(dataPoint[0] + "," + dataPoint[1] + "," + dataPoint[2] + "," + label);
            }

            // Close the PrintWriter
            writer.close();

            System.out.println("Predictions for the unknown data have been written to result.txt.");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}