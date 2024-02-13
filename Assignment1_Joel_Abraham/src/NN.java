
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NN implements MainClassifier  {
    private List<double[]> trainingData;

    public NN(String fileName) {
        this.trainingData = new ArrayList<>();
        loadTrainingData(fileName);
    }

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

  
    public List<double[]> getTrainingData() {
        return trainingData;
    }

}
