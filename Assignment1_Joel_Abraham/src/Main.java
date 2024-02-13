import java.io.FileInputStream;

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;


public class Main {
	public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("Welcome to the Phone Orientation App!");
	    System.out.println("Please choose one of the following classifiers:");
	    System.out.println("1. Nearest Neighbor (NN)");
	    System.out.println("2. K-Nearest Neighbor (KNN)");
	    System.out.println("3. Another Classifier");
	    int choice = scanner.nextInt();
	    scanner.nextLine(); // Consume newline

	    String trainingFileName = "trainingData1.txt"; // Assuming this is your training data file

	    // Initialize variables for test file and result file
	    String testFileName = "";
	    String resultFileName = "result.txt";

	    if (choice == 1) {
	        NN nn = new NN(trainingFileName);
	        // Ask the user to input the test file
	        System.out.println("Please enter the test file name:");
	        testFileName = scanner.nextLine();
	        predictAndWrite(nn, testFileName, resultFileName);
	        System.out.println("Prediction done using NN classifier. Check the result file.");
	    } else if (choice == 2) {
	        System.out.println("Please enter the value of k:");
	        int k = scanner.nextInt();
	        scanner.nextLine(); // Consume newline
	        KNN knn = new KNN(trainingFileName, k);
	        // Ask the user to input the test file
	        System.out.println("Please enter the test file name:");
	        testFileName = scanner.nextLine();
	        predictAndWrite(knn, testFileName, resultFileName);
	        System.out.println("Prediction done using KNN classifier. Check the result file.");
	    } else if (choice == 3) {
	        AnotherClassifier ac = new AnotherClassifier();
	        ac.train(trainingFileName);
	        // Ask the user to input the test file
	        System.out.println("Please enter the test file name:");
	        testFileName = scanner.nextLine();
	        predictAndWrite(ac, testFileName, resultFileName);
	        System.out.println("Prediction done using Another Classifier. Check the result file.");
	    } else {
	        System.out.println("Invalid choice. Please try again.");
	    }

	    scanner.close();
	}

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
