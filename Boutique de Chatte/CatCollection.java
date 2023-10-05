import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
/**
 * CatCollection to stores origins objects
 * Performs search
 *
 * PUBLIC FEATURES:
 * // Constructors
 *    public CatCollection()
 *    public CatCollection(Origins ori)
 *
 * // Methods
 *    public int addCat(Cat c)
 *    public int catCount()
 *    public int originsCount()
 *    public Cat[] getAllCat()
 *    public Origins[] getAllOrigins()
 *    public double getAverageAge()
 *    public double getAveragePrice()
 *    public double getAverageWeight()
 *    public void loadCat(String file) throws IOException, ClassNotFoundException
 *    public void saveCat(String file) throws IOException
 *    public Cat[] search(int minPrice, int maxPrice, double minKilo, double maxKilo)
 *    public Cat[] search(int minAge, int maxAge)
 */
 @SuppressWarnings("unchecked")
public class CatCollection
{
   /*Constants that were returned by the addCat method
      shows new data addedd successfully */
	public static final int NO_ERROR = 0;
   
   // new cat is not added successfully - origins has reached limit (20 cats)
	public static final int CAT_MAXIMUM_REACHED = 1;
   
   // returned bcs system has reached the limit
	public static final int ORIGINS_MAXIMUM_REACHED = 2;

	private final int maxOrigins = 20;
	private final int maxCat = 20;

	private Origins[] origins = new Origins[0];

	public CatCollection(){}

   //to add cat collection
	public CatCollection(Origins ori)
	{
		addOrigins(ori);
	}

   /* add cat to CatCollection and file it
         or create new origins if new*/
	public int addCat(Cat c)
	{
		Origins man;
		String name = c.getOrigins();
		int index = -1;
		int result = NO_ERROR;

		for (int i = 0; i < origins.length; i++)
		{
			// if origins already exists
			if (origins[i].getOriginsName().equalsIgnoreCase(name))
				index = i;
		}

		// if origins do not exist
		if (index == -1)
		{
			if (origins.length < maxOrigins)
			{
				man = new Origins(name, c);
				addOrigins(man);
			}
			else
				result = ORIGINS_MAXIMUM_REACHED;
		}
		else
		{
			if (origins[index].catCount() < maxCat)
				origins[index].addCat(c);
			else
				result = CAT_MAXIMUM_REACHED;
		}

		return result;
	}

   //add origins object to CatCollection
	private void addOrigins(Origins man)
	{
		origins = resizeArray(origins, 1);
		origins[origins.length - 1] = man;
	}

   //count number of cats 
	public int catCount()
	{
		int count = 0;

		for (int i = 0; i < origins.length; i++)
			count += origins[i].catCount();

		return count;
	}

   //count total number of origins
	public int originsCount()
	{
		return origins.length;
	}

   // get all cats from all origins
	public Cat[] getAllCat()
	{
		Vector result = new Vector();
		Cat[] cat;
		for (int i = 0; i < origins.length; i++)
		{
			cat = origins[i].getAllCat();
			for (int j = 0; j < cat.length; j++)
			{
				result.addElement(cat[j]);
			}
		}

		return CatShopSystem.vectorToCat(result);
	}

   // get all origins
	public Origins[] getAllOrigins()
	{
		return origins;
	}

   // calc average age of all cats
	public double getAverageAge()
	{
		Cat[] cat;
		double result = 0;
		int count = 0;

		for (int i = 0; i < origins.length; i++)
		{
			cat = origins[i].getAllCat();
			for (int j = 0; j < cat.length; j++)
			{
				result += cat[j].getAge();
				count++;
			}
		}
		if (count == 0)
			return 0;
		else
			return (result / count);
	}

   // calc average weight of all cats (in kg)
   public double getAverageKilo()
	{
		Cat[] cat;
		double result = 0;
		int count = 0;

		for (int i = 0; i < origins.length; i++)
		{
			cat = origins[i].getAllCat();
			for (int j = 0; j < cat.length; j++)
			{
				result += cat[j].getWeight();
				count++;
			}
		}
		if (count == 0)
			return 0;
		else
			return (result / count);
	}

   // calc average price of all cats
	public double getAveragePrice()
	{
		Cat[] cat;
		double result = 0;
		int count = 0;

		for (int i = 0; i < origins.length; i++)
		{
			cat = origins[i].getAllCat();
			for (int j = 0; j < cat.length; j++)
			{
				result += cat[j].getPrice();
				count++;
			}
		}
		if (count == 0)
			return 0;
		else
			return (result / count);
	}

   // load all cats into the origins object from data file
	public void loadCat(String file) throws IOException, ClassNotFoundException
	{

		ObjectInputStream obj = new ObjectInputStream(new FileInputStream(file));
		origins = (Origins[])obj.readObject();
		obj.close();
	}

   // resize the origins array
	private Origins[] resizeArray(Origins[] inArray, int extendBy)
	{
		Origins[] result = new Origins[inArray.length + extendBy];

		for (int i = 0; i < inArray.length; i++)
		{
			result[i] = inArray[i];
		}

		return result;
	}

   //saving cats to a binary file
	public void saveCat(String file) throws IOException
	{
		int flag = 0;
		int items = origins.length;
		Origins temp;

		if (origins.length > 0)
		{
			do
			{
				flag = 0;
				for (int i = 0; i < items; i++)
				{
					if (i + 1 < items)
					{
						if (origins[i].getOriginsName().compareTo(origins[i + 1].getOriginsName()) > 0)
						{
							temp = origins[i];
							origins[i] = origins[i + 1];
							origins[i + 1] = temp;
							flag++;
						}
					}
				}
			} while (flag > 0);

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));

			out.writeObject(origins);
			out.close();
		}
	}
   
   //search cat by age
	public Cat[] search(int minAge, int maxAge)
	{
		Cat[] cat;
		cat = getAllCat();
		Vector result = new Vector();

		if (maxAge == -1)
		{
			for (int i = 0; i < cat.length; i++)
			{
				if (cat[i].getAge() >= minAge)
				{
					result.addElement(cat[i]);
				}
			}
		}
		else
		{
			for (int i = 0; i < cat.length; i++)
			{
				if (cat[i].getAge() >= minAge && cat[i].getAge() <= maxAge)
				{
					result.addElement(cat[i]);
				}
			}
		}

		return CatShopSystem.vectorToCat(result);
	}

   //search cat by price AND weight (in kg)
	public Cat[] search(int minPrice, int maxPrice, double minKilo, double maxKilo)
	{
		Vector result = new Vector();
		int price;
		double kilo;
		Cat[] cat;
		cat = getAllCat();

		for (int i = 0; i < cat.length; i++)
		{
			price = cat[i].getPrice();
			kilo = cat[i].getWeight();

			if (price >= minPrice && price <= maxPrice)
 				if (kilo >= minKilo && kilo <= maxKilo)
					result.add(cat[i]);
		}

		return CatShopSystem.vectorToCat(result);
	}
} // end of class