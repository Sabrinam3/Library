package Views;

/**
 * Program Name: WelcomeView.java
 * Purpose: Builds and shows the home screen for the library application
 * Coder: Sabrina Tessier
 * Date: Jul. 17, 2019
 */
import java.awt.*;
import javax.swing.*;

import Utils.ComponentGenerator;

public class LibraryView extends JFrame implements ComponentGenerator
{

	

	private WelcomePanel welcomePanel;
	private BorrowersPanel borrowersPanel;
	private BooksPanel booksPanel;
	private LoansPanel loansPanel;
	private CheckInPanel checkInPanel;
	private ReportsPanel reportsPanel;
	private ReportResults reportResults;
	private AddNewBook addNewBook;
	private AddNewBorrower addNewBorrower;
	private JTabbedPane pane;

	
	public LibraryView()
	{
		super("The Library");
		this.setSize(1200, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		loadTabbedPane();
		this.add(pane);
		this.setVisible(true);
	}

	public WelcomePanel getWelcomePanel()
	{
		return welcomePanel;
	}


	public BorrowersPanel getBorrowersPanel()
	{
		return borrowersPanel;
	}

	public void setBorrowersPanel(BorrowersPanel bp)
	{
		borrowersPanel = bp;
	}

	public BooksPanel getBooksPanel()
	{
		return booksPanel;
	}


	public LoansPanel getLoansPanel()
	{
		return loansPanel;
	}


	public CheckInPanel getCheckInPanel()
	{
		return checkInPanel;
	}

	public ReportsPanel getReportsPanel()
	{
		return reportsPanel;
	}

	public void setAddNewBookPanel(AddNewBook pnl)
	{
		addNewBook = pnl;
	}
	public void setAddNewBorrowerPanel(AddNewBorrower pnl)
	{
		addNewBorrower = pnl;
	}
	public AddNewBorrower getAddNewBorrower()
	{
		return addNewBorrower;
	}
	public AddNewBook getAddNewBook()
	{
		return addNewBook;
	}
	public ReportResults getReportResults()
	{
		return reportResults;
	}
	public void setReportResults(ReportResults reportResults)
	{
		this.reportResults = reportResults;
	}
	public JTabbedPane getPane()
	{
		return pane;
	}
	public void loadTabbedPane()
	{
		welcomePanel = new WelcomePanel();
		borrowersPanel = new BorrowersPanel();
		booksPanel = new BooksPanel();
		loansPanel = new LoansPanel();
		checkInPanel = new CheckInPanel();
		reportsPanel = new ReportsPanel();
		//load the tabbed pane
		UIManager.put("TabbedPane.selected",tabBackgroundSelected);
		pane = new JTabbedPane();
		pane.add(welcomePanel);
		pane.add(borrowersPanel);
		pane.add(booksPanel);
		pane.add(loansPanel);
		pane.add(checkInPanel);
		pane.add(reportsPanel);
		
		pane.setTabComponentAt(0, createTabLabel("Welcome"));
		pane.setTabComponentAt(1, createTabLabel("Borrowers"));
		pane.setTabComponentAt(2, createTabLabel("Books"));
		pane.setTabComponentAt(3, createTabLabel("Check Out Book"));
		pane.setTabComponentAt(4, createTabLabel("Check In Book"));
		pane.setTabComponentAt(5, createTabLabel("Reports"));
		formatTabbedPane();
	}
	public JLabel createTabLabel(String text)
	{
		JLabel label = new JLabel(text, JLabel.CENTER);
		label.setPreferredSize(tabSize);
		label.setFont(buttonFont);
		label.setForeground(Color.WHITE);
		return label;
	}
	
	public void formatTabbedPane()
	{
		for(int i = 1; i < 6; i++)
		{
			pane.setBackgroundAt(i, tabBackgroundNotSelected);
		}
	}


}
//end-class