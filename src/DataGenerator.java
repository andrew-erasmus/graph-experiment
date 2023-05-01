// Program to generate edges for a graph data structure with the corresponding cost.
// Andrew Erasmus
// 01/05/2023

import java.lang.Math;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
import java.io.File;

public class DataGenerator {

    /**
     * Main method of the DataGenerator class to take in arguments to generate datasets.
     * The user will enter the filename and the number of verticies and edges they wish to have in a graph data structure.
     * This will randomise a name for the verticies in the form NodeX, to form an edge to be written to the file in
     * the form NodeX NodeY Z where Z is a random weight. 
     * Edges cannot be duplicated and an edge cannot go to the vertex it comes from.
     * @param args The arguements from the user's inputs
     */
    public static void main(String[] args) {

        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter a filename: ");
            String filename = input.next();
            input.nextLine();
            System.out.print("Enter the number of Vertices: ");
            int numV = input.nextInt();
            input.nextLine();
            System.out.print("Enter the number of Edges: ");
            int numE = input.nextInt();
            input.nextLine();

            File currentDir = new File(".");
            File dataDir = new File(currentDir.getParent(), "data");
            String filePath = new File(dataDir, filename).getAbsolutePath();
            PrintWriter fileOut = new PrintWriter(new FileWriter(filePath, false));
            String output = "";

            HashMap<String, Integer> edges = new HashMap<String, Integer>();

            int countUnique = 0;
            while (countUnique < numE) {
                int ranCost = (int) ((Math.random() * (11 - 1)) + 1);
                int source = (int) ((Math.random() * (numV + 1 - 1)) + 1);
                int dest = (int) ((Math.random() * (numV + 1 - 1)) + 1);

                String inEdge = "Node" + source + " " + "Node" + dest;

                if (source != dest && edges.get(inEdge) == null) {
                    edges.put(inEdge, ranCost);
                    output += inEdge + " " + ranCost + "\n";
                    countUnique++;
                }
            }

            fileOut.println(output);
            fileOut.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}