package Views;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.border.*;

import Utils.ComponentGenerator;
/**
 * Program Name: BorrowersPanel.java
 * Purpose: Renders the Borrowers Panel of the application
 * Coder: Sabrina Tessier
 * Date: Jul. 17, 2019
 */

public class BorrowersPanel extends JPanel implements ComponentGenerator
{
	private JButton addNewBtn;
	private JButton deleteBtn;
	private JButton updateBtn;
	private JList<String> borrowerList;
	private JTable borrowerTable;
	
	public JList<String> getBorrowerList()
	{
		return borrowerList;
	}
	
	public JButton getAddNewBtn()
	{
		return addNewBtn;
	}
	public JButton getDeleteBtn()
	{
		return deleteBtn;
	}
	public JTable getBorrowerTable()
	{
		return borrowerTable;
	}
	public JButton getUpdateBtn()
	{
		return updateBtn;
	}
	public BorrowersPanel()
	{
		this.setLayout(new GridLayout(3, 1, 0, 0));
		JPanel topPanel = new JPanel(new GridLayout(2,1));
		topPanel.setOpaque(false);
		JPanel titlePanel = new JPanel();
		titlePanel.setOpaque(false);
		titlePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, borderColor));
		JLabel titleLabel = new JLabel("Manage Borrowers");
		titleLabel.setFont(headingFont);
		titleLabel.setForeground(headingTextColor);
		titlePanel.add(titleLabel);
		topPanel.add(titlePanel);
		
		JLabel lbl = new JLabel("Current Borrowers:", JLabel.CENTER);
		lbl.setFont(buttonFont);
		lbl.setForeground(Color.WHITE);
		topPanel.add(lbl);
		this.add(topPanel);
		
		JPanel borrowersPanel = new JPanel(new GridLayout(1,1));
		borrowerTable = new JTable();
		JScrollPane scrollPane = new JScrollPane(borrowerTable);
		scrollPane.getViewport().setBackground(addPanelColor);
		borrowersPanel.add(scrollPane);
		this.add(borrowersPanel);
		JPanel btnPanel = new JPanel();
		btnPanel.setOpaque(false);
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		btnPanel.add(Box.createRigidArea(new Dimension(150,0)));
		addNewBtn = makeButton("Add New Borrower");
		deleteBtn = makeButton("Delete Selected Borrower");
		updateBtn = makeButton("Update Selected Borrower");
		btnPanel.add(addNewBtn);
		btnPanel.add(Box.createRigidArea(new Dimension(75,0)));
		btnPanel.add(deleteBtn);
		btnPanel.add(Box.createRigidArea(new Dimension(75,0)));
		btnPanel.add(updateBtn);
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