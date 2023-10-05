import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * SearchByOtherPanel to obtaining search parameters by using cat price and cat's weight (in kg)
 *
 * PUBLIC FEATURES:
 * // Constructors
 *    public SearchByOtherPanel(CatShopSystem catSys)
 *
 * // Methods
 *    public void actionPerformed(ActionEvent ev)
 */
public class SearchByOtherPanel extends JPanel implements ActionListener
{
	//NANTI TUKAR KPD HARGA KUCING
   private final String[] price = {"300-1000", "1001-2000", "2001-3000", "3001-4000",
		"4001-5000", "5001-10000", "10001-15000", "15001-20000", "20001-25000", "25001-30000", "30001+"};
	private final String[] weight = {"2.0 - 2.5", "2.501 - 3.0", "3.001 - 3.5", "3.501 - 4.0", "4.001 - 4.5", "4.501 - 5.0",
		"5.001 - 5.5", "5.501 - 6.0", "6.001 - 6.5", "6.501 - 8.0"};
	private Cat[] catList;
	private CatShopSystem catSystem;
	private int currentIndex = 0;
	private JLabel headingLabel = new JLabel("Search on Price and Weight");
	private JLabel priceLabel = new JLabel("Price");
	private JLabel weightLabel = new JLabel("Weight in kg");
	private JButton searchButton = new JButton("Search");
	private JButton resetButton = new JButton("Reset");
	private JButton previousButton = new JButton("Previous");
	private JButton nextButton = new JButton("Next");
	private JComboBox priceCombo = new JComboBox(price);
	private JComboBox weightCombo = new JComboBox(weight);
	private JPanel topPanel = new JPanel();
	private JPanel pricePanel = new JPanel();
	private JPanel weightPanel = new JPanel();
	private JPanel priceWeightPanel = new JPanel();
	private JPanel searchButtonsPanel = new JPanel();
	private JPanel navigateButtonsPanel = new JPanel();
	private CatDetailsComponents catComponents = new CatDetailsComponents();

	public SearchByOtherPanel(CatShopSystem catSys)
	{
		catSystem = catSys; //inks to CatShopSystem object
		setLayout(new BorderLayout());
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

		previousButton.addActionListener(this);
		nextButton.addActionListener(this);
		resetButton.addActionListener(this);
		searchButton.addActionListener(this);

		pricePanel.add(priceLabel);
		pricePanel.add(priceCombo);
		weightPanel.add(weightLabel);
		weightPanel.add(weightCombo);
		priceWeightPanel.add(pricePanel);
		priceWeightPanel.add(weightPanel);

		searchButtonsPanel.add(searchButton);
		searchButtonsPanel.add(resetButton);
		navigateButtonsPanel.add(previousButton);
		navigateButtonsPanel.add(nextButton);

		headingLabel.setAlignmentX(0.5f);
		topPanel.add(Box.createVerticalStrut(10));
		topPanel.add(headingLabel);
		topPanel.add(Box.createVerticalStrut(10));
		topPanel.add(priceWeightPanel);
		topPanel.add(searchButtonsPanel);
		topPanel.add(Box.createVerticalStrut(15));
		catComponents.add(navigateButtonsPanel, "Center");
		catComponents.setVisible(false);

		add(topPanel, "North");
		add(catComponents, "Center");
	}

   //checking button clicks
	public void actionPerformed(ActionEvent ev)
	{
		if (ev.getSource() == searchButton)
			searchButtonClicked();
		else if (ev.getSource() == resetButton)
			resetButtonClicked();
		else if (ev.getSource() == previousButton)
			previousButtonClicked();
		else if (ev.getSource() == nextButton)
			nextButtonClicked();
	}

	/**
	 * get next index if it exists, and display it visually using CarDetailsComponents
	 */
   /* display next index (if any)
      display appropriate message (if none) */
	private void nextButtonClicked()
	{
		if (currentIndex < catList.length - 1)
		{
			currentIndex++;
			catComponents.displayDetails(catList[currentIndex]);
		}
		else
			JOptionPane.showMessageDialog(catSystem, "End of the list", "Alert", JOptionPane.ERROR_MESSAGE);
	}

   /* display previous index (if any)
      display appropriate message (if none) */
	private void previousButtonClicked()
	{
		if (currentIndex > 0)
		{
			currentIndex--;
			catComponents.displayDetails(catList[currentIndex]);
		}
		else
			JOptionPane.showMessageDialog(catSystem, "End of the list", "Alert", JOptionPane.ERROR_MESSAGE);
	}

   // clear the search results
	private void resetButtonClicked()
	{
		currentIndex = 0;
		catList = null;
		catComponents.setVisible(false);
		priceCombo.setSelectedIndex(0);
		weightCombo.setSelectedIndex(0);
	}
   
   // search cat by price and weight
	private void searchButtonClicked()
	{
		// convert weight and price combo box text into a range
		double[] weightRange = CatShopSystem.convertToRange((String)weightCombo.getSelectedItem());
		double[] priceRange = CatShopSystem.convertToRange((String)priceCombo.getSelectedItem());

		if (priceRange[0] >= 0 && weightRange[0] >= 0)
		{
			catList = catSystem.search((int)priceRange[0], (int)priceRange[1], (double)weightRange[0], (double)weightRange[1]);
		}

		if (catList.length > 0)
		{
			currentIndex = 0;
			catComponents.setVisible(true);
			catComponents.displayDetails(catList[0]);

			if (catList.length == 1)
			{
				nextButton.setEnabled(false);
				previousButton.setEnabled(false);
			}
			else
			{
				nextButton.setEnabled(true);
				previousButton.setEnabled(true);
			}

			catSystem.repaint();
		}
		else
			JOptionPane.showMessageDialog(catSystem, "Sorry, no search results found", "Searching failed", JOptionPane.WARNING_MESSAGE);
	}
} // end of class