import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * CatDetailsComponents contais the group of text fields representing the cats information (visual)
 * @
 *
 * PUBLIC FEATURES:
 * // Constructors
 *    public CatDetailsComponents()
 *
 * // Methods
 *    public void clearTextFields()
 *    public void componentHidden(ComponentEvent ev)
 *    public void componentMoved(ComponentEvent ev)
 *    public void componentResized(ComponentEvent ev)
 *    public void componentShown(ComponentEvent ev)
 *    public void displayDetails(Cat c)
 *    public JPanel getDetailsPanel()
 *    public String getOriginsText()
 *    public String getYearText()
 *    public String getKgText()
 *    public String getBreedText()
 *    public String getPriceText()
 *    public String getInfoText()
 *    public void setFocusOriginsTextField()
 */
public class CatDetailsComponents extends JPanel implements ComponentListener
{
	private JLabel originsLabel = new JLabel("Origins");
	private JLabel yearLabel = new JLabel("Year");
	private JLabel breedLabel = new JLabel("Breed");
	private JLabel priceLabel = new JLabel("Price");
	private JLabel kgLabel = new JLabel("Kg");
	private JLabel infoLabel = new JLabel("Extra Information");
   //private JLabel imageLabel = new JLabel("Chatte Photo");
	public JTextField originsTextField = new JTextField();
	public JTextField yearTextField = new JTextField();
	public JTextField breedTextField = new JTextField();
	public JTextField priceTextField = new JTextField();
	public JTextField kgTextField = new JTextField();
	public JTextArea infoTextArea = new JTextArea(4, 0);
   //public JTextPane imageIcon = new JTextPane();//Image
   //JTextPane Pane = new JTextPane();

	private final int divFactor = 27;

   // set new CatDetailComponents object
	public CatDetailsComponents()
	{
		Insets currentInsets;
		GridBagConstraints gridBagConstraints;
		setLayout(new BorderLayout(0, 20));
		JPanel compPanel = new JPanel(new GridBagLayout());
		String currentFont = originsLabel.getFont().getName();
		currentInsets =  new Insets(0, 10, 0, 30);

		 originsLabel.setFont(new Font(currentFont, Font.BOLD, 12));
       gridBagConstraints = new GridBagConstraints();
       gridBagConstraints.anchor = GridBagConstraints.WEST;
       gridBagConstraints.insets = currentInsets;
       compPanel.add(originsLabel, gridBagConstraints);

       yearLabel.setFont(new Font(currentFont, Font.BOLD, 12));
       gridBagConstraints = new GridBagConstraints();
       gridBagConstraints.gridx = 0;
       gridBagConstraints.gridy = 1;
       gridBagConstraints.anchor = GridBagConstraints.WEST;
       gridBagConstraints.insets = currentInsets;
       compPanel.add(yearLabel, gridBagConstraints);

       breedLabel.setFont(new Font(currentFont, Font.BOLD, 12));
       gridBagConstraints = new GridBagConstraints();
       gridBagConstraints.gridx = 0;
       gridBagConstraints.gridy = 2;
       gridBagConstraints.anchor = GridBagConstraints.WEST;
       gridBagConstraints.insets = currentInsets;
       compPanel.add(breedLabel, gridBagConstraints);

       priceLabel.setFont(new Font(currentFont, Font.BOLD, 12));
       gridBagConstraints = new GridBagConstraints();
       gridBagConstraints.gridx = 0;
       gridBagConstraints.gridy = 3;
       gridBagConstraints.anchor = GridBagConstraints.WEST;
       gridBagConstraints.insets = currentInsets;
       compPanel.add(priceLabel, gridBagConstraints);

       kgLabel.setFont(new Font(currentFont, Font.BOLD, 12));
       gridBagConstraints = new GridBagConstraints();
       gridBagConstraints.gridx = 0;
       gridBagConstraints.gridy = 4;
       gridBagConstraints.anchor = GridBagConstraints.WEST;
       gridBagConstraints.insets = currentInsets;
       compPanel.add(kgLabel, gridBagConstraints);

       infoLabel.setFont(new Font(currentFont, Font.BOLD, 12));
       gridBagConstraints = new GridBagConstraints();
       gridBagConstraints.gridx = 0;
       gridBagConstraints.gridy = 5;
       gridBagConstraints.anchor = GridBagConstraints.WEST;
       gridBagConstraints.insets = currentInsets;
       compPanel.add(infoLabel, gridBagConstraints);

       gridBagConstraints = new GridBagConstraints();
       gridBagConstraints.gridwidth = GridBagConstraints.RELATIVE;
	    gridBagConstraints.anchor = GridBagConstraints.WEST;
       gridBagConstraints.weightx = 1.0;
       compPanel.add(originsTextField, gridBagConstraints);

       gridBagConstraints = new GridBagConstraints();
       gridBagConstraints.gridx = 1;
       gridBagConstraints.gridy = 1;
       gridBagConstraints.gridwidth = GridBagConstraints.RELATIVE;
		 gridBagConstraints.anchor = GridBagConstraints.WEST;
       gridBagConstraints.weightx = 1.0;
       compPanel.add(yearTextField, gridBagConstraints);

       gridBagConstraints = new GridBagConstraints();
       gridBagConstraints.gridx = 1;
       gridBagConstraints.gridy = 2;
       gridBagConstraints.gridwidth = GridBagConstraints.RELATIVE;
		 gridBagConstraints.anchor = GridBagConstraints.WEST;
       gridBagConstraints.weightx = 1.0;
       compPanel.add(breedTextField, gridBagConstraints);

       gridBagConstraints = new GridBagConstraints();
       gridBagConstraints.gridx = 1;
       gridBagConstraints.gridy = 3;
       gridBagConstraints.gridwidth = GridBagConstraints.RELATIVE;
		 gridBagConstraints.anchor = GridBagConstraints.WEST;
       gridBagConstraints.weightx = 1.0;
       compPanel.add(priceTextField, gridBagConstraints);

       gridBagConstraints = new GridBagConstraints();
       gridBagConstraints.gridx = 1;
       gridBagConstraints.gridy = 4;
       gridBagConstraints.gridwidth = GridBagConstraints.RELATIVE;
		 gridBagConstraints.anchor = gridBagConstraints.WEST;
       gridBagConstraints.weightx = 1.0;
       compPanel.add(kgTextField, gridBagConstraints);
       
       
       infoTextArea.setLineWrap(true);
		 currentInsets = new Insets(2, 20, 0, 20);
       gridBagConstraints = new GridBagConstraints();
       gridBagConstraints.gridx = 1;
       gridBagConstraints.gridy = 5;
       gridBagConstraints.anchor = gridBagConstraints.WEST;
		 gridBagConstraints.weightx = 1.0;
       compPanel.add(new JScrollPane(infoTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), gridBagConstraints);

		// this listens for resize events
		addComponentListener(this);
        add(compPanel, "North");
	}

   // empty-kan all text fields
	public void clearTextFields()
	{
		originsTextField.setText("");
		yearTextField.setText("");
		breedTextField.setText("");
		priceTextField.setText("");
		kgTextField.setText("");
		infoTextArea.setText("");    
	}

	public void componentHidden(ComponentEvent ev) {}

	public void componentMoved(ComponentEvent ev) {}

   //change text field's size when details panel changes size
	public void componentResized(ComponentEvent ev)
	{
		if (ev.getSource() == this)
		{
			int width = getWidth();

			if (width >= 0)
			{
            //size the text field manually
				originsTextField.setColumns(width / divFactor);
				yearTextField.setColumns(width / divFactor);
				breedTextField.setColumns(width / divFactor);
				priceTextField.setColumns(width / divFactor);
				kgTextField.setColumns(width / divFactor);
				infoTextArea.setColumns((width / divFactor) + 3); // larger text field
			}
		}
	}//end of componentResized

	public void componentShown(ComponentEvent ev){}

   // display cat details at text box
	public void displayDetails(Cat c)
	{
		originsTextField.setText(c.getOrigins());
		yearTextField.setText(Integer.toString(c.getYear()));
		breedTextField.setText(c.getBreed());
		priceTextField.setText(Integer.toString(c.getPrice()));
		kgTextField.setText(Double.toString(c.getWeight()));
		infoTextArea.setText(c.getInformation());
	}
   
   //get method
	public String getInfoText()
	{
		return infoTextArea.getText();
	}

	public String getKgText()
	{
		return kgTextField.getText();
	}

	public String getOriginsText()
	{
		return originsTextField.getText();
	}

	public String getBreedText()
	{
		return breedTextField.getText();
	}

	public String getPriceText()
	{
		return priceTextField.getText();
	}

	public String getYearText()
	{
		return yearTextField.getText();
	}

   // setkan focus 
	public void setFocusOriginsTextField()
	{
		originsTextField.grabFocus();
	}         
}
//end class
