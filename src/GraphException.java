// Used to signal violations of preconditions for
// various shortest path algorithms.
// Andrew Erasmus
// 01/05/2023
class GraphException extends RuntimeException
{
    
	private static final long serialVersionUID = 1L;

    /**
     * Constructor to generate the exception for the name passed in.
     * @param name
     */
	public GraphException( String name )
    {
        super( name );
    }
}
