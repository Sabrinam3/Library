package Views;

/**
 * Program Name: ReportsPanel.java
 * Purpose: generates the Reports panel for the application
 * Coder: Sabrina Tessier
 * Date: Jul. 17, 2019
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import Utils.ComponentGenerator;
public class ReportsPanel extends JPanel implements ComponentGenerator
{
	private ButtonGroup reportGroup;
	private JRadioButton allBooksBtn;
	private JRadioButton booksOnLoanBtn;
	private JRadioButton booksBySubjectBox;
	private JRadioButton booksByAuthorBtn;
	private JRadioButton allBorrowersBtn;
	
	private JButton generateBtn;
	
	//Filtering options
	private JLabel filterLabel;
	private JTextField filterOption;
	
	public JRadioButton getallBooksBtn()
	{
		return allBooksBtn;
	}

	public JRadioButton getbooksOnLoanBtn()
	{
		return booksOnLoanBtn;
	}

	public JRadioButton getBooksBySubjectBtn()
	{
		return booksBySubjectBox;
	}

	public JRadioButton getBooksByAuthorBtn()
	{
		return booksByAuthorBtn;
	}

	public JRadioButton getallBorrowersBtn()
	{
		return allBorrowersBtn;
	}

	public JButton getGenerateBtn()
	{
		return generateBtn;
	}

	public JTextField getFilterOption()
	{
		return filterOption;
	}
	public JLabel getFilterLabel()
	{
		return filterLabel;
	}
	
	public ButtonGroup getreportGroup()
	{
		return reportGroup;
	}
	public ReportsPanel()
	{
		this.setLayout(new GridLayout(4, 1, 0, 0));
		JPanel topPanel = new JPanel(new GridLayout(2,1));
		topPanel.setOpaque(false);
		JPanel titlePanel = new JPanel();
		titlePanel.setOpaque(false);
		titlePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, borderColor));
		JLabel titleLabel = new JLabel("Reports");
		titleLabel.setFont(headingFont);
		titleLabel.setForeground(headingTextColor);
		titlePanel.add(titleLabel);
		topPanel.add(titlePanel);
		
		JLabel lbl = new JLabel("Select Report Type:", JLabel.CENTER);
		lbl.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lbl.setForeground(Color.WHITE);
		topPanel.add(lbl);
		this.add(topPanel);
		
		JPanel checkBoxPanel = new JPanel();
		reportGroup = new ButtonGroup();
		checkBoxPanel.setOpaque(false);
		allBooksBtn = new JRadioButton("All Books In The Library", false);
		allBooksBtn.setActionCommand("All Books In The Library");
		allBooksBtn.setFont(textFieldFont);
		allBooksBtn.setOpaque(false);
		reportGroup.add(allBooksBtn);
		checkBoxPanel.add(allBooksBtn);
		booksOnLoanBtn = new JRadioButton("Books On Loan", false);
		booksOnLoanBtn.setActionCommand("Books On Loan");
		booksOnLoanBtn.setFont(textFieldFont);
		booksOnLoanBtn.setOpaque(false);
		reportGroup.add(booksOnLoanBtn);
		checkBoxPanel.add(booksOnLoanBtn);
		booksBySubjectBox = new JRadioButton("Books By Subject", false);
		booksBySubjectBox.setActionCommand("Books By Subject");
		booksBySubjectBox.setFont(textFieldFont);
		booksBySubjectBox.setOpaque(false);
		reportGroup.add(booksBySubjectBox);
		checkBoxPanel.add(booksBySubjectBox);
		booksByAuthorBtn = new JRadioButton("Books By Author", false);
		booksByAuthorBtn.setActionCommand("Books By Author");
		booksByAuthorBtn.setFont(textFieldFont);
		booksByAuthorBtn.setOpaque(false);
		reportGroup.add(booksByAuthorBtn);
		checkBoxPanel.add(booksByAuthorBtn);
		allBorrowersBtn = new JRadioButton("All Borrowers", false);
		allBorrowersBtn.setActionCommand("All Borrowers");
		allBorrowersBtn.setFont(textFieldFont);
		allBorrowersBtn.setOpaque(false);
		reportGroup.add(allBorrowersBtn);
		checkBoxPanel.add(allBorrowersBtn);
		this.add(checkBoxPanel);
		
		JPanel btnFilterPanel = new JPanel();
		btnFilterPanel.setOpaque(false);
		generateBtn = makeButton("Generate Report");
		generateBtn.setPreferredSize(new Dimension(200, 60));
		
		filterLabel = new JLabel(); //gets populated based on which report user is generating
		filterLabel.setVisible(false);//invisible until user selects one of the report types that requires filtering
		filterOption = new JTextField();
		filterOption.setVisible(false); //invisible until user selects one of the report types that requires filtering
		filterOption.setPreferredSize(new Dimension(200, 30));
		btnFilterPanel.add(filterLabel);
		btnFilterPanel.add(filterOption);
		btnFilterPanel.add(generateBtn);
		this.add(btnFilterPanel);

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