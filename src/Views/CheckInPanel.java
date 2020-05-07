package Views;

/**
 * Program Name: CheckInPanel.java
 * Purpose: Generates the check in panel for the application
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
public class CheckInPanel extends JPanel implements ComponentGenerator
{
	private JComboBox<String> bookDropdown;
	private JTextField dateInField;
	private JButton checkInBtn;
	
	public JComboBox<String> getBookDropdown()
	{
		return bookDropdown;
	}

	public JTextField getDateInField()
	{
		return dateInField;
	}

	public JButton getCheckInBtn()
	{
		return checkInBtn;
	}
	
	public CheckInPanel()
	{
		this.setLayout(new BorderLayout());
		JPanel topPanel = new JPanel(new GridLayout(2,1));
		topPanel.setOpaque(false);
		JPanel titlePanel = new JPanel();
		titlePanel.setOpaque(false);
		titlePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, borderColor));
		JLabel titleLabel = new JLabel("Check In Book");
		titleLabel.setFont(headingFont);
		titleLabel.setForeground(headingTextColor);
		titlePanel.add(titleLabel);
		topPanel.add(titlePanel);
		this.add(topPanel, BorderLayout.NORTH);

		JPanel secondPanel = new JPanel(new GridLayout(1, 4));
		secondPanel.setOpaque(false);	

		JPanel labelPanel = new JPanel(new GridLayout(6,1,0,20));
		labelPanel.setOpaque(false);

		
			
		labelPanel.add(createTextFieldLabel("Book: "));
		labelPanel.add(createTextFieldLabel("Date In: "));
		
		secondPanel.add(labelPanel);
		
		JPanel fieldPanel = new JPanel(new GridLayout(6, 1, 0, 20));
		fieldPanel.setOpaque(false);

		bookDropdown = new JComboBox<String>();
		bookDropdown.setPreferredSize(new Dimension(375,30));
		fieldPanel.add(bookDropdown);

		dateInField = new JTextField();
		dateInField.setPreferredSize(new Dimension(375,30));
		dateInField.setText(LocalDate.now().toString());
		fieldPanel.add(dateInField); 
		
		checkInBtn = makeButton("Check In");
		fieldPanel.add(checkInBtn);
		
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