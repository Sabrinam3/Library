package Views;

/**
 * Program Name: LoansPanel.java
 * Purpose: Generates the Loans panel for the application
 * Coder: Sabrina Tessier
 * Date: Jul. 17, 2019
 */
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.LineBorder;

import Utils.ComponentGenerator;
public class LoansPanel extends JPanel implements ComponentGenerator
{
	private JComboBox<String> bookDropdown;
	private JComboBox<String> borrowerDropdown;
	private JTextField commentField;
	private JTextField dateOutField; //should not be editable
	private JComboBox<String> loanPeriodDropdown;
	private JTextField dateDueField; //should not be editable
	private JButton checkOutBtn;
	
	public JComboBox<String> getBookDropdown()
	{
		return bookDropdown;
	}


	public JComboBox<String> getBorrowerDropdown()
	{
		return borrowerDropdown;
	}


	public JTextField getCommentField()
	{
		return commentField;
	}


	public JTextField getDateOutField()
	{
		return dateOutField;
	}


	public JComboBox<String> getLoanPeriodDropdown()
	{
		return loanPeriodDropdown;
	}


	public JTextField getDateDueField()
	{
		return dateDueField;
	}
	
	public JButton getCheckOutButton()
	{
		return checkOutBtn;
	}
	
public LoansPanel()
{
	this.setLayout(new BorderLayout());
	JPanel topPanel = new JPanel(new GridLayout(2,1));
	topPanel.setOpaque(false);
	JPanel titlePanel = new JPanel();
	titlePanel.setOpaque(false);
	titlePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, borderColor));
	JLabel titleLabel = new JLabel("Check Out Book");
	titleLabel.setFont(headingFont);
	titleLabel.setForeground(headingTextColor);
	titlePanel.add(titleLabel);
	topPanel.add(titlePanel);
	this.add(topPanel, BorderLayout.NORTH);

	JPanel secondPanel = new JPanel(new GridLayout(1, 4));
	secondPanel.setOpaque(false);	
	//JPanel imagePanel = new JPanel();
	//imagePanel.setOpaque(false);
	//ImageIcon bookImage = new ImageIcon(getClass().getResource("book.png"));
	//JLabel bookImageLabel = new JLabel(bookImage);
	//secondPanel.add(bookImageLabel);
	
	JPanel labelPanel = new JPanel(new GridLayout(8,1,0,20));
	labelPanel.setOpaque(false);

	
		
	labelPanel.add(createTextFieldLabel("Book: "));
	labelPanel.add(createTextFieldLabel("Borrower: "));
	labelPanel.add(createTextFieldLabel("Comment: "));
	labelPanel.add(createTextFieldLabel("Date Out: "));
	labelPanel.add(createTextFieldLabel("Loan Period: "));
	labelPanel.add(createTextFieldLabel("Date Due: "));
	secondPanel.add(labelPanel);
	
	JPanel fieldPanel = new JPanel(new GridLayout(8, 1, 0, 20));
	fieldPanel.setOpaque(false);

	bookDropdown = new JComboBox<String>();
	bookDropdown.setPreferredSize(new Dimension(375,30));
	fieldPanel.add(bookDropdown);
	

	borrowerDropdown = new JComboBox<String>();
	borrowerDropdown.setPreferredSize(new Dimension(375,30));
	fieldPanel.add(borrowerDropdown);

	commentField = new JTextField(); //max 13
	commentField.setPreferredSize(new Dimension(375,30));
	fieldPanel.add(commentField);

	dateOutField = new JTextField();
	dateOutField.setEditable(false);
	dateOutField.setPreferredSize(new Dimension(375,30));
	dateOutField.setText(LocalDate.now().toString());
	fieldPanel.add(dateOutField);
	
	loanPeriodDropdown = new JComboBox<String>();
	loanPeriodDropdown.setPreferredSize(new Dimension(375,30));
	fieldPanel.add(loanPeriodDropdown);
	

	dateDueField = new JTextField();
	dateDueField.setPreferredSize(new Dimension(375,30));
	fieldPanel.add(dateDueField); //calculate based on loan period
	
	checkOutBtn = makeButton("Check Out");
	fieldPanel.add(checkOutBtn);
	
	secondPanel.add(fieldPanel);
	secondPanel.add(Box.createRigidArea(new Dimension(100, 800)));
	this.add(secondPanel, BorderLayout.CENTER);
	
}
	 @Override
   protected void paintComponent(Graphics g) {
       super.paintComponent(g);
       Graphics2D g2d = (Graphics2D) g;
       g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
       int w = getWidth();
       int h = getHeight();
       GradientPaint gp = new GradientPaint(0, 0, gradient1, 0, h, gradient2);
       g2d.setPaint(gp);
       g2d.fillRect(0, 0, w, h);
   }
}
//end-class