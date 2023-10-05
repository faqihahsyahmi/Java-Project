import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JComboBox;
public class SearchByOthersTrial extends JPanel implements ActionListener {

// Here I will create JComboBoxes
JComboBox<String> breed = new JComboBox<String>();//pizza to breed
JComboBox<String> price = new JComboBox<String>();//topping to price
Font headlineFont = new Font("Arial", Font.BOLD,36);
JLabel ComboTrial = new JLabel("Combo Trial");
JLabel Breed = new JLabel("Breed List ");//SizeList to Breed
JLabel Price = new JLabel("Price List ");//Topping to Price

public SearchByOthersTrial() 
{
super("Combo Trial");
// sets the layout of the form
setLayout(new FlowLayout());
//setLayout(new BorderLayout());
//setLayout(new CardLayout());
// adds items to pizza
breed.addItem("Mainecoon");
breed.addItem("British Shorthair");
breed.addItem("British Longhair");
breed.addItem("Sphynx");
breed.addItem("Scottish Fold");
breed.addItem("Munchkin");
breed.addItem("Bengal");

// adds items to toping list
price.addItem("300-1000");
price.addItem("1001-2000");
price.addItem("2001-3000");
price.addItem("3001-4000");
price.addItem("4001-5000");
price.addItem("5001-10000");
price.addItem("10001-15000");
// add labels
add(ComboTrial);
//This will set the fon't of the Premiere Pizza
ComboTrial.setFont(headlineFont);
add(Breed);
add(breed);
add(Price);
add(price);
//add(totalPrice);
// Closes the program upon exit
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// Finally in the constructor I registered a class as a
// listener for events generated by each of the three JComboBoxes
breed.setSelectedIndex(0);
price.setSelectedIndex(0);
//totalPrice.setEditable(false);
breed.addActionListener(this);
price.addActionListener(this);
// prices.addItemListener(this);
}
// Begin the itemStateChanged() or actionPerformed() method that executes
// when the user selects
// one of the elements
// in the JComboBox. Use the appropriate
// method to determine which specific member is the source of the current
// ItemEvent and whether the event was generated.

/*public void actionPerformed(ActionEvent e) 
{
   int breedOfSelected = (breed).getSelectedIndex();
   int pricePositionSelected = price.getSelectedIndex();
   // Switch statement for positionOfSelected
   switch (breedOfSelected) 
   {
      case 0:
         TotalPrice = 0;
         totalPrice.setText("The Price is $" + TotalPrice);
         break;
      case 1:
         TotalPrice = 7;
         totalPrice.setText("The Price is $" + TotalPrice);
         break;
      case 2:
         TotalPrice = 9;
         totalPrice.setText("The Price is $" + TotalPrice);
         break;
      case 3:
         TotalPrice = 11;
         totalPrice.setText("The Price is $" + TotalPrice);
         break;
      case 4:
         TotalPrice = 14;
         totalPrice.setText("The Price is $" + TotalPrice);
         break;
      default:
         totalPrice.setText("The Price is $" + TotalPrice);
         break;
   }
   
// If statement that adds a dollar to the total price for a topping
if (pricePositionSelected == 2 || pricePositionSelected == 3 || pricePositionSelected == 4) {
   
totalPrice.setText("The Price is $" + TotalPrice);
}
else
totalPrice.setText("The Price is $" + TotalPrice);
}*/
// Main Method
public static void main(String[] args) {
SearchByOthersTrial aFrame = new SearchByOthersTrial();
final int WIDTH = 300;
final int HEIGHT = 200;
aFrame.setSize(WIDTH, HEIGHT);
aFrame.setVisible(true);
}
}