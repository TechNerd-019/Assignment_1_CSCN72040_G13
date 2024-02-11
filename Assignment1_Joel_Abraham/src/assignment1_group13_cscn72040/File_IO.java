package assignment1_group13_cscn72040;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class File_IO {
    public static double[][] readData(String filename) throws IOException {
       // Open the file.
    	File openFile = new File(filename);
    	Scanner readLine = new Scanner(openFile);
    	
    	ArrayList<Double> xCoordinates = new ArrayList<Double>();  
    	ArrayList<Double> yCoordinates = new ArrayList<Double>();
    	ArrayList<Double> zCoordinates = new ArrayList<Double>();
    
    	
    	while (readLine.hasNextLine()) {
            String line = readLine.nextLine();
            String[] parts = line.split(",");
            if (parts.length == 4) { // Assuming each line has 4 parts: x, y, z, label
                // Parse and store coordinates
                xCoordinates.add(Double.parseDouble(parts[0]));
                yCoordinates.add(Double.parseDouble(parts[1]));
                zCoordinates.add(Double.parseDouble(parts[2]));
            } else {
                // Handle invalid line
                System.err.println("Invalid line: " + line);
            }
        }
        
        readLine.close();
        
        double[][] result = new double[xCoordinates.size()][4];
        for (int i = 0; i < xCoordinates.size(); i++) {
            result[i][0] = xCoordinates.get(i);
            result[i][1] = yCoordinates.get(i);
            result[i][2] = zCoordinates.get(i);
        }

        return result;
    	

    }
    
    public static int[] readLabels(String filename) throws IOException {
    	
    	// Open the file.
    	File openFile = new File(filename);
    	Scanner readLine = new Scanner(openFile);
    	
    	int capacity = 100; // Example capacity, you can adjust it based on your needs.
    	int[] labels = new int[capacity];
    	int index = 0;
    	
    	while (readLine.hasNextLine()) {
    	    String line = readLine.nextLine();
    	    String[] parts = line.split(",");
    	    if (parts.length == 4) { // Assuming each line has 4 parts: x, y, z, label
    	        // Parse and store label
    	        labels[index++] = Integer.parseInt(parts[3]);
    	        
    	        // Resize the array if needed
    	        if (index == labels.length) {
    	            // Resize by doubling the capacity
    	            int newCapacity = labels.length * 2;
    	            int[] newLabels = new int[newCapacity];
    	            System.arraycopy(labels, 0, newLabels, 0, index);
    	            labels = newLabels;
    	        }
    	    } else {
    	        // Handle invalid line
    	        System.err.println("Invalid line: " + line);
    	    }
    	}
    	
    	readLine.close();

    	// Trim the array to remove unused slots if necessary
    	if (index < labels.length) {
    	    int[] trimmedLabels = new int[index];
    	    System.arraycopy(labels, 0, trimmedLabels, 0, index);
    	    labels = trimmedLabels;
    	}
    	
    	return labels;
    	
        
    }
}
