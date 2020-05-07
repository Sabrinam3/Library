package Views;

/**
 * Program Name: AddNewBorrower.java
 * Purpose: Generates the Books panel for the application
 * Coder: Sabrina Tessier
 * Date: Jul. 17, 2019
 */
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.LineBorder;

import Utils.ComponentGenerator;

public class AddNewBorrower extends JFrame
{
	private AddNewBorrowerPanel panel;
	
	public AddNewBorrowerPanel getAddNewBorrowerPanel()
	{
		return panel;
	}
	
	public AddNewBorrower(String title)
	{
		super(title);
		this.setSize(600,400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.panel = new AddNewBorrowerPanel();
		this.add(panel);
	}
	
	public class AddNewBorrowerPanel extends JPanel implements ComponentGenerator
	{
		private JTextField firstNameField;
		private JTextField lastNameField;
		private JTextField emailField;
		private JLabel titleLabel;
		private JButton okBtn;
		private JButton cancelBtn;
		
		public JTextField getFirstNameField()
		{
			return firstNameField;
		}


		public JTextField getLastNameField()
		{
			return lastNameField;
		}


		public JTextField getEmailField()
		{
			return emailField;
		}


		public JButton getOkBtn()
		{
			return okBtn;
		}


		public JButton getCancelButton()
		{
			return cancelBtn;
		}
		
		public JLabel getTitleLabel()
		{
			return titleLabel;
		}
		
		public AddNewBorrowerPanel()
		{
			this.setBackground(addPanelColor);
			this.setLayout(new BorderLayout());
			JPanel topPanel = new JPanel(new GridLayout(2,1));
			topPanel.setOpaque(false);
			JPanel titlePanel = new JPanel();
			titlePanel.setOpaque(false);
			titlePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, borderColor));
			titleLabel = new JLabel("Add New Borrower");
			titleLabel.setFont(headingFont);
			titleLabel.setForeground(headingTextColor);
			titlePanel.add(titleLabel);
			topPanel.add(titlePanel);
			this.add(topPanel, BorderLayout.NORTH);

			JPanel secondPanel = new JPanel(new GridLayout(1, 2));
			secondPanel.setOpaque(false);	
			JPanel labelPanel = new JPanel(new GridLayout(4,1,0,20));
			labelPanel.setOpaque(false);

			labelPanel.add(createTextFieldLabel("First Name: "));
			labelPanel.add(createTextFieldLabel("Last Name: "));
			labelPanel.add(createTextFieldLabel("Email: "));
			okBtn = makeButton("Ok");
			labelPanel.add(okBtn);
			secondPanel.add(labelPanel);
			
			JPanel fieldPanel = new JPanel(new GridLayout(4, 1, 0, 20));
			fieldPanel.setOpaque(false);
			firstNameField = new JTextField();
			fieldPanel.add(firstNameField);
			
			lastNameField = new JTextField();
			fieldPanel.add(lastNameField);
			
			emailField = new JTextField();
			fieldPanel.add(emailField);
			
			cancelBtn = makeButton("Cancel");
			fieldPanel.add(cancelBtn);
			secondPanel.add(fieldPanel);
			
			this.add(secondPanel, BorderLayout.CENTER);	
		}
 }
}