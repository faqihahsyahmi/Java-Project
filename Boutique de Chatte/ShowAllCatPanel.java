import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * ShowAllCatPanel used for display information about all cats
 *
 * PUBLIC FEATURES:
 * // Constructors
 *    public ShowAllCatPanel(CatShopSystem catSys)
 *
 * // Methods
 *    public void actionPerformed(ActionEvent ev)
 *    public void catUpdated(CatUpdateEvent ev)
 *    public void stateChanged(ChangeEvent ev)
 */
public class ShowAllCatPanel extends JPanel implements ActionListener, ChangeListener
{
	private CatShopSystem catSystem;
	private Cat[] catList;
	private int currentIndex = 0;
	private JLabel headingLabel = new JLabel("Show all Cat's Details");
	private JButton previousButton = new JButton("Previous");
	private JButton nextButton = new JButton("Next");
	private JPanel buttonPanel = new JPanel();
	private CatDetailsComponents catComponents = new CatDetailsComponents();
	private boolean catUpdated = false;

	public ShowAllCatPanel(CatShopSystem catSys)
	{
		catSystem = catSys;
		catList = catSystem.getAllCat();

		if (catList.length > 0)
			catComponents.displayDetails(catList[0]);

		catSys.addCatUpdateListener(this);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		previousButton.addActionListener(this);
		nextButton.addActionListener(this);
		headingLabel.setAlignmentX(0.5f);

		buttonPanel.add(previousButton);
		buttonPanel.add(nextButton);

		add(Box.createVerticalStrut(10));
		add(headingLabel);
		add(Box.createVerticalStrut(15));
		catComponents.add(buttonPanel, "Center");
		add(catComponents);

		catList = catSystem.getAllCat();
	}

   //checking for the button clicks
	public void actionPerformed(ActionEvent ev)
	{
		if (ev.getSource() == previousButton)
			previousButtonClicked();
		else if (ev.getSource() == nextButton)
			nextButtonClicked();
	}

   //invoked the method when new cat has been added
	public void catUpdated(CatUpdateEvent ev)
	{
		if (ev.getSource() == catSystem)
		{
			catUpdated = true;
		}
	}

   /* Update the cat list with the latest data
            e.g maybe got new data*/
	public void stateChanged(ChangeEvent ev)
	{
		// source is the JTabbedPane
		if (ev.getSource() instanceof JTabbedPane)
		{
			JTabbedPane tab = (JTabbedPane)ev.getSource();
			// selected tab index is ShowAllCatPanel
			if (tab.getSelectedIndex() == 2)
			{
				// cat have been updated
				if (catUpdated)
				{
					catList = catSystem.getAllCat();
					if (!(catList == null))
						catComponents.displayDetails(catList[currentIndex]);
					// no updating unless got new data inserted
					catUpdated = false;
				}
			}
		}
	}

   /* display next item (if any)
      display an appropriate message (if none) */
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

   // display prev index if the prev button is clicked or an appropriate message (if none)
	private void previousButtonClicked()
	{
		if (currentIndex > 0)
		{
			currentIndex--;
			catComponents.displayDetails(catList[currentIndex]);
		}
		else
			JOptionPane.showMessageDialog(catSystem, "End of List", "Alert", JOptionPane.ERROR_MESSAGE);
	}
} // end of class