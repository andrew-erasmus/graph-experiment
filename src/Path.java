// Represents an entry in the priority queue for Dijkstra's algorithm.
// Andrew Erasmus
// 01/05/2023

class Path implements Comparable<Path>
{
    public Vertex     dest;   // w
    public double     cost;   // d(w)
    
    /**
     * Constructor for the path to a vertex
     * @param d the destination vertex
     * @param c the cost to the current vertex
     */
    public Path( Vertex d, double c )
    {
        dest = d;
        cost = c;
    }
    
    /**
     * Compare to method to see if the current path to a vertex is less than the the other path
     * @param rhs the other path being compared
     */
    public int compareTo( Path rhs )
    {
        double otherCost = rhs.cost;
        
        return cost < otherCost ? -1 : cost > otherCost ? 1 : 0;
    }
}
