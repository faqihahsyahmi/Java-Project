/**
 * CatUpdateEvent.java
 *     event that sent to registered listeners
 *
 * PUBLIC FEATURES:
 * // Constructors
 *    public CatUpdateEvent(Object source)
 */
public class CatUpdateEvent extends java.util.EventObject
{
	public CatUpdateEvent(Object source)
	{
		super(source);
	}
} // end of class
