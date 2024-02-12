package com.mycompanyname;

public class AnotherClassifier implements PhoneOrientationClassifier  {
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

