/*This is the main JFrame application.*/
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.nio.file.*;
import java.io.*;
import static java.nio.file.StandardOpenOption.*;
/**
 * PUBLIC FEATURES:
 * // Constructor(s)
 *    public CatShopSystem(String f)
 *
 * // Methods
 *    public void aboutMenuItemClicked()
 *    public void actionPerformed(ActionEvent ev)
 *    public void addCatUpdateListener(Object listener)
 *    public int addNewCat(Cat c)
 *    public void closing()
 *    public void componentHidden(ComponentEvent ev)
 * 	public void componentMoved(ComponentEvent ev)
 *	   public void componentResized(ComponentEvent ev)
 *    public void componentShown(ComponentEvent ev)
 *    public static double[] convertToRange(String s)
 *    public Cat[] getAllCat()
 *    public boolean getCatUpdated()
 *    public double getStatistics(int type)
 *    public static void main(String[] args)
 *    public Cat[] search(int minAge, int maxAge)
 *    public Cat[] search(int minPrice, int maxPrice, double minDistance, double maxDistance)
 *    public void setCatUpdated()
 *    public void stateChanged(ChangeEvent ev)
 *    public static Cat[] vectorToCat(Vector v)
 */
 
@SuppressWarnings("unchecked")
public class CatShopSystem extends JFrame implements ActionListener, ComponentListener, ChangeListener
{
	//current version of cat shop system
   public static final double APP_VERSION = 1.0;
   
   // the parameter in 'getStatistics(int type)' method to count the total number of cats in the system
	public static final int CAT_COUNT = 0;

   // a parameter in 'getStatistics(int type)' method to count the total number of origins in the system 
	public static final int ORIGINS_COUNT = 1;

   //a parameter in 'getStatistics(int type)' method to count the average price of cats 
	public static final int AVERAGE_PRICE = 2;

   // a parameter in 'getStatistics(int type)' method to count the average weight of cats in the system 
	public static final int AVERAGE_KILO = 3; 

   // the parameter in 'getStatistics(int type)' method to count the average age of cats in the system 
	public static final int AVERAGE_AGE = 4;

	private String file;
	private AboutDialog aboutDlg;
	private boolean catUpdated = false;
	private Vector registeredListeners = new Vector();
	private CatCollection catCollection;
   private JPanel titlePanel = new JPanel(new GridLayout(2, 1));
	private JPanel topPanel = new JPanel(new BorderLayout());
	//private JPanel titlePanel = new JPanel(new GridLayout(2, 1));
   private JLabel pictureLabel = new JLabel();
	private JLabel statusLabel = new JLabel();
	//private JLabel pictureLabel = new JLabel();
   private JTabbedPane theTab = new JTabbedPane(JTabbedPane.LEFT);
	private JLabel catCoLabel = new JLabel("Famera Chatte.Co", JLabel.CENTER); 
	private JLabel salesSysLabel = new JLabel("CAT SHOP SYSTEM ", JLabel.CENTER);
	//private JTabbedPane theTab = new JTabbedPane(JTabbedPane.LEFT);
	private JMenuBar menuBar = new JMenuBar();
	private JMenu fileMenu = new JMenu("File");
	private JMenuItem aboutItem = new JMenuItem("About");
	private JMenuItem exitItem = new JMenuItem("Exit");
	private WindowCloser closer = new WindowCloser();

   // to store or retrieve cat data
	public CatShopSystem(String f)
	{
		super("Boutique de Chatte"); // Cat Shop (France)

		addWindowListener(closer);
		addComponentListener(this);
		theTab.addChangeListener(this);

		//set size
      setSize(780, 560); 

		Container c = getContentPane();
		catCollection = new CatCollection();

		file = f;
      
      //try code and catch exception
		try
		{
			catCollection.loadCat(file);
		}
		catch (java.io.FileNotFoundException exp)
		{
			System.out.println("The data file, 'cat.dat' doesn't exist. Please create an empty file named 'cat.dat'");
			System.exit(0);
		}

		// empty cat.dat file (boleh ignored)
		catch (java.io.EOFException exp){}
      
		catch (java.io.IOException exp)
		{
			System.out.println("The file 'cat.dat' probably has corrupted. Please delete it and create a new empty data file.");
			System.exit(0);
		}
		catch (Exception exp)
		{
			System.out.println("An error has occur while loading 'cat.dat'. Please try deleting and creating a new empty file.");
			System.exit(0);
		}
      
      //set up font
		catCoLabel.setFont(new Font("Adobe Kaiti Std R", Font.PLAIN | Font.ITALIC, 20));
		salesSysLabel.setFont(new Font("Bowlby One SC", Font.BOLD, 40));

		// create menu bar
		menuBar.add(fileMenu);
		fileMenu.add(aboutItem);
		fileMenu.add(exitItem);
		aboutItem.addActionListener(this);
		exitItem.addActionListener(this);

		// add menu bar
		setJMenuBar(menuBar);

		// set border on status bar label to make it look like a panel
		statusLabel.setBorder(new javax.swing.border.EtchedBorder());

		// load the cat co. logo at the top panel
		pictureLabel.setIcon(new ImageIcon("ReLogo.png"));
      
      //add the panel
		titlePanel.add (catCoLabel);
		titlePanel.add(salesSysLabel);
		topPanel.add(pictureLabel, "West");
		topPanel.add(titlePanel, "Center");

		WelcomePanel welcomePanel = new WelcomePanel(this, f);
		AddCatPanel addCatPanel = new AddCatPanel(this);
		ShowAllCatPanel showAllCatPanel = new ShowAllCatPanel(this);
		SearchByAgePanel searchByAgePanel = new SearchByAgePanel(this);
		SearchByOtherPanel searchByOtherPanel = new SearchByOtherPanel(this);

		theTab.add("<< WELCOME >>", welcomePanel);
		theTab.add("Insert a Cat's data", addCatPanel);
		theTab.add("Show all Cat's Details", showAllCatPanel);
		theTab.add("Search on age", searchByAgePanel);
		theTab.add("Search on Price and Weight", searchByOtherPanel);
      theTab.setFont(new Font("Dutch801 Rm BT", Font.BOLD, 12));

		theTab.addChangeListener(showAllCatPanel);
		theTab.addChangeListener(welcomePanel);

		theTab.setSelectedIndex(0);
      
		c.setLayout(new BorderLayout());
		c.add(topPanel, "North");
		c.add(theTab, "Center");
		c.add(statusLabel, "South");
	}
   
   //method() to show dialog
	public void aboutMenuItemClicked()
	{
		if (aboutDlg == null)
			aboutDlg = new AboutDialog(this, "About", true);
		aboutDlg.showAbout();
	}

   /* receives menu click events and handles it
      object for ActionEvent*/
	public void actionPerformed(ActionEvent ev)
	{
		if (ev.getSource() == aboutItem)
			aboutMenuItemClicked();
		else if (ev.getSource() == exitItem)
			closing();
	}

	/* addimg an obj to receive notification when a new cat data is added
	      @param listener a listener object */
	public void addCatUpdateListener(Object listener)
	{
		if (!(listener == null))
			registeredListeners.add(listener);
	}

   //add new cat using CatCollection class
	public int addNewCat(Cat c)
	{
		return catCollection.addCat(c);
	}

	//handles closing events for the Cat System dan saves any updated data
	public void closing()
	{
		boolean ok;
		if (catUpdated)
		{
			do
			{
				try
				{
					catCollection.saveCat(file);
					ok = true;
				}
				catch (java.io.IOException exp)
				{
					int result = JOptionPane.showConfirmDialog(this, "The data file could not be written, possibly because you don't have access to this location.\nIf you chose No to retry you will lose all car data from this session.\n\nWould you like to reattempt saving the data file?", "Problem saving data", JOptionPane.YES_NO_OPTION);

					// checks if user wants to re-attempt saving the data file
					if (result == JOptionPane.YES_OPTION)
						ok = false;
					else
						ok = true;
				}
			} while (!ok);
		} // end if

		System.exit(0);//terminate
	}

	public void componentHidden(ComponentEvent ev) {}

	public void componentMoved(ComponentEvent ev) {}

   /* receive events when JFrame is resized - pastikan app do not resized
         to avoid display problem */
	public void componentResized(ComponentEvent ev)
	{
		if (this == ev.getComponent())
		{
			Dimension size = getSize();

			if (size.height < 530)
				size.height = 530;
			if (size.width < 675)
				size.width = 675;

			setSize(size);
		}
	}

	public void componentShown(ComponentEvent ev) {}

