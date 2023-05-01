// Represents an edge in the graph.
// Andrew Erasmus
// 01/05/2023
class Edge
{
    public Vertex     dest;   // Second vertex in Edge
    public double     cost;   // Edge cost
    
    /**
     * Constructor for the edge of a graph with a destination vertex and cost
     * @param d the destination vertex for this edge
     * @param c the cost of the edge from the source to the desination vertex
     */
    public Edge( Vertex d, double c )
    {
        dest = d;
        cost = c;
    }
}
