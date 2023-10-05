import java.util.*;
/**
 * Origins.java - Stores and retrieves Cat objects
 *
 * PUBLIC FEATURES:
 * // Constructors
 *    public Origins(String ori, Cat c)
 *
 * // Methods
 *    public void addCat(Cat c)
 *    public int catCount()
 *    public Cat[] getAllCat()
 *    public String getOriginsName()
 *    private Cat[] resizeArray(Cat[] c, int extendBy)
 *    public void setOriginsName(String ori)
 */
public class Origins implements java.io.Serializable
{
	private String origins; //cat's origins
	private Cat[] cat = new Cat[0]; //stores information about all cats by each origins

   //add to origins
	public Origins(String ori, Cat c)
	{
		origins = ori.toUpperCase();
		addCat(c);
	}

   //add new cat to origins
	public void addCat(Cat c)
	{
		cat = resizeArray(cat, 1);
		cat[cat.length - 1] = c;
	}

	/**
	 * count cars by manufacturer
	 * @return number of cars in manufacturer
	 */
   //count num of cats by the origins
	public int catCount()
	{
		return cat.length;
	}

   // get cats from the origins
	public Cat[] getAllCat()
	{
		return cat;
	}

   //get origins
	public String getOriginsName()
	{
		return origins;
	}

   //resize array 
	private Cat[] resizeArray(Cat[] c, int extendBy)
	{
		Cat[] result = new Cat[c.length + extendBy];

		for (int i = 0; i < c.length; i++)
		{
			result[i] = c[i];
		}

		return result;
	}

	public void setOriginsName(String ori)
	{
		origins = ori.toUpperCase();
	}
}