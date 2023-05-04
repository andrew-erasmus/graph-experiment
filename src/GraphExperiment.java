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

            
            writer.write("NumV, NumE, vCount, eCount, pqCount, totalOperations");
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



