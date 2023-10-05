//Welcome page (1st tab)
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
/**
 * WelcomePanel: displays basic statistics about the cats in the data file
 * @
 *
 * PUBLIC FEATURES:
 * // Constructors
 *    public WelcomePanel(CatShopSystem catSys, String data)
 *
 * // Methods
 *    public void catUpdated(CatUpdateEvent ev)
 *    public void stateChanged(ChangeEvent ev)
 */
 
public class WelcomePanel extends JPanel implements ChangeListener
{
	private CatShopSystem catSystem;
	private JLabel headingLabel = new JLabel("FAMERA CHATTE.CO CAT SHOP SYSTEM", JLabel.CENTER);
	private JLabel catLabel = new JLabel();
	private JLabel originsLabel = new JLabel();
	private JLabel avgPriceLabel = new JLabel();
	private JLabel avgKmLabel = new JLabel();
	private JLabel avgAgeLabel = new JLabel();
	private JLabel versionLabel = new JLabel();
	private JLabel dataSizeLabel = new JLabel();
	private JPanel statsPanel = new JPanel();
	private JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
	private boolean catUpdated = false;
	private String file;

	public WelcomePanel(CatShopSystem catSys, String data)
	{
		catSystem = catSys; //links to CatShopSystem object
		file = data;
		setLayout(new BorderLayout(0, 10));
		catSys.addCatUpdateListener(this);

		statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
		centerPanel.add(statsPanel);
		headingLabel.setBorder(new EmptyBorder(new Insets(10, 0, 0, 0)));
      headingLabel.setFont(new Font("Bahnschrift SemiLight SemiConde", Font.BOLD, 12));

		//update stat
      updateStats();

      //adding the panel
		statsPanel.add(catLabel);
		statsPanel.add(originsLabel);
		statsPanel.add(avgPriceLabel);
		statsPanel.add(avgKmLabel);
		statsPanel.add(avgAgeLabel);
		statsPanel.add(Box.createVerticalStrut(20));
		statsPanel.add(versionLabel);
		statsPanel.add(dataSizeLabel);

		add(headingLabel, "North");
		add(centerPanel, "Center");
	}

   //invoked when added a cat
	public void catUpdated(CatUpdateEvent ev)
	{
		if (ev.getSource() == catSystem)
		{
			catUpdated = true;
		}
	}

	/**
	 * when the tab on the main frame gets changed over to this one, we may have to update the
	 * car list with the latest version
	 *
	 * @param ev ChangeEvent object
	 */
    
	public void stateChanged(ChangeEvent ev)
	{
		// getSource from  JTabbedPane
		if (ev.getSource() instanceof JTabbedPane)
		{
			JTabbedPane tab = (JTabbedPane)ev.getSource();
			// choose Welcome Tab
			if (tab.getSelectedIndex() == 0)
			{
				// up date the statistics if haven't
				if (catUpdated)
				{
					// update statistic
					updateStats();
					// do not update unless new data added
					catUpdated = false;
				}
			}
		}
	}

   // update all statistics
	private void updateStats()
	{
		// receive new statistics
		int cat = (int)catSystem.getStatistics(CatShopSystem.CAT_COUNT);
		int origins = (int)catSystem.getStatistics(CatShopSystem.ORIGINS_COUNT);
		double avgPrice = Math.floor(catSystem.getStatistics(CatShopSystem.AVERAGE_PRICE) * 10 + 0.5) / 10;
		double avgKg = Math.floor(catSystem.getStatistics(CatShopSystem.AVERAGE_KILO) * 10 + 0.5) / 10;
		double avgAge = Math.floor(catSystem.getStatistics(CatShopSystem.AVERAGE_AGE) * 10 + 0.5) / 10;
		java.io.File f = new java.io.File(file);
		long size = f.length(); // get length of binary data file

		catLabel.setText("Total number of Cat: " + String.valueOf(cat));
		originsLabel.setText("Total number of Origins: " + String.valueOf(origins));
		avgPriceLabel.setText("Average cat price: RM " + String.valueOf(avgPrice));
		avgKmLabel.setText("Average cat weight (kg): " + String.valueOf(avgKg));
		avgAgeLabel.setText("Average cat age: " + String.valueOf(avgAge));
		versionLabel.setText("Cat Shop System, Version " + CatShopSystem.APP_VERSION);
		dataSizeLabel.setText("Size of data file: " + size + " bytes");
      
      //font setting
      catLabel.setFont(new Font("Bahnschrift SemiLight SemiConde", Font.PLAIN, 12));
      originsLabel.setFont(new Font("Bahnschrift SemiLight SemiConde", Font.PLAIN, 12));
      avgPriceLabel.setFont(new Font("Bahnschrift SemiLight SemiConde", Font.PLAIN, 12));
      avgKmLabel.setFont(new Font("Bahnschrift SemiLight SemiConde", Font.PLAIN, 12));
      versionLabel.setFont(new Font("Bahnschrift SemiLight SemiConde", Font.PLAIN, 12));
      dataSizeLabel.setFont(new Font("Bahnschrift SemiLight SemiConde", Font.PLAIN, 12));
      avgAgeLabel.setFont(new Font("Bahnschrift SemiLight SemiConde", Font.PLAIN, 12));
	}
} // end of class