// Program to generate a graph data structure, run Dijkstra's algorithm and get the number of operations when finding the shortest path.
// Andrew Erasmus
// 01/05/2023

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.Map;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.File;
import java.io.BufferedWriter;


// Graph class: evaluate shortest paths.
//
// CONSTRUCTION: with no parameters.
//
// ******************PUBLIC OPERATIONS**********************
// void addEdge( String v, String w, double cvw )
//                              --> Add additional edge
// void printPath( String w )   --> Print path after alg is run
// void unweighted( String s )  --> Single-source unweighted
// void dijkstra( String s )    --> Single-source weighted
// void negative( String s )    --> Single-source negative weighted
// void acyclic( String s )     --> Single-source acyclic
// ******************ERRORS*********************************
// Some error checking is performed to make sure graph is ok,
// and to make sure graph satisfies properties needed by each
// algorithm.  Exceptions are thrown if errors are detected.

public class Graph
{
    public static final double INFINITY = Double.MAX_VALUE;
    private Map<String,Vertex> vertexMap = new HashMap<String,Vertex>( );
    public static int vCount=0;
    public static int eCount=0;
    public static int pqCount=0;
    public static int totalOperations=0;

    /**
     * Add a new edge to the graph.
     * @param sourceName the name of the source vertex
     * @param destName the name of the destination vertex
     * @param cost the cost of the edge from source to destination
     * 
     */
    public void addEdge( String sourceName, String destName, double cost )
    {
        Vertex v = getVertex( sourceName );
        Vertex w = getVertex( destName );
        v.adj.add( new Edge( w, cost ) );
    }

    /**
     * Driver routine to handle unreachables and print total cost.
     * It calls recursive routine to print shortest path to
     * destNode after a shortest path algorithm has run.
     * @param destName the name of the destination vertex
     */
    public void printPath( String destName )
    {
        Vertex w = vertexMap.get( destName );
        if( w == null )
            throw new NoSuchElementException( "Destination vertex not found" );
        else if( w.dist == INFINITY )
            System.out.println( destName + " is unreachable" );
        else
        {
            System.out.print( "(Cost is: " + w.dist + ") " );
            printPath( w );
            System.out.println( );
        }
    }

    /**
     * If vertexName is not present, add it to vertexMap.
     * In either case, return the Vertex.
     * @param vertexName the name of the vertex to be returned
     */
    private Vertex getVertex( String vertexName )
    {
        Vertex v = vertexMap.get( vertexName );
        if( v == null )
        {
            v = new Vertex( vertexName );
            vertexMap.put( vertexName, v );
        }
        return v;
    }

    /**
     * Recursive routine to print shortest path to dest
     * after running shortest path algorithm. The path
     * is known to exist.
     * @param dest the destination vertex
     */
    private void printPath( Vertex dest )
    {
        if( dest.prev != null )
        {
            printPath( dest.prev );
            System.out.print( " to " );
        }
        System.out.print( dest.name );
    }
    
    /**
     * Initializes the vertex output info prior to running
     * any shortest path algorithm.
     */
    private void clearAll( )
    {
        for( Vertex v : vertexMap.values( ) )
            v.reset( );
    }


    /**
     * Single-source weighted shortest-path algorithm. (Dijkstra) 
     * using priority queues based on the binary heap
     * @param startName the name of the vertex to start with
     */
    public void dijkstra( String startName )
    {
        int opCountE=0;
        int opCountV=0;
        int opCountPQ=0;
        
        PriorityQueue<Path> pq = new PriorityQueue<Path>( );

        Vertex start = vertexMap.get( startName );
        if( start == null )
            throw new NoSuchElementException( "Start vertex not found" );

        clearAll( );
        pq.add( new Path( start, 0 ) ); start.dist = 0;
        
        int nodesSeen = 0;
        while( !pq.isEmpty( ) && nodesSeen < vertexMap.size( ) )
        {
            opCountPQ += (int)Math.log(pq.size())/Math.log(2);
            Path vrec = pq.remove( );
            Vertex v = vrec.dest;
            
            if( v.scratch != 0 )  // already processed v
                continue;
              
                
            // vertex is being processed
            opCountV++;
            v.scratch = 1;
            nodesSeen++;

            for( Edge e : v.adj )
            {
                Vertex w = e.dest;
                double cvw = e.cost;
                
                if( cvw < 0 )
                    throw new GraphException( "Graph has negative edges" );
                    
                //edge is being processed
                opCountE++;    
                if( w.dist > v.dist + cvw ) 
                {
                    w.dist = v.dist +cvw;
                    w.prev = v;
                    pq.add( new Path( w, w.dist ) );
                    opCountPQ += (int)Math.log(pq.size())/Math.log(2);
                }
            }
        }
        vCount=opCountV;
        eCount=opCountE;
        pqCount=opCountPQ;
        totalOperations=(opCountE+opCountV+opCountPQ);
    }

   
    /**
     * Process a request; return false if end of file.
     * @param inSource the name of the source vertex 
     * @param inDest the name of the destination vertex
     * @param g the graph data structure being processed
     */
    public static boolean processRequest( String inSource, String inDest, Graph g )
    {
        try
        {
            String startName = inSource;
            String destName = inDest;
            g.dijkstra( startName );
            g.printPath( destName );
            System.out.println("Number of operations: "+totalOperations);
                    
            //g.printPath( destName );
        }
        catch( NoSuchElementException e )
          { return false; }
        catch( GraphException e )
          { System.err.println( e ); }
        return true;
    }

    /**
     * A main routine that:
     * 1. Reads a file containing edges (supplied as a command-line parameter);
     * 2. Forms the graph;
     * 3. Runs dijkstra's algorithm to count the number of operations 
     * 4. Write the results to a file called results.txt
     * The data file is a sequence of lines of the format
     *    source destination cost
     * @param args
     */
    public static void main( String [ ] args )
    {
        Graph g = new Graph( );
        String sourceNode="";
        String destNode="";
        int lineCount=0;
        try
        {   	
            FileReader fin = new FileReader(args[0]);
            FileReader finForNodes = new FileReader(args[0]);
            Scanner graphFile = new Scanner( fin );
            Scanner firstLine = new Scanner( finForNodes );

            // Read the edges and insert
            String line;
            String nodes = firstLine.nextLine();
            String[] splitLine = nodes.split(" ");
            sourceNode=splitLine[0];
            destNode=splitLine[1];
            

            while( graphFile.hasNextLine( ) )
            {
                line = graphFile.nextLine( );
                lineCount++;
                StringTokenizer st = new StringTokenizer( line );
                
                try
                {
                    if( st.countTokens( ) != 3 )
                    {
                        System.err.println( "Skipping ill-formatted line " + line );
                        continue;
                    }
                    String source  = st.nextToken( );
                    String dest    = st.nextToken( );
                    int    cost    = Integer.parseInt( st.nextToken( ) );
                    g.addEdge( source, dest, cost );
                }
                catch( NumberFormatException e )
                  { System.err.println( "Skipping ill-formatted line " + line ); }
             }
             graphFile.close();
             firstLine.close();
         }
         catch( IOException e )
           { System.err.println( e ); }

         System.out.println( "File read..." );
         System.out.println( g.vertexMap.size( ) + " vertices" );

         //get the first edge of the file to be the source and destination node to calculate shortest distances
         // edits the program so that there is no user input
         processRequest(sourceNode, destNode, g );
         try{
            File currentDir = new File(".");
            File dataDir = new File(currentDir.getParent(), "data");
            File outputFile = new File(dataDir, "results.csv");

            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile.getAbsolutePath(),true));

            //int e=lineCount-1;
            //int v=g.vertexMap.size();
            //int bound = (int)(Math.log(Math.abs(e)) + Math.log(v) / Math.log(2));
            // Write data
            writer.write(g.vertexMap.size()+", "+(lineCount - 1)+", "+vCount+", "+eCount+", "+pqCount+", "+totalOperations/*+", "+bound*/);
            writer.newLine();
            // Close the writer
            writer.close();
         }catch(IOException e){
            e.printStackTrace();
         }
         
    }
}
