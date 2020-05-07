package Views;

/**
 * Program Name: AddNewBookPanel.java
 * Purpose: Generates the Books panel for the application
 * Coder: Sabrina Tessier
 * Date: Jul. 17, 2019
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.Document;

import Utils.ComponentGenerator;

import java.util.ArrayList;

public class AddNewBook extends JFrame
{
	private AddNewBookPanel panel;
	
	public AddNewBookPanel getAddNewBookPanel()
	{
		return panel;
	}
	
	public AddNewBook()
	{
		super("Add New Book");
		this.setSize(600,650);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.panel = new AddNewBookPanel();
		this.add(panel);
	}
	
	public class AddNewBookPanel extends JPanel implements ComponentGenerator
	{
		private JTextField titleField;
		private JTextField authorFNField;
		private JTextField authorLNField;
		private JTextField ISBNField;
		private JTextField editionField;
		private JTextField subjectField;
		private JButton addAnotherAuthorBtn;
		private JButton okBtn;
		private JButton cancelBtn;
		private ArrayList<Author> authorList;
		
		public JTextField getTitleField()
		{
			return titleField;
		}
		public JTextField getAuthorFNField()
		{
			return authorFNField;
		}
		public JTextField getAuthorLNField()
		{
			return authorLNField;
		}
		public JTextField getISBNField()
		{
			return ISBNField;
		}
		public JTextField getEditionField()
		{
			return editionField;
		}
		public JTextField getSubjectField()
		{
			return subjectField;
		}
		public JButton getOkBtn()
		{
			return okBtn;
		}
		public JButton getCancelButton()
		{
			return cancelBtn;
		}
		public ArrayList<Author> getAuthorList()
		{
			return authorList;
		}
		
		public JButton getAddAnotherAuthorButton()
		{
			return addAnotherAuthorBtn;
		}
		
		public AddNewBookPanel()
		{
			this.setBackground(addPanelColor);
			this.setLayout(new BorderLayout());
			JPanel topPanel = new JPanel(new GridLayout(3,1));
			topPanel.setOpaque(false);
			JPanel titlePanel = new JPanel();
			titlePanel.setOpaque(false);
			titlePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, borderColor));
			JLabel titleLabel = new JLabel("Add New Book");
			titleLabel.setFont(headingFont);
			titleLabel.setForeground(headingTextColor);
			titlePanel.add(titleLabel);
			topPanel.add(titlePanel);
			addAnotherAuthorBtn = makeButton("Add Another Author");
			JLabel instructionLabel = new JLabel("To add multiple authors, enter first and last name for one author and then click 'Add Another Author'");
			JPanel pnl = new JPanel();
			pnl.setOpaque(false);
			pnl.add(addAnotherAuthorBtn);
			topPanel.add(instructionLabel);	
			topPanel.add(pnl);		
			this.add(topPanel, BorderLayout.NORTH);

			JPanel secondPanel = new JPanel(new GridLayout(1, 2));
			secondPanel.setOpaque(false);	
			JPanel labelPanel = new JPanel(new GridLayout(7,1,0,20));
			labelPanel.setOpaque(false);

			labelPanel.add(createTextFieldLabel("Title: "));
			labelPanel.add(createTextFieldLabel("Author First Name: "));
			labelPanel.add(createTextFieldLabel("Author Last Name: "));
			labelPanel.add(createTextFieldLabel("ISBN: "));
			labelPanel.add(createTextFieldLabel("Edition: "));
			labelPanel.add(createTextFieldLabel("Subject: "));
			okBtn = makeButton("Ok");
			labelPanel.add(okBtn);
			secondPanel.add(labelPanel);
			
			JPanel fieldPanel = new JPanel(new GridLayout(7, 1, 0, 20));
			fieldPanel.setOpaque(false);
			titleField = new JTextField();
			fieldPanel.add(titleField);
			
			authorFNField = new JTextField();
			fieldPanel.add(authorFNField);		
			authorLNField = new JTextField();
			fieldPanel.add(authorLNField);	
			authorList = new ArrayList<Author>();
			
			ISBNField = new JTextField();
			fieldPanel.add(ISBNField);
			
			editionField = new JTextField();
			fieldPanel.add(editionField);
			
			subjectField = new JTextField();
			fieldPanel.add(subjectField);
			
			cancelBtn = makeButton("Cancel");
			fieldPanel.add(cancelBtn);
			secondPanel.add(fieldPanel);
			
			this.add(secondPanel, BorderLayout.CENTER);	
			
		}
		


 }
}