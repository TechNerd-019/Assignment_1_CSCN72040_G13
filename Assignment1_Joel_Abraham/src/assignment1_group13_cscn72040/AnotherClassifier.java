package assignment1_group13_cscn72040;

public class AnotherClassifier implements Classifier {
    
    @Override
    public String classify(String data) {
        printFunctionName("classify");
        // That will return the dummy classifier
        return "Dummy Classification";
    }
    
    @Override
    public String getName() {
        printFunctionName("getName");
        //That will return the name of the classifier 
        return "AnotherClassifier";
    }
    
    private void printFunctionName(String functionName) {
        // Print the name of the function that was called
        System.out.println("Function called: " + functionName);
    }
}
