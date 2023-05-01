// Program to run the graph experiment to count the number of operations when running dijkstra's algorithm for 25 datasets
// Andrew Erasmus
// 01/05/2023

import java.io.IOException;
import java.io.File;
public class GraphExperiment{

    /**
     * Main method to run the experiment for the 25 datasets
     * @param args the user's input from the output, which is not necessary in this case
     */
    public static void main(String[] args) throws IOException, InterruptedException{
                   

        File currentDir = new File(".");
        File dataDir = new File(currentDir.getParent(), "data");
        
        for (int i = 1; i < 26; i++) {
            String filename = "Dataset" + i + ".txt";
            String filePath = new File(dataDir, filename).getAbsolutePath();
            Graph.main(new String[] { filePath });
        }
            
    }

    
    // public void createGraph(){

    // }

    
}


    /*
     * HOW TO DO ASSIGNMENT
     * 
     * Theoretical bound in performance - see if stays within bound
     * 
     * Number of vertices is not the same as vCount since some are not even dealt with since have no edges
     * Priority queue - logN operations
     * Instrumentation - counters
     * After plot - do we need to count vertex and edge operations
     * Maybe count number of comparisons
     * 
     * If increase number of datasets - get better data ()
     * 
     */


