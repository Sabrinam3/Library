package Controllers;
/**
 * Program Name: LibraryController.java
 * Purpose:Controller to get data from the modal and populate the view
 * Coder: Sabrina Tessier
 * Date: Jul. 17, 2019
 */
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableModel;

import Models.LibraryModel;
import Utils.FieldLimitExceededException;
import Utils.InvalidDataException;
import Utils.Styles;
import Utils.emptyFieldException;
import Views.AddNewBook;
import Views.AddNewBorrower;
import Views.Author;
import Views.LibraryView;
import Views.ReportResults;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Vector;

import javax.swing.*;

public class LibraryController implements Styles
{
	private LibraryView view;
	private LibraryModel model;
	
	public LibraryController(LibraryView v, LibraryModel m)
	{
		view = v;
		model = m;
	}
	
  /**
   * Adds action listeners to view components
   * @return void
   */
	public void initController()
	{	
			//register change listener for JTabbedPane
			JTabbedPane pane = view.getPane();
			pane.addChangeListener(new libraryTabListener(pane));
			
			//Register action listeners for welcome panel buttons
			view.getWelcomePanel().getManageBorrowersBtn().addActionListener(new panelButtonListener());
			view.getWelcomePanel().getManageBooksBtn().addActionListener(new panelButtonListener());
			view.getWelcomePanel().getCheckOutBookBtn().addActionListener(new panelButtonListener());
			view.getWelcomePanel().getCheckInBookBtn().addActionListener(new panelButtonListener());
			view.getWelcomePanel().getReportsBtn().addActionListener(new panelButtonListener());
			view.getWelcomePanel().getHelpBtn().addActionListener(new panelButtonListener());
			
			//ACTION LISTENER FOR ADD NEW BOOK
			view.getBooksPanel().getAddNewBtn().addActionListener((e) -> {
					view.setAddNewBookPanel(new AddNewBook());
					view.getAddNewBook().getAddNewBookPanel().getOkBtn().addActionListener(new addNewBookListener());
					view.getAddNewBook().getAddNewBookPanel().getAddAnotherAuthorButton().addActionListener((ev) -> {
						String authorFName = view.getAddNewBook().getAddNewBookPanel().getAuthorFNField().getText();
						String authorLName = view.getAddNewBook().getAddNewBookPanel().getAuthorLNField().getText();
													  
						if(!authorFName.contentEquals("") && !authorLName.contentEquals(""))
						{
							view.getAddNewBook().getAddNewBookPanel().getAuthorList().add(new Author(authorFName, authorLName));
							view.getAddNewBook().getAddNewBookPanel().getAuthorFNField().setText("");
							view.getAddNewBook().getAddNewBookPanel().getAuthorLNField().setText("");
						}
					});
					view.getAddNewBook().getAddNewBookPanel().getCancelButton().addActionListener((ex) -> {
						view.getAddNewBook().dispose();
					});
					
			});
			//ACTION LISTENER FOR ADD NEW BORROWER
			view.getBorrowersPanel().getAddNewBtn().addActionListener((e) -> {
					view.setAddNewBorrowerPanel(new AddNewBorrower("Add New Borrower"));
					view.getAddNewBorrower().getAddNewBorrowerPanel().getOkBtn().addActionListener(new addNewBorrowerListener());
					view.getAddNewBorrower().getAddNewBorrowerPanel().getCancelButton().addActionListener((ex) -> {
						view.getAddNewBorrower().dispose();
					});
			});
			
			//ACTION LISTENER FOR UPDATE BORROWER
			view.getBorrowersPanel().getUpdateBtn().addActionListener(new UpdateBorrowerListener());
			//DELETE BORROWER LISTENER
			view.getBorrowersPanel().getDeleteBtn().addActionListener(new DeleteListener());
			
			//DELETE BOOK LISTENER
			view.getBooksPanel().getDeleteBtn().addActionListener(new DeleteListener());
			
			//ACTION LISTENER TO UPDATE THE DATE FIELDS 
			view.getLoansPanel().getLoanPeriodDropdown().addActionListener((e) -> {
					LocalDate today = LocalDate.now(); //get today's date and populate the date due field
					LocalDate dateDue = null;
					if(view.getLoansPanel().getLoanPeriodDropdown().getSelectedIndex() != -1)
					{
						String loanPeriod = view.getLoansPanel().getLoanPeriodDropdown().getSelectedItem().toString();
						switch(loanPeriod)
						{
						case "1 Week": dateDue = today.plus(1, ChronoUnit.WEEKS);break;
						case "2 Weeks":dateDue = today.plus(2, ChronoUnit.WEEKS);break;
						case "3 Weeks":dateDue = today.plus(3, ChronoUnit.WEEKS);break;
						}
						if(loanPeriod != "")
						{
							view.getLoansPanel().getDateDueField().setText(dateDue.toString());
						}
					}
				});
			
				//ACTION LISTENER FOR CHECK OUT BUTTON
				view.getLoansPanel().getCheckOutButton().addActionListener(new checkOutListener());
			
			//ACTION LISTENER FOR CHECK IN BUTTON
			view.getCheckInPanel().getCheckInBtn().addActionListener(new checkInListener());
			
			//REPORTS PANEL ACTION LISTENERS
			view.getReportsPanel().getGenerateBtn().addActionListener(new generateReportListener());
			view.getReportsPanel().getBooksBySubjectBtn().addActionListener((e) -> {
				view.getReportsPanel().getFilterLabel().setText("Enter Subject: ");
				view.getReportsPanel().getFilterLabel().setVisible(true);
				view.getReportsPanel().getFilterOption().setVisible(true);
				view.getReportsPanel().getFilterOption().setText("");
				view.getReportsPanel().getFilterOption().requestFocus();
			});
			view.getReportsPanel().getBooksByAuthorBtn().addActionListener((e) -> {
				view.getReportsPanel().getFilterLabel().setText("Enter Author: ");
				view.getReportsPanel().getFilterLabel().setVisible(true);
				view.getReportsPanel().getFilterOption().setVisible(true);
				view.getReportsPanel().getFilterOption().setText("");
				view.getReportsPanel().getFilterOption().requestFocus();
			});
			view.getReportsPanel().getallBooksBtn().addActionListener((e) -> {
				if(view.getReportsPanel().getFilterLabel().isVisible())
				{
					view.getReportsPanel().getFilterLabel().setVisible(false);
					view.getReportsPanel().getFilterOption().setVisible(false);
				}
			});
			view.getReportsPanel().getallBorrowersBtn().addActionListener((e) -> {
				if(view.getReportsPanel().getFilterLabel().isVisible())
				{
					view.getReportsPanel().getFilterLabel().setVisible(false);
					view.getReportsPanel().getFilterOption().setVisible(false);
				}
			});
			view.getReportsPanel().getbooksOnLoanBtn().addActionListener((e) -> {
				if(view.getReportsPanel().getFilterLabel().isVisible())
				{
					view.getReportsPanel().getFilterLabel().setVisible(false);
					view.getReportsPanel().getFilterOption().setVisible(false);
				}
			});
	}//end init controller
	
	/*
	 * Action listener class for the JTabbedPane in the application
	 */
	private class libraryTabListener implements ChangeListener
	{
		private JTabbedPane _pane;
		
		public libraryTabListener(JTabbedPane pane)
		{
			_pane = pane;
		}
		/*
		 * Method executes when user clicks any of the tabs. Data is updated accordingly in the view.
		 */
		public void stateChanged(ChangeEvent e)
		{
			try
			{
				int tab = _pane.getSelectedIndex();
				
				for(int i = 0; i < 6; i++)
				{
					if(tab == i)
					{
						_pane.setBackgroundAt(i, tabBackgroundSelected);
					}
					else
					{
						_pane.setBackgroundAt(i, tabBackgroundNotSelected);
					}
				}
				
				//Ensure the view components are loading updated data
				switch(tab)
				{
					case 1: //borrowers
					{
						TableModel mdl = model.getAllBorrowers();
						if(mdl != null)
						{
							view.getBorrowersPanel().getBorrowerTable().setModel(model.getAllBorrowers());
						}else
						{
							view.getBorrowersPanel().getBorrowerTable().setVisible(false);
							JOptionPane.showMessageDialog(null, "No Data to Display", "No Data", JOptionPane.INFORMATION_MESSAGE);
						}
						
						break;
					}
					case 2: //books
					{
						TableModel mdl = model.getAllBooks();
						if(mdl != null)
						{
							view.getBooksPanel().getBookTable().setModel(model.getAllBooks());
						}else
						{
							view.getBooksPanel().getBookTable().setVisible(false);
							JOptionPane.showMessageDialog(null, "No Data to Display", "No Data", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					case 3: //loans
					{
						view.getLoansPanel().getBookDropdown().setModel(model.getBookList());
						view.getLoansPanel().getBookDropdown().setSelectedIndex(-1);
						view.getLoansPanel().getBorrowerDropdown().setModel(model.getBorrowerList());
						view.getLoansPanel().getBorrowerDropdown().setSelectedIndex(-1);
						String[] loanOptions = {"1 Week", "2 Weeks", "3 Weeks"};
						view.getLoansPanel().getLoanPeriodDropdown().setModel(new DefaultComboBoxModel<String>(loanOptions));
						view.getLoansPanel().getLoanPeriodDropdown().setSelectedIndex(-1);
					}
					case 4: //check in
					{
						view.getCheckInPanel().getBookDropdown().setModel(new DefaultComboBoxModel<String>(model.getBookListCheckedOut()));
						view.getCheckInPanel().getBookDropdown().setSelectedIndex(-1);
					}
				}
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
		
		}		
	}
	
	/*
	 * Action listener for buttons on welcome view. 
	 */
	private class panelButtonListener implements ActionListener
	{
		
		/*
		 * actionPerformed method sets active tab in the JTabbedPane 
		 */
		@Override
		public void actionPerformed(ActionEvent ev)
		{
			JTabbedPane pane = view.getPane();
			if(ev.getActionCommand().equals("Manage Borrowers"))
			{
				pane.setSelectedIndex(1);
			}
			else if(ev.getActionCommand().equals("Manage Books"))
			{
				pane.setSelectedIndex(2);
			}
			else if(ev.getActionCommand().equals("Loan Book"))
			{
				pane.setSelectedIndex(3);
			}
			else if(ev.getActionCommand().equals("Check In Book"))
			{
				pane.setSelectedIndex(4);
			}
			else if(ev.getActionCommand().equals("Reports"))
			{
				pane.setSelectedIndex(5);
			}
			else if(ev.getActionCommand().equals("Help"))
			{
				JOptionPane.showMessageDialog(null, "For Assistance using the library application, please call 1-800-333-HELP", "Help", JOptionPane.INFORMATION_MESSAGE);
			}	
		}	
	}
	
	/*
	 * Action listener for adding a new borrower
	 */
	private class addNewBorrowerListener implements ActionListener
	{
		/*
		 * actionPerformed tries to add the new borrower and reports if the operation was successful
		 * This method also serves as an action listener for updating a borrower's data
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String firstName = view.getAddNewBorrower().getAddNewBorrowerPanel().getFirstNameField().getText();
			String lastName = view.getAddNewBorrower().getAddNewBorrowerPanel().getLastNameField().getText();
			String email = view.getAddNewBorrower().getAddNewBorrowerPanel().getEmailField().getText();
			int result = 0;
			
			if(view.getAddNewBorrower().getAddNewBorrowerPanel().getTitleLabel().getText().equals("Add New Borrower"))
			{
				if(e.getActionCommand().contentEquals("Ok"))
				{
					result = model.addNewBorrower(firstName, lastName, email);
					if(result > 0) //refresh contents of borrower list
					{
							TableModel mdl = model.getAllBorrowers();
							if(mdl != null)
							{
								view.getBorrowersPanel().getBorrowerTable().setModel(mdl);
								view.getAddNewBorrower().dispose();
								JOptionPane.showMessageDialog(null, "Borrower Added Successfully", "Add Successful", JOptionPane.INFORMATION_MESSAGE);	
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Problem Adding Borrower", "Add Unsuccessful", JOptionPane.ERROR_MESSAGE);
							}				
					}	
				}
				else
				{
					view.getAddNewBorrower().dispose();
				}
			
			}
			else //update
			{
				if(e.getActionCommand().contentEquals("Ok"))
				{
					JTable tbl = view.getBorrowersPanel().getBorrowerTable();
					int row = tbl.getSelectedRow();
					String id = tbl.getModel().getValueAt(row, 0).toString();
					result = model.updateBorrower(id, firstName, lastName, email);
				if(result > 0) //refresh contents of borrower list
				{
						TableModel mdl = model.getAllBorrowers();
						if(mdl != null)
						{
							view.getBorrowersPanel().getBorrowerTable().setModel(mdl);
							view.getAddNewBorrower().dispose();
							JOptionPane.showMessageDialog(null, "Borrower Updated Successfully", "Update Successful", JOptionPane.INFORMATION_MESSAGE);	
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Problem Updating Borrower", "Update Unsuccessful", JOptionPane.ERROR_MESSAGE);
						}
				}	
				}else
				{
					view.getAddNewBorrower().dispose();
				}	
			}
		}
	}
	
	/*
	 * Action listener for updating a borrower
	 */
	private class UpdateBorrowerListener implements ActionListener
	{
		/*
		 * actionPerformed opens the window for the user to update the data and populates the form fields with the current data
		 */
		@Override
		public void actionPerformed(ActionEvent e)
		{		
			 //get the selected borrower from the table
			 JTable tbl = view.getBorrowersPanel().getBorrowerTable();
			 int row = tbl.getSelectedRow();
			 if(row == -1)
			 {
				 JOptionPane.showMessageDialog(null, "Select a Borrower from the Table", "Error", JOptionPane.ERROR_MESSAGE);
			 }
			 else
			 {
				 	String lastName = tbl.getModel().getValueAt(row, 1).toString();
					String firstName = tbl.getModel().getValueAt(row, 2).toString();
					String email = tbl.getModel().getValueAt(row, 3).toString();
					view.setAddNewBorrowerPanel(new AddNewBorrower("Update Borrower"));
					view.getAddNewBorrower().getAddNewBorrowerPanel().getOkBtn().addActionListener(new addNewBorrowerListener());
					view.getAddNewBorrower().getAddNewBorrowerPanel().getCancelButton().addActionListener(new addNewBorrowerListener());
					view.getAddNewBorrower().getAddNewBorrowerPanel().getTitleLabel().setText("Update Borrower");
					view.getAddNewBorrower().getAddNewBorrowerPanel().getLastNameField().setText(lastName);
					view.getAddNewBorrower().getAddNewBorrowerPanel().getFirstNameField().setText(firstName);
					view.getAddNewBorrower().getAddNewBorrowerPanel().getEmailField().setText(email);
			 }
		}
		
	}
	
	/*
	 * Action Listener for adding a book
	 */
	private class addNewBookListener implements ActionListener
	{
		/*
		 * actionPerformed tries to add the new book to the database and reports to the user if the operation was successful. 
		 */
		@Override
		public void actionPerformed(ActionEvent e)
		{
			int edition;
			boolean valid;
			
			try
			{
				valid = true;
				String title = view.getAddNewBook().getAddNewBookPanel().getTitleField().getText();
				if(title.contentEquals(""))
				{
					view.getAddNewBook().getAddNewBookPanel().getTitleField().requestFocus();
					throw new emptyFieldException("Title");
				}
				else if(title.length() > 255)
				{
					view.getAddNewBook().getAddNewBookPanel().getTitleField().requestFocus();
					throw new FieldLimitExceededException("Title", "255");
				}
				
				String authorFName = view.getAddNewBook().getAddNewBookPanel().getAuthorFNField().getText();
				String authorLName = view.getAddNewBook().getAddNewBookPanel().getAuthorLNField().getText();
				if(authorFName.length() > 45)
				{
					valid = false;
					view.getAddNewBook().getAddNewBookPanel().getAuthorFNField().requestFocus();
					throw new FieldLimitExceededException("Author First Name", "45");
				}
				else if(authorLName.length() > 45)
				{
					valid = false;
					view.getAddNewBook().getAddNewBookPanel().getAuthorLNField().requestFocus();
					throw new FieldLimitExceededException("Author Last Name", "45");
				}
				if((authorFName.contentEquals("") || authorLName.contentEquals("")) && view.getAddNewBook().getAddNewBookPanel().getAuthorList().isEmpty())
				{
					valid = false;
					view.getAddNewBook().getAddNewBookPanel().getAuthorFNField().requestFocus();
					throw new emptyFieldException("Author");
				}

				String ISBN = view.getAddNewBook().getAddNewBookPanel().getISBNField().getText();
				if(ISBN.contentEquals(""))
				{
					valid = false;
					view.getAddNewBook().getAddNewBookPanel().getISBNField().requestFocus();				
					throw new emptyFieldException("ISBN");	
				}
				else if(ISBN.length() > 13)
				{
					valid = false;
					view.getAddNewBook().getAddNewBookPanel().getISBNField().requestFocus();
					throw new FieldLimitExceededException("ISBN", "13");
				}
				else if(ISBN.length() < 13)
				{
					valid = false;
					view.getAddNewBook().getAddNewBookPanel().getISBNField().requestFocus();
					throw new InvalidDataException("ISBN field must be exactly 13 characters");
				}
				//TODO: check for unique ISBN
				String editionS = view.getAddNewBook().getAddNewBookPanel().getEditionField().getText();
				if(editionS.contentEquals(""))
				{
					valid = false;
					view.getAddNewBook().getAddNewBookPanel().getEditionField().requestFocus();
					throw new emptyFieldException("Edition");
				}
				else if(editionS.length() > 3)
				{
					valid = false;
					view.getAddNewBook().getAddNewBookPanel().getEditionField().requestFocus();
					throw new FieldLimitExceededException("Edition", "3");
				}
				try
				{
					edition = Integer.parseInt(editionS);
				}catch(NumberFormatException ex)
				{
					valid = false;
					view.getAddNewBook().getAddNewBookPanel().getEditionField().requestFocus();
					throw new InvalidDataException("Edition must be a numeric field between 1 and 3 digits");		
				}
				
				String subject = view.getAddNewBook().getAddNewBookPanel().getSubjectField().getText();
				if(subject.contentEquals(""))
				{
					valid = false;
					view.getAddNewBook().getAddNewBookPanel().getSubjectField().requestFocus();
					throw new emptyFieldException("Subject");
				}
				else if(subject.length() > 45)
				{
					valid = false;
					view.getAddNewBook().getAddNewBookPanel().getSubjectField().requestFocus();
					throw new FieldLimitExceededException("Subject", "45");
				}
				
				if(valid)
				{
					//add author to list
					if(!authorFName.contentEquals("") && !authorLName.contentEquals(""))
					{
						view.getAddNewBook().getAddNewBookPanel().getAuthorList().add(new Author(authorFName, authorLName));
					}
					//Try inserting the new book
					int result = model.addNewBook(title, view.getAddNewBook().getAddNewBookPanel().getAuthorList(), ISBN, edition, subject);
				
					if(result == 1)
					{
						JOptionPane.showMessageDialog(null, "Book Added Successfully", "Add Successful", JOptionPane.INFORMATION_MESSAGE);
						//Refresh the table
						view.getBooksPanel().getBookTable().setModel(model.getAllBooks());
						view.getAddNewBook().dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Problem Adding Book", "Add Unsuccessful", JOptionPane.ERROR_MESSAGE);
					}
				}
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}		
			
		}
	}
	
	/*
	 * Delete listener for both borrowers and books
	 */
	private class DeleteListener implements ActionListener
	{
		/*
		 * actionPerformed tries to delete the book or borrower and reports to the user whether the operation was successful
		 */
		@Override
		public void actionPerformed(ActionEvent e)
		{
			int input;
			if(e.getActionCommand().equals("Delete Selected Borrower"))
			{
				 //get the selected borrower from the table
				 JTable tbl = view.getBorrowersPanel().getBorrowerTable();
				 int row = tbl.getSelectedRow();
				 if(row == -1)
				 {
					 JOptionPane.showMessageDialog(null, "Select a Borrower from the Table", "Error", JOptionPane.ERROR_MESSAGE);
				 }
				 else
				 {
						ImageIcon icon = new ImageIcon("x-button.png");
						 input = JOptionPane.showConfirmDialog(null, "Are you sure you'd like to delete?", "Confirm delete",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
						 if(input == JOptionPane.YES_OPTION)
						 {
							 String id = tbl.getModel().getValueAt(row, 0).toString();
								 int result = model.deleteBorrowerById(id);
								 if(result != 0)
								 {
											TableModel mdl = model.getAllBorrowers();
											if(mdl != null)
											{
												view.getBorrowersPanel().getBorrowerTable().setModel(mdl);
												JOptionPane.showMessageDialog(null, "Borrower Deleted Successfully", "Delete Successful", JOptionPane.INFORMATION_MESSAGE);	
											}
											else
											{
												view.getBorrowersPanel().getBorrowerTable().setVisible(false);
												JOptionPane.showMessageDialog(null, "No Data to Display", "No Data", JOptionPane.INFORMATION_MESSAGE);
											}
								 }
						 }
				 }
			}
			else if(e.getActionCommand().equals("Delete Selected Book"))
			{
				ImageIcon icon = new ImageIcon("x-button.png");
				 input = JOptionPane.showConfirmDialog(null, "Are you sure you'd like to delete?", "Confirm delete",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
				 if(input == JOptionPane.YES_OPTION)
				 {
					 //get the selected book from the table
					 JTable tbl = view.getBooksPanel().getBookTable();
					 int row = tbl.getSelectedRow();
					 String title = tbl.getModel().getValueAt(row, 0).toString();
					 String edition = tbl.getModel().getValueAt(row, 1).toString();
						 int result = model.deleteBookById(model.getBookIdByTitleEdition(title, edition));
						 if(result != 0)
						 {
							 	TableModel mdl = model.getAllBooks();
								if(mdl != null)
								{
									view.getBooksPanel().getBookTable().setModel(mdl);
									JOptionPane.showMessageDialog(null, "Book Deleted Successfully", "Delete Successful", JOptionPane.INFORMATION_MESSAGE);	
								}
								else
								{
									view.getBooksPanel().getBookTable().setVisible(false);
									JOptionPane.showMessageDialog(null, "No Data to Display", "No Data", JOptionPane.INFORMATION_MESSAGE);
								}
						 }
				 }
				 else
				 {
					 //do nothing
				 }
			}
		}
	}

	/*
	 * Action listener for checking out a book
	 */
	private class checkOutListener implements ActionListener
	{
		/*
		 * actionPerformed tries to update the status of the book in the database and reports to the user if the operation was successful
		 */

		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				int bookId;
				int borrowerId;
				if(view.getLoansPanel().getBookDropdown().getSelectedIndex() != -1)
				{
					String selectedBook = view.getLoansPanel().getBookDropdown().getSelectedItem().toString();
					bookId = Integer.parseInt(selectedBook.split("-")[0].strip());
					borrowerId = 0;
					if(view.getLoansPanel().getBorrowerDropdown().getSelectedIndex() != -1)
					{
						String selectedBorrower = view.getLoansPanel().getBorrowerDropdown().getSelectedItem().toString();
						String borrowerLastName = selectedBorrower.split(",")[0];
						String borrowerFirstName = selectedBorrower.split(",")[1].split(" - ")[0].strip();
						String borrowerEmail = selectedBorrower.split(" - ")[1];
						borrowerId = model.getBorrowerIdByName(borrowerFirstName, borrowerLastName, borrowerEmail);
					}else
					{
						throw new emptyFieldException("Borrower");
					}
					
				}else
				{
					throw new emptyFieldException("Book");
				}

				String comment = view.getLoansPanel().getCommentField().getText();
				String dateOut = view.getLoansPanel().getDateOutField().getText();
				String dateDue = view.getLoansPanel().getDateDueField().getText();
				
				if(dateDue.contentEquals(""))
				{
					throw new emptyFieldException("Loan period");
				}
					
					int result = model.checkOutBook(bookId, borrowerId, comment, dateOut, dateDue);
						
					if(result != 0)
					{
						JOptionPane.showMessageDialog(null, "Book Successfully Checked Out", "Loan Successful", JOptionPane.INFORMATION_MESSAGE);	
						//Refresh the drop down
						view.getLoansPanel().getBookDropdown().setModel(model.getBookList());
						view.getLoansPanel().getBookDropdown().setSelectedIndex(-1);

						//Clear form fields
						view.getLoansPanel().getBorrowerDropdown().setSelectedIndex(-1);
						view.getLoansPanel().getCommentField().setText("");
						view.getLoansPanel().getLoanPeriodDropdown().setSelectedIndex(-1);
						view.getLoansPanel().getDateDueField().setText("");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Problem Checking Out Book", "Loan Unsuccessful", JOptionPane.ERROR_MESSAGE);
					}
				
			
			}catch(emptyFieldException ex)
			{
				
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
	/*
	 * Action listener for checking in a book
	 */
	private class checkInListener implements ActionListener
	{
		/*
		* actionPerformed tries to update the status of the book in the database and reports to the user if the operation was successful
		 */
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String selectedBook = view.getCheckInPanel().getBookDropdown().getSelectedItem().toString();
			int bookId = Integer.parseInt(selectedBook.split("-")[0].strip());
			
			try
			{
				int result = model.checkInBook(bookId);
				if(result != 0)
				{
					JOptionPane.showMessageDialog(null, "Book Successfully Checked In", "Return Successful", JOptionPane.INFORMATION_MESSAGE);	
					//refresh the drop down
					view.getCheckInPanel().getBookDropdown().setModel(new DefaultComboBoxModel<String>(model.getBookListCheckedOut()));
					view.getCheckInPanel().getBookDropdown().setSelectedIndex(-1);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Problem Checking In Book", "Return Unsuccessful", JOptionPane.ERROR_MESSAGE);
				}
				
			}catch(Exception ex)
			 {
				 ex.printStackTrace();
			 }
		}
	}
	
	/*
	 * Action listener for the reports page
	 */
	private class generateReportListener implements ActionListener
	{
		/*
		 * actionPerformed checks which report the user is trying to generate and queries the database then creating a new view with the results in a table
		 */
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				if(view.getReportsPanel().getreportGroup().getSelection() == null)
				{
					JOptionPane.showMessageDialog(null, "Select A Report Type", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String selectedButton = view.getReportsPanel().getreportGroup().getSelection().getActionCommand();
					view.setReportResults(new ReportResults());
					TableModel mdl = null;
					
					if(selectedButton.contentEquals("All Books In The Library"))
					{		
						mdl = model.getAllBooks();
						view.getReportResults().getReportLabel().setText(selectedButton);
					}else if(selectedButton.contentEquals("Books On Loan"))
					{
						mdl = model.getBooksCheckedOut();
						view.getReportResults().getReportLabel().setText(selectedButton);
					}else if(selectedButton.contentEquals("Books By Subject"))
					{
						String subject = view.getReportsPanel().getFilterOption().getText();
						mdl = model.getBooksBySubject(subject);
						view.getReportResults().getReportLabel().setText(selectedButton + ": " + subject );
					}else if(selectedButton.contentEquals("Books By Author"))
					{
						String author = view.getReportsPanel().getFilterOption().getText();
						mdl = model.getBooksByAuthor(author);
						view.getReportResults().getReportLabel().setText(selectedButton + ": " + author );
					}else if(selectedButton.contentEquals("All Borrowers"))
					{
						mdl = model.getAllBorrowers();
						view.getReportResults().getReportLabel().setText(selectedButton);
					}
					
					
					if(mdl != null)
					{
						view.getReportResults().getReportResultsTable().setModel(mdl);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "No Data to Display", "No Data", JOptionPane.INFORMATION_MESSAGE);
					}	
				}
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}			
		}		
	}
}
//end-class