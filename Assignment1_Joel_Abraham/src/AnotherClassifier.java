/*
 * Subclass of MainClassifier.
 * 
 * This class contains all three function declarations which
 * are printed when choosing "3" from the main class.
 * 
 * 
 */

public class AnotherClassifier implements MainClassifier  {
    public AnotherClassifier() {
        System.out.println("AnotherClassifier constructor");
    }

    public void train(String fileName) {
        System.out.println("AnotherClassifier train function");
    }

    public int predict(double[] testData) {
        System.out.println("AnotherClassifier predict function");
        return -1;
    }
}

