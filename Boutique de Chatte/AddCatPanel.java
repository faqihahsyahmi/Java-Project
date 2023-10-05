import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * AddCatPanel used for adding cat to the CatShopSystem
 * 
 * PUBLIC FEATURES:
 * // Constructors
 *    public AddCatPanel(CatShopSystem catSys)
 *
 * // Methods
 *    public void actionPerformed(ActionEvent ev)
 */
public class AddCatPanel extends JPanel implements ActionListener
{
	private CatShopSystem catSystem;
	private JLabel headingLabel = new JLabel("ADD CAT");
	private JButton resetButton = new JButton("Reset");
	private JButton saveButton = new JButton("Save");
	private JPanel buttonPanel = new JPanel();
	private CatDetailsComponents catComponents = new CatDetailsComponents();

	public AddCatPanel(CatShopSystem catSys)
	{
		catSystem = catSys; //link to CatShopSystem obj

		resetButton.addActionListener(this);
		saveButton.addActionListener(this);
		headingLabel.setAlignmentX(0.5f);

		buttonPanel.add(resetButton);
		buttonPanel.add(saveButton);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(Box.createVerticalStrut(10));
		add(headingLabel);
		add(Box.createVerticalStrut(15));
		catComponents.add(buttonPanel, "Center");
		add(catComponents);
	}

   // get the pressed button using actionPerformed
	public void actionPerformed(ActionEvent ev)
	{
		if (ev.getSource() == resetButton)
			resetButtonClicked();
		else if (ev.getSource() == saveButton)
			saveButtonClicked();
	}

	private void resetButtonClicked()
	{
		catComponents.clearTextFields();
	}

	private void saveButtonClicked()
	{
		String origins = "";
		String breed = "";
		String info = "";
		double weight = 0;
		int price = 0;
		int year = 0;
		boolean valid = false;
      
      //try code and catch exception
		try
		{
			//retrieve all values from the text field and convert into appropriate format 
         origins = catComponents.getOriginsText().trim();
			breed = catComponents.getBreedText().trim();
			info = catComponents.getInfoText().trim();
			weight = Double.parseDouble(catComponents.getKgText().trim());
			price = Integer.parseInt(catComponents.getPriceText().trim());
			year = Integer.parseInt(catComponents.getYearText().trim());

			// validation process
			if (validateString(origins))
			{
				if (year >= 1000 && year <= 9999)
				{
					if (validateString(breed))
					{
///////////////////////////////////////////////////////////FUNC BLEH ADD DATA (SUCCESS)//////////////////////////////////////
                  if (validateWeight(catComponents.getKgText().trim()))
						{
							valid = true;
						}
						else
							JOptionPane.showMessageDialog(catSystem, "An error has occured due to incorrect \"Kg Cat\" text field data.\nThis text field must contain number with one decimal place only (x.x).", "Invalid field", JOptionPane.ERROR_MESSAGE);
					
               }
					else
						JOptionPane.showMessageDialog(catSystem, "An error has occured due to incorrect \"Breed\" text field data.\nThis text field must contain any string data type of at least two non-spaced characters.", "Invalid field", JOptionPane.ERROR_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(catSystem, "An error has occured due to incorrect \"Year\" text field data.\nThis text field must be in the form, YYYY (e.g 2009).", "Invalid field", JOptionPane.ERROR_MESSAGE);
			}//end if 
			else
				JOptionPane.showMessageDialog(catSystem, "An error has occured due to incorrect \"Origins\" text field data.\nThis text field must contain any string data type of at least two non-spaced characters.", "Invalid field", JOptionPane.ERROR_MESSAGE);

		}
      
		//Throw NumberFormatException if the text fields have invalid data,
		catch (NumberFormatException exp)
		{
			JOptionPane.showMessageDialog(catSystem, "An unknown error has occured. Ensure the fields meet the following requirements:\n" +
			"The \"Year\" field must contain four(4) numeric digits only\nThe \"Price\" field must contain a valid integer with no decimal places\nThe \"Weight (in kg)\" field must contain a number which can have a maximum of one decimal place", "Invalid field", JOptionPane.ERROR_MESSAGE);
		}

		if (valid)
		{
			// create cat object 
			Cat myCat = new Cat(origins, breed, info);
			myCat.setWeight(weight);
			myCat.setPrice(price);
			myCat.setYear(year);

			// to add the new cat 
			int result = catSystem.addNewCat(myCat);

			// if the cat was added successfully - display an appropiate message
			if (result == CatCollection.NO_ERROR)
			{
				catSystem.setCatUpdated();
				JOptionPane.showMessageDialog(catSystem, "Record successfully added.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
				resetButtonClicked();
				catComponents.setFocusOriginsTextField();
			}
			// Origins limi reached
			else if (result == CatCollection.CAT_MAXIMUM_REACHED)
				JOptionPane.showMessageDialog(catSystem, "The maximum amount of cat for that origins has been reached.\n Data from this origins cannot be added any further.", "Problem adding car", JOptionPane.WARNING_MESSAGE);
			// system has reached the maximum number of origins
			else if (result == CatCollection.ORIGINS_MAXIMUM_REACHED)
				JOptionPane.showMessageDialog(catSystem, "The maximum amount of origins in the cat system has been reached.\nData cannot be added any further.", "Problem adding car", JOptionPane.WARNING_MESSAGE);
		}
	}

   //checking the argument
	private boolean validateString(String arg)
	{
		boolean valid = false;
		String[] splitted = arg.split(" "); // splits argument around spaces and creates an array

		for (int i = 0; i < splitted.length; i++)
		{
			// checks if the number of characters between a space is greater than 2
			valid = (splitted[i].length() > 2);
			if (valid)
				break;
		}

		return valid;
	}

   /* check the argument 
         * valid - contains a decimal value (e.g 2.9)
         * invalid - contains not one decimal places (e.g 2.87) */
   private boolean validateWeight(String kilo)
	{
		boolean valid = false;
		String rem;
		StringTokenizer tokens = new StringTokenizer(kilo, "."); // looking for decimal point

		tokens.nextToken();

		if (tokens.hasMoreTokens()) // decimal point present
		{
			// get string representation of all numbers (after the decimal point)
			rem = tokens.nextToken();
			// only one number after the decimal point - valid
			if (rem.length() == 1)
				valid = true;
			else
			{
				// check other types of user input (e.g 5.00)
				if ((Integer.parseInt(rem)) % (Math.pow(10, rem.length() - 1)) == 0)
					valid = true;
				else
					valid=false;
			}
		}
		else // no decimal places
			valid = true;

		return valid;
	}
   
} // end of class