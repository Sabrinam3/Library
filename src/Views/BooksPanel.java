package Views;

/**
 * Program Name: BooksPanel.java
 * Purpose: Generates the Books panel for the application
 * Coder: Sabrina Tessier
 * Date: Jul. 17, 2019
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import Utils.ComponentGenerator;
public class BooksPanel extends JPanel implements ComponentGenerator
{
	private JButton addNewBtn;
	private JButton deleteBtn;
	private JList<String> bookList;
	private JTable bookTable;
	
	public JList<String> getBookList()
	{
		return bookList;
	}
	
	public JButton getAddNewBtn()
	{
		return addNewBtn;
	}
	public JButton getDeleteBtn()
	{
		return deleteBtn;
	}
	
	public JTable getBookTable()
	{
		return bookTable;
	}
	
	public BooksPanel()
	{
		this.setLayout(new GridLayout(3, 1, 0, 0));
		JPanel topPanel = new JPanel(new GridLayout(2,1));
		topPanel.setOpaque(false);
		JPanel titlePanel = new JPanel();
		titlePanel.setOpaque(false);
		titlePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, borderColor));
		JLabel titleLabel = new JLabel("Manage Books");
		titleLabel.setFont(headingFont);
		titleLabel.setForeground(headingTextColor);
		titlePanel.add(titleLabel);
		topPanel.add(titlePanel);
		
		JLabel lbl = new JLabel("Current Books:", JLabel.CENTER);
		lbl.setFont(buttonFont);
		lbl.setForeground(Color.WHITE);
		topPanel.add(lbl);
		this.add(topPanel);

		JPanel booksPanel = new JPanel(new GridLayout(1,1));
		booksPanel.setOpaque(false);
		bookTable = new JTable();
		JScrollPane scrollPane = new JScrollPane(bookTable);
		scrollPane.getViewport().setBackground(addPanelColor);
		booksPanel.add(scrollPane);
		this.add(booksPanel);
		JPanel btnPanel = new JPanel();
		btnPanel.setOpaque(false);
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		btnPanel.add(Box.createRigidArea(new Dimension(300,0)));
		addNewBtn = makeButton("Add New Book");
		deleteBtn = makeButton("Delete Selected Book");
		btnPanel.add(addNewBtn);
		btnPanel.add(Box.createRigidArea(new Dimension(75,0)));
		btnPanel.add(deleteBtn);
		this.add(btnPanel);
		
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