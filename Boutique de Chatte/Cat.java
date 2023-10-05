import java.util.*;
/**
 * Cat to stores information about cat. 
 * Calculates cat age (from current date)
 *
 * PUBLIC FEATURES:
 * // Constructors
 *    public Cat()
 *    public Cat(String man, String mod, String info)
 *
 * // Methods
 *    public String getOrigins()
 *    public int getAge()
 *    public String getInformation()
 *    public double getWeight()
 *    public String getBreed()
 *    public int getPrice()
 *    public int getYear()
 *    public void setInformation(String info)
 *    public void setWeight(double kg)
 *    public void setOrigins(String ori)
 *    public void setBreed(String mod)
 *    public void setPrice(int cost)
 *    public void setYear(int yr)
 */
public class Cat implements java.io.Serializable
{
   private String breed;
	private String origins;
	private String information;
	private int year;
	private int price;
	private double weight;

	public Cat(){}

	public Cat(String man, String mod, String info)
	{
		breed = mod;
		origins = man.toUpperCase();
		information = info;
	}

	/**
	 * calculates using current year - model year
	 */
   //calculate use current year
	public int getAge()
	{
		GregorianCalendar calendar = new GregorianCalendar();
		return (calendar.get(Calendar.YEAR) - year);
	}

	public String getInformation()
	{
		return information;
	}
   
   public double getWeight()
	{
		return weight;
	}

	public String getOrigins()
	{
		return origins;
	}

	public String getBreed()
	{
		return breed;
	}

	public int getPrice()
	{
		return price;
	}

	public int getYear()
	{
		return year;
	}

	public void setInformation(String info)
	{
		information = info;
	}
   
   public void setWeight(double kg)
	{
		weight = kg;
	}

	public void setOrigins(String org)
	{
		origins = org.toUpperCase();
	}

	public void setBreed(String mod)
	{
		breed = mod;
	}

	public void setPrice(int cost)
	{
		price = cost;
	}

	public void setYear(int yr)
	{
		year = yr;
	}
} // end of cat class