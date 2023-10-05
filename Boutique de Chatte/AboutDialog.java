//AboutDialog
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * PUBLIC FEATURES:
 * // Constructors
 *    public AboutDialog(JFrame parent, String title, boolean modal)
 *
 * // Methods
 *    public void actionPerformed(ActionEvent ev)
 *    public void closing()
 *    public void showAbout()
 *    public int addNewCat(Cat c)
 *    public void closing()
 */
 
public class AboutDialog extends JDialog implements ActionListener
{
	private JButton okButton = new JButton("OK"); //'ok' button
	private JPanel buttonPanel = new JPanel();
	private WindowCloser closer = new WindowCloser();

	/*
	 * a parent JFrame to place the dialog on top
	 * title of the about dialog
	 * modal means focus cannot be taken away from the dialog, non-modal means it can.
	 */
	public AboutDialog(JFrame parent, String title, boolean modal)
	{
		super(parent, title, modal);
		Container c = getContentPane();

		setSize(480, 130);
		setLocationRelativeTo(parent);
		addWindowListener(closer);
		c.setLayout(new GridLayout(3, 1));
		setTitle(title);
		buttonPanel.add(okButton);
		c.add(new JLabel("Cat Sales System by Famera Chatte.Co", JLabel.CENTER));
		c.add(new JLabel("OOPs, Project", JLabel.CENTER));
		c.add(buttonPanel);
		okButton.addActionListener(this);
	}

   // if clicked 'ok' button - call the closing method
	public void actionPerformed(ActionEvent ev)
	{
		if (ev.getSource() == okButton)
			closing();
	}

	public void closing()
	{
		setVisible(false);
	}

   //method to show ab dialog
	public void showAbout()
	{
		// center the dialog
		setLocationRelativeTo(getParent());
		setVisible(true);
	}

	class WindowCloser extends WindowAdapter
	{
      //call the system's main closing ev
		public void windowClosing(WindowEvent ev)
		{
			closing();
		}
	}
} // end of class