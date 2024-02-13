/**
 * @file MainClassifier.java
 * @brief Interface defining the predict function for classifier classes.
 */

/**
 * @interface MainClassifier
 * @brief Interface defining the predict function for classifier classes.
 */
public interface MainClassifier {
    
    /**
     * @brief Predicts the orientation label for the given test data.
     * @param testData An array of doubles representing the test data.
     * @return The predicted orientation label.
     */
    public int predict(double[] testData);
}
