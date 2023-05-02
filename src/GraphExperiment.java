// Program to run the graph experiment to count the number of operations when running dijkstra's algorithm for 25 datasets
// Andrew Erasmus
// 01/05/2023

import java.io.IOException;
import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
public class GraphExperiment{

    /**
     * Main method to run the experiment for the 25 datasets
     * @param args the user's input from the output, which is not necessary in this case
     */
    public static void main(String[] args) throws IOException, InterruptedException{
                   

        
        
        try{
            File currentDir = new File(".");
            File dataDir = new File(currentDir.getParent(), "data");
            File outputFile = new File(dataDir, "results.csv");

            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile.getAbsolutePath(),false));

            
            writer.write("NumV, NumE, vCount, eCount, pqCount, totalOperations, |E|log|V|");
            writer.newLine();

            writer.close();

            for (int i = 1; i < 26; i++) {
                String filename = "Dataset" + i + ".txt";    
                String filePath = new File(dataDir, filename).getAbsolutePath();
                Graph.main(new String[] { filePath });
            }
        }catch(IOException e){
            e.printStackTrace();
        }            
    }
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


