import java.io.IOException;
import java.io.File;

public class GraphExperiment{

    public static void main(String[] args) throws IOException, InterruptedException{
                   

        File currentDir = new File(".");
        File dataDir = new File(currentDir.getParent(), "data");
        
        for (int i = 1; i < 26; i++) {
            String filename = "Dataset" + i + ".txt";
            String filePath = new File(dataDir, filename).getAbsolutePath();
            Graph.main(new String[] { filePath });
        }
        
            // // create the process builder with the java command and its arguments
            // String[] command = {"java", "Graph", filename};
            // ProcessBuilder pb = new ProcessBuilder(command);

            // // start the process and wait for it to finish
            // Process process = pb.start();
            // process.waitFor();

            // // read the output from the process
            // BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            // String line;
            // while ((line = reader.readLine()) != null) {
            //     System.out.println(line);
            // }

            
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
     * Have code to run experiment multiple times - this loop
     * 
     * 
     * After plot - do we need to count vertex and edge operations
     * Maybe count number of comparisons
     * 
     * If increase number of datasets - get better data ()
     * 
     */


