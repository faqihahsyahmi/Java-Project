import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * SearchByAgePanel to obtain search parameters by using cat age
 *
 * PUBLIC FEATURES:
 * // Constructors
 *    public SearchByAgePanel(CatShopSystem catSys)
 *
 * // Methods
 *    public void actionPerformed(ActionEvent ev)
 */
 @SuppressWarnings("unchecked")
public class SearchByAgePanel extends JPanel implements ActionListener
{
	private final String[] age = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
		"11-15", "16-20", "21-25", "26-30", "31+"};
	private Cat[] catList;
	private CatShopSystem catSystem;
	private int currentIndex = 0;
	private JLabel headingLabel = new JLabel("Search on age");
	private JLabel ageLabel = new JLabel("Cat Age");
	private JButton searchButton = new JButton("Search");
	private JButton resetButton = new JButton("Reset");
	private JButton previousButton = new JButton("Previous");
	private JButton nextButton = new JButton("Next");
	private JComboBox ageCombo = new JComboBox(age);
	private JPanel topPanel = new JPanel();
	private JPanel agePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel searchButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel navigateButtonsPanel = new JPanel();
	private CatDetailsComponents catComponents = new CatDetailsComponents();

	public SearchByAgePanel(CatShopSystem catSys)
	{
		catSystem = catSys; // links to CatShopSystem objects
		setLayout(new BorderLayout());
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

		previousButton.addActionListener(this);
		nextButton.addActionListener(this);
		resetButton.addActionListener(this);
		searchButton.addActionListener(this);

		agePanel.add(ageLabel);
		agePanel.add(ageCombo);
		searchButtonsPanel.add(searchButton);
		searchButtonsPanel.add(resetButton);
		navigateButtonsPanel.add(previousButton);
		navigateButtonsPanel.add(nextButton);
		agePanel.setBorder(new javax.swing.border.EmptyBorder(new Insets(0, 5, 0, 0)));
		searchButtonsPanel.setBorder(new javax.swing.border.EmptyBorder(new Insets(0, 5, 0, 0)));

		headingLabel.setAlignmentX(0.5f);

		topPanel.add(Box.createVerticalStrut(10));
		topPanel.add(headingLabel);
		topPanel.add(Box.createVerticalStrut(10));
		topPanel.add(agePanel);
		topPanel.add(searchButtonsPanel);
		topPanel.add(Box.createVerticalStrut(15));
		catComponents.add(navigateButtonsPanel, "Center");
		catComponents.setVisible(false);

		add(topPanel, "North");
		add(catComponents, "Center");
	}

   //checking the button clicks
	public void actionPerformed(ActionEvent ev)
	{
		if (ev.getSource() == searchButton)
			searchButtonClicked();
		else if (ev.getSource() == previousButton)
			previousButtonClicked();
		else if (ev.getSource() == nextButton)
			nextButtonClicked();
		else if (ev.getSource() == resetButton)
			resetButtonClicked();
	}

   // go to next index (if any)
	private void nextButtonClicked()
	{
		if (currentIndex < catList.length - 1)
		{
			currentIndex++;
			catComponents.displayDetails(catList[currentIndex]);
		}
		else
			JOptionPane.showMessageDialog(catSystem, "End of list", "Alert", JOptionPane.ERROR_MESSAGE);
	}

   // go to prev index (if any)
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

   // reset (clear) the search results
	private void resetButtonClicked()
	{
		currentIndex = 0;
		catList = null;
		catComponents.setVisible(false);
		ageCombo.setSelectedIndex(0);
	}

   //locate search parameters and start searching
	private void searchButtonClicked()
	{
		// convert string range to lower and upper bound
		double[] range = CatShopSystem.convertToRange((String)ageCombo.getSelectedItem());

		if (range[0] >= 0)
		{
			catList = catSystem.search((int)range[0], (int)range[1]);
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
			JOptionPane.showMessageDialog(catSystem, "Sorry, no search results available", "Searching failed", JOptionPane.WARNING_MESSAGE);
	}
} // end of class