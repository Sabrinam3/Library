package Views;

/**
 * Program Name: WelcomePanel.java
 * Purpose: Renders the Welcome Panel of the application
 * Coder: Sabrina Tessier
 * Date: Jul. 17, 2019
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

import Utils.ComponentGenerator;
public class WelcomePanel extends JPanel implements ComponentGenerator
{
	private ImageIcon welcomePageImage;
	private JButton manageBorrowersBtn;
	private JButton manageBooksBtn;
	private JButton checkOutBookBtn;
	private JButton checkInBookBtn;
	private JButton reportsBtn;
	private JButton helpBtn;

	public WelcomePanel()
	{
		this.setLayout(new GridLayout(2, 1, 0, 0));
		welcomePageImage = new ImageIcon(getClass().getResource("bookshelf.png"));
		JPanel imagePanel = new JPanel();
		imagePanel.setOpaque(false);
		imagePanel.add(new JLabel(welcomePageImage));
		JLabel welcomeLabel = new JLabel("Welcome to the Library");
		welcomeLabel.setFont(headingFont);
		welcomeLabel.setForeground(headingTextColor);
		imagePanel.add(welcomeLabel);
		this.add(imagePanel);
		this.add(makeButtonPanel());
	}
	
	public JButton getManageBorrowersBtn()
	{
		return manageBorrowersBtn;
	}

	public JButton getManageBooksBtn()
	{
		return manageBooksBtn;
	}

	public JButton getCheckOutBookBtn()
	{
		return checkOutBookBtn;
	}

	public JButton getCheckInBookBtn()
	{
		return checkInBookBtn;
	}

	public JButton getReportsBtn()
	{
		return reportsBtn;
	}

	public JButton getHelpBtn()
	{
		return helpBtn;
	}

	
	public JPanel makeButtonPanel()
	{
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(1, 2, 0, 0));
		buttonsPanel.setOpaque(false);
		JPanel firstPanel = new JPanel();
		firstPanel.setOpaque(false);
		firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.Y_AXIS));
		firstPanel.add(Box.createRigidArea(new Dimension(300, 50)));
		manageBorrowersBtn = makeButton("Manage Borrowers");
		firstPanel.add(manageBorrowersBtn);
		firstPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		checkOutBookBtn = makeButton("Loan Book");
		firstPanel.add(checkOutBookBtn);
		firstPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		reportsBtn = makeButton("Reports");
		firstPanel.add(reportsBtn);
		buttonsPanel.add(firstPanel);
		
		JPanel secondPanel = new JPanel();
		secondPanel.setOpaque(false);
		secondPanel.setLayout(new BoxLayout(secondPanel, BoxLayout.Y_AXIS));
		secondPanel.add(Box.createRigidArea(new Dimension(25, 50)));
		manageBooksBtn = makeButton("Manage Books");
		secondPanel.add(manageBooksBtn);
		secondPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		checkInBookBtn = makeButton("Check In Book");
		secondPanel.add(checkInBookBtn);
		secondPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		helpBtn = makeButton("Help");
		secondPanel.add(helpBtn);
		buttonsPanel.add(secondPanel);
		return buttonsPanel;
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