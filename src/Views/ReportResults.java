package Views;

/**
 * Program Name: BooksPanel.java
 * Purpose: Generates the Books panel for the application
 * Coder: Sabrina Tessier
 * Date: Jul. 20, 2019
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import Utils.ComponentGenerator;

public class ReportResults extends JFrame implements ComponentGenerator
{
	private JLabel reportLabel;
	private JTable reportResultsTable;
	private ReportResultsPanel panel;
	
	public JLabel getReportLabel()
	{
		return reportLabel;
	}
	public JTable getReportResultsTable()
	{
		return reportResultsTable;
	}
	
	public ReportResultsPanel getReportResultsPanel()
	{
		return panel;
	}
	public ReportResults()
	{
		super("Report Results");
		this.setSize(900,450);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel = new ReportResultsPanel();
		JPanel topPanel = new JPanel(new GridLayout(2,1));
		topPanel.setOpaque(false);
		JPanel titlePanel = new JPanel();
		titlePanel.setOpaque(false);
		titlePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, borderColor));
		JLabel titleLabel = new JLabel("Report Results");
		titleLabel.setFont(headingFont);
		titleLabel.setForeground(headingTextColor);
		titlePanel.add(titleLabel);
		topPanel.add(titlePanel);
		
		reportLabel = new JLabel("", JLabel.CENTER);
		reportLabel.setFont(buttonFont);
		reportLabel.setForeground(Color.WHITE);
		topPanel.add(reportLabel);
		panel.add(topPanel);
		
		JPanel booksPanel = new JPanel(new GridLayout(1,1));
		booksPanel.setOpaque(false);
		reportResultsTable = new JTable();
		JScrollPane scrollPane = new JScrollPane(reportResultsTable);
		scrollPane.getViewport().setBackground(addPanelColor);
		booksPanel.add(scrollPane);
		panel.add(booksPanel);
		this.add(panel);
		this.setVisible(true);
	}
	
	public class ReportResultsPanel extends JPanel implements ComponentGenerator
	{
		public ReportResultsPanel()
		{
			this.setLayout(new GridLayout(2, 1, 0, 0));
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
}
//end-class