// Represents a vertex in a graph
// Andrew Erasmus
// 01/05/2023

import java.util.List;
import java.util.LinkedList;

class Vertex
{
    public String     name;   // Vertex name
    public List<Edge> adj;    // Adjacent vertices
    public double     dist;   // Cost
    public Vertex     prev;   // Previous vertex on shortest path
    public int        scratch;// Extra variable used in algorithm

    /**
     * constructor of the vertex class
     * @param nm the name of the vertex
     */
    public Vertex( String nm )
      { name = nm; adj = new LinkedList<Edge>( ); reset( ); }

    /**
     * Resets the distance of the vertex to be infinity
     */
    public void reset( )
    //  { dist = Graph.INFINITY; prev = null; pos = null; scratch = 0; }    
    { dist = Graph.INFINITY; prev = null; scratch = 0; }
      
   // public PairingHeap.Position<Path> pos;  // Used for dijkstra2 (Chapter 23)
}
