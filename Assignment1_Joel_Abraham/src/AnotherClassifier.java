/**
 * @file AnotherClassifier.java
 * @brief Subclass of MainClassifier, implementing training and prediction functions.
 */

/**
 * @class AnotherClassifier
 * @brief Subclass of MainClassifier providing training and prediction functions.
 */
public class AnotherClassifier implements MainClassifier {

    /**
     * @brief Constructor for AnotherClassifier.
     * Prints a message indicating the constructor is called.
     */
    public AnotherClassifier() {
        System.out.println("AnotherClassifier constructor");
    }

    /**
     * @brief Training function for AnotherClassifier.
     * Prints a message indicating the training function is called.
     * @param fileName The name of the file used for training (not utilized in this example).
     */
    public void train(String fileName) {
        System.out.println("AnotherClassifier train function");
    }

    /**
     * @brief Prediction function for AnotherClassifier.
     * Prints a message indicating the prediction function is called and returns a placeholder value.
     * @param testData An array of doubles representing the test data.
     * @return A placeholder value (-1) as this is a print-only function for demonstration.
     */
    public int predict(double[] testData) {
        System.out.println("AnotherClassifier predict function");
        return -1;
    }
}