	/**
	 * Converts a range string such as "50-100" or "20+" to a double array with minimum and maximum
	 * bounds
	 *
	 * @param s a string containing a valid range. "200", "100-200", "20+" are all examples
	 * of valid ranges
	 * @return array of 2 elements. First element indicating minimum bounds, second element
	 * indicating maximum bounds. A value of -1 for minimum and maximum bounds indicates an error
	 * in converting the parameter to a proper range. A value of -1 for the maximum bounds and
	 * a value > 0 for the minimum bounds indicates the maximum bounds is infinity in theory.
	 * If both bounds are greater than 0, this indicates a valid range a to b, where a and b
	 * can be equal.
	 */
	public static double[] convertToRange(String s)
	{
		String[] parts = s.trim().split("-");
		double[] bounds = new double[2];
      
      // try code and catch exception
		try
		{
			// if true then form of range is 'm+' or 'm'
			if (parts.length == 1)
			{
				String c = s.substring(s.length() - 1);

				// if form "m+"
				if (c.equals("+"))
				{
					// get lower bounds from the string
					bounds[0] = Double.parseDouble(s.substring(0, s.length() - 1));
					// no upper maximum
					bounds[1] = -1;
				}
				// if true - form 'm'
				else
				{
					// upper bounds == lower bounds
					bounds[0] = Double.parseDouble(s);
					bounds[1] = bounds[0];
				}
			}
			// if true - form "m-n"
			else if(parts.length == 2)
			{
				bounds[0] = Double.parseDouble(parts[0]);
				bounds[1] = Double.parseDouble(parts[1]);
				if (bounds[0] > bounds[1])
				{
					//incorrect bounds (e.g 10-5)
					bounds[0] = -1;
					bounds[1] = -1;
				}
			}
		}
		catch (NumberFormatException exp)
		{
			//incorrect bounds format
			bounds[0] = -1;
			bounds[1] = -1;
		}

		return bounds;
	}

   // get all cat list from CatCollection
	public Cat[] getAllCat()
	{
		return catCollection.getAllCat();
	}

   // get (check) any cat updated from last program run
	public boolean getCatUpdated()
	{
		// return to chech wheter the cat got updated
      return catUpdated;
	}

   // retrieve statistics ab the cat collection
	public double getStatistics(int type)
	{
		double result = 0;
		if (type == CAT_COUNT)
		{
			result	= catCollection.catCount();
		}
		else if (type == ORIGINS_COUNT)
		{
			result = catCollection.originsCount();
		}
		else if (type == AVERAGE_PRICE)
		{
			result = catCollection.getAveragePrice();
		}
		else if (type == AVERAGE_KILO)
		{
			result = catCollection.getAverageKilo();
		}
		else if (type == AVERAGE_AGE)
		{
			result = catCollection.getAverageAge();
		}
		return result;
	} // end of getStatistics

   // The main method
	public static void main(String[] args)
	{
		CatShopSystem catSales = new CatShopSystem("cat.dat");
		catSales.setVisible(true);
	}
   
   // method to search cat by age
	public Cat[] search(int minAge, int maxAge)
	{
		// return cat that matches the search
      return catCollection.search(minAge, maxAge);
	}

   //search by price and weight
	public Cat[] search(int minPrice, int maxPrice, double minKilo, double maxKilo)
	{
		return catCollection.search(minPrice, maxPrice,  minKilo, maxKilo);
	}

   /* call this method - alert CatShopSystem that data updated
         send messages to registered cat update listeners */
	public void setCatUpdated()
	{
		catUpdated = true;

		for (int i = 0; i < registeredListeners.size(); i++)
		{
			Class[] paramType = {CatUpdateEvent.class};
			Object[] param = {new CatUpdateEvent(this)};
         
         //try code and catch exception
			try
			{
				//get reference to the method that want to invoke to the listener
				java.lang.reflect.Method callingMethod = registeredListeners.get(i).getClass().getMethod("catUpdated", paramType);
				//invoke the method with the parameters
				callingMethod.invoke(registeredListeners.get(i), param);
			}
			catch (NoSuchMethodException exp)
			{
				System.out.println("WARNING, 'public catUpdated(CatEvent)' method does not exist in " + registeredListeners.get(i).getClass().getName() + ". No cat update events will be received.");
			}
			catch (IllegalAccessException exp)
			{
				System.out.println("WARNING, the 'public catUpdated(CarEvent)' method could not be called. No cat update events will be received.");
			}
			catch (Exception exp){}
		}
	}

   /* when we select another tab (JTabbedPane changed), the title bar text is change too
          ChangeEvent object */
	public void stateChanged(ChangeEvent ev)
	{
		if (ev.getSource() == theTab)
			statusLabel.setText("Current panel: " + theTab.getTitleAt(theTab.getSelectedIndex()));
	}

   // convert vector to cat array
	public static Cat[] vectorToCat(Vector v)
	{
		Cat[] ret = new Cat[v.size()];

		for (int i = 0; i < v.size(); i++)
		{
			ret[i] = (Cat)v.elementAt(i);
		}
      // ret contains the cat objects from the vector
		return ret;
	}

	class WindowCloser extends WindowAdapter
	{
      // call the system's main close ev
		public void windowClosing(WindowEvent ev)
		{
			closing();
		}
	}
} //end of class