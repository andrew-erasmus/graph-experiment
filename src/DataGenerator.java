import java.util.Random;
import java.lang.Math;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;

public class DataGenerator{
    
    public static void main(String[] args){

        try{
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

            PrintWriter fileOut = new PrintWriter(new FileWriter(filename, false));
            String output="";

            HashMap<String, Integer> edges = new HashMap<String, Integer>();

            for(int i=0; i<numE ;i++){
                int ranCost =(int)((Math.random() * (10 - 1)) + 1);
                int source =(int)((Math.random() * (numV - 1)) + 1);
                int dest = (int)((Math.random() * (numV - 1)) + 1);
                
                String inEdge = "Node"+source+" "+"Node"+dest;

                if (source != dest && edges.get(inEdge) == null){
                    edges.put(inEdge, ranCost);
                    output += inEdge+" "+ranCost+"\n";
                }else{
                    i--;
                    // while(source == dest || edges.get(inEdge) != null){
                    //     ranCost =(int)((Math.random() * (10 - 1)) + 1);
                    //     source =(int)((Math.random() * (numV - 1)) + 1);
                    //     dest = (int)((Math.random() * (numV - 1)) + 1);
                    //     inEdge = "Node"+source+" "+"Node"+dest; 
                    //     if (source != dest && edges.get(inEdge) == null){
                    //         edges.put(inEdge, ranCost);
                    //         output += inEdge+" "+ranCost+"\n";
                    //     }
                    // }
                } 
            }

            fileOut.println(output);
            fileOut.close();

        }catch(IOException e){
            e.printStackTrace();
        }

       
    }
}