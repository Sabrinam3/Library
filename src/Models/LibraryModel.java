package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;
import java.sql.*;
import javax.sql.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import Utils.DbUtils;
import Views.Author;
public class LibraryModel
{
	private Connection myConn;
	private Statement myStmt;
	private ResultSet myRslt;
	
	/*
	 * opens the connection to the Books database and sets the Statement class variable
	 * @return void
	 */
	private void openDbConnection()
	{
		//Open connection
			try
			{
				 myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/info3136_books?useSSL=false", "root", "root");
				 myStmt = myConn.createStatement();
			}catch(SQLException ex)
			{
				JOptionPane.showMessageDialog(null, "Problem opening database connection. Message is " + ex.getMessage(), "Problem opening connection", JOptionPane.ERROR_MESSAGE);
			}
	}
	
	/*
	 * closes the connection to the Books database
	 * @return void
	 */
	private void closeDbConnection()
	{
		try
		{
			if(myRslt != null)
			{
				myRslt.close();
			}
			if(myStmt != null)
			{
				myStmt.close();
			}
			if(myConn != null)
			{
				myConn.close();
			}
		}catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Problem closing database connection, message is " + e.getMessage(), "Problem closing connection", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/*
	 * queries the database to get all the books and formats the result set into a table model
	 * @returns a TableModel of the book data
	 */
	public TableModel getAllBooks()
	{
		openDbConnection();
		
		TableModel model = null;		
		try
		{
			String queryString = "SELECT Title, Edition_Number, GROUP_CONCAT(First_name, ' ', Last_Name separator ',') AS 'Author[s]' FROM book " + 
					"INNER JOIN book_author on BookID = Book_BookID INNER JOIN Author on Author_AuthorID = AuthorID " + 
					"GROUP BY Title " + 
					"ORDER BY Title";
			ResultSet myRslt = myStmt.executeQuery(queryString);
			if(myRslt.next())
			{
				myRslt.beforeFirst();
				model = DbUtils.resultSetToTableModel(myRslt);
			}
		}catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null, "Error:  Message is " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);	
		}finally
		{
			closeDbConnection();
		}
		return model;
	}
	
	/*
	 * queries the database to get all the books and formats the result set into a default combo box model
	 * @returns a DefaultComboBoxModel of the book data
	 */
	public DefaultComboBoxModel<String> getBookList()
	{
		openDbConnection();
		
		Vector<String> books = new Vector<String>();
		try
		{
			String queryString = "SELECT * FROM book";
			myRslt = myStmt.executeQuery(queryString);
			while(myRslt.next())
			{
				//If the book is available, add to list
				if(myRslt.getInt(6) != 0)
				{
					String book = myRslt.getInt(1) + " - " + myRslt.getString(2) + ", Edition " + myRslt.getString(4);
					books.add(book);
				}

			}
		}catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Error:  Message is " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);	
		}finally
		{
			closeDbConnection();
		}
		return new DefaultComboBoxModel<String>(books);
	}
	
	/*
	 * queries the database to get all the books that are checked out and formats the result set into a table model
	 * @returns a TableModel of the book data
	 */
	public TableModel getBooksCheckedOut()
	{
		openDbConnection();
		TableModel model = null;
		try
		{
			String queryString = "SELECT Title, CONCAT(First_Name, ' ', Last_Name) AS 'Borrower Name', " +
														"Date_due FROM book INNER JOIN book_loan ON BookID = Book_BookID " + 
														"INNER JOIN borrower ON Borrower_Borrower_ID = Borrower_ID " + 
														"WHERE Date_returned IS NULL;";
			myRslt = myStmt.executeQuery(queryString);
			if(myRslt.next())
			{
				myRslt.beforeFirst();
				model = DbUtils.resultSetToTableModel(myRslt);
			}
		}catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Error:  Message is " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}finally
		{
			closeDbConnection();
		}
		return model;
	}
	
	/*
	 * queries the database to get all the books that are checkout out and formats the result set into a default combo box model
	 * @returns a DefaultComboBoxModel of the book data
	 */
	public Vector<String> getBookListCheckedOut()
	{
		openDbConnection();
		Vector<String> checkedOutBooks = new Vector<String>();
		try
		{
			String queryString = "SELECT * FROM book";
			myRslt = myStmt.executeQuery(queryString);
			while(myRslt.next())
			{
				//If the book is not available, add to list
				if(myRslt.getInt(6) ==0)
				{
					String book = myRslt.getInt(1) + " - " + myRslt.getString(2) + ", Edition " + myRslt.getString(4);
					checkedOutBooks.add(book);
				}

			}
		}catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Error:  Message is " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);	
		}finally
		{
			closeDbConnection();
		}
		return checkedOutBooks;
	}
	
	/*
	 * queries the database to get all the borrowers and formats the result set into a table model
	 * @returns a TableModel of the borrower data
	 */
	public TableModel getAllBorrowers()
	{
		openDbConnection();
		TableModel model = null;
		try
		{
			String queryString = "SELECT * FROM borrower";
			myRslt = myStmt.executeQuery(queryString);
			if(myRslt.next())
			{
				myRslt.beforeFirst();
				model = DbUtils.resultSetToTableModel(myRslt);
			}
		}catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Error:  Message is " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}finally
		{
			closeDbConnection();
		}
		return model;	
	}
	
	/*
	 * queries the database to get all the borrowers and formats the result set into a default combo box model
	 * @returns a DefaultComboBoxModel of the borrower data
	 */
	public DefaultComboBoxModel<String> getBorrowerList()
	{
		openDbConnection();
		Vector<String> borrowers = new Vector<String>();
		try
		{
			String queryString = "SELECT * FROM borrower";
			myRslt = myStmt.executeQuery(queryString);
			while(myRslt.next())
			{
				String bookEdition = myRslt.getString(2) + ", " + myRslt.getString(3) + " - " + myRslt.getString(4);
				borrowers.add(bookEdition);
			}
		}catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Error:  Message is " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);	
		}finally
		{
			closeDbConnection();
		}
		return new DefaultComboBoxModel<String>(borrowers);
	}
	
	/*
	 * Adds a new borrower to the database 
	 * @param first - first name
	 * @param last - last name
	 * @param email - email address
	 * @returns int indicating if the add was successful
	 */
	public int addNewBorrower(String first, String last, String email)
	{
		openDbConnection();
		int returnedValue = 0;
		try
		{
			PreparedStatement myPrepStmt = myConn.prepareStatement("INSERT INTO borrower " + 
					 "(Last_Name, First_Name, Borrower_email) " +
					 "VALUES (?, ?, ?)");
			myPrepStmt.setString(1, last);
			myPrepStmt.setString(2, first);
			myPrepStmt.setString(3, email);
			returnedValue = myPrepStmt.executeUpdate();
			
		}catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Error:  Message is " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}finally
		{
			closeDbConnection();
		}
		return returnedValue;
	}
	
	/*
	 * Updates data for a specified borrower
	 * @param id - the id of the borrower to update
	 * @param first - first name
	 * @param last- last name
	 * @param email - email address
	 * @returns int indicating if update was successful
	 */
	public int updateBorrower(String id, String first, String last, String email)
	{
		openDbConnection();
		int returnedValue = 0;
		try
		{
			PreparedStatement myPrepStmt = myConn.prepareStatement("UPDATE borrower " + 
														"SET Last_Name = ?, First_Name = ?, Borrower_email = ? " +
														"WHERE Borrower_ID = ? ");
			myPrepStmt.setString(1, last);
			myPrepStmt.setString(2, first);
			myPrepStmt.setString(3, email);
			myPrepStmt.setInt(4, Integer.parseInt(id));
			
			returnedValue = myPrepStmt.executeUpdate();
			
		}catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Error:  Message is " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);			
		}finally
		{
			closeDbConnection();
		}
		return returnedValue;
	}
	
	/*
	 * Adds a new book to the database 
	 * @param title - title of book
	 * @param authors - an array list of the author(s) of the book
	 * @param edition - the edition number
	 * @param subject - the subject of the book
	 * @returns int indicating if the add was successful
	 */
	public int addNewBook(String title, ArrayList<Author> authors, String ISBN, int edition, String subject)
	{
		openDbConnection();
		int result = -1;
		int bookId = -1;
		try
		{	
			myConn.setAutoCommit(false); //start a transaction
			
			//Make a new entry in the book table
			PreparedStatement myPrepStmt = myConn.prepareStatement("INSERT INTO book " + 
																														"(Title, ISBN, Edition_Number, Subject, Available) " +
																														"VALUES(?, ?, ?, ?, ?)");
			myPrepStmt.setString(1, title);
			myPrepStmt.setString(2, ISBN);
			myPrepStmt.setInt(3, edition);
			myPrepStmt.setString(4, subject);
			myPrepStmt.setInt(5, 1);
			
			result = myPrepStmt.executeUpdate();
			if(result != 1)
			{
				return result;
			}
			//Get the newly generated BookId
			String queryString = "SELECT * FROM book";
			myRslt = myStmt.executeQuery(queryString);
			myRslt.afterLast();
			while(myRslt.previous())
			{
				bookId = myRslt.getInt(1);
				break;
			}
				
			//Check each author provided for the book to see if it exists in the author table. If it does, get the AuthorID. 
			//If it doesn't, make a new entry in the author table and return the AuthorID
			for(int i = 0; i < authors.size(); i++)
			{
				String authorFName = authors.get(i).getfName();
				String authorLName = authors.get(i).getLname();
				int authorId = updateAuthorTableOrReturnId(authorFName, authorLName);
				if(authorId == -1)
				{
					return result;
				}
				
				//Make a new entry to the book_author junction table
				myPrepStmt = myConn.prepareStatement("INSERT INTO book_author " + 
						"(Book_BookID, Author_AuthorID) " +
						"VALUES(?, ?)");
				
				myPrepStmt.setInt(1, bookId);
				myPrepStmt.setInt(2, authorId);
				
				result = myPrepStmt.executeUpdate();
				if(result != 1)
				{
					return result;
				}
			}
			
		}catch(Exception ex)
		{
			//rollback transaction
			if (myConn != null) 
			{
        try 
        {
        	JOptionPane.showMessageDialog(null, "Error Updating Tables. Rollback has been performed", "Error", JOptionPane.ERROR_MESSAGE);
            myConn.rollback();
        }catch(SQLException excep) 
        {
            excep.printStackTrace();
        }			
			}
		}finally
		{
			//commit transaction
			try
			{
				myConn.setAutoCommit(true);
			}catch(SQLException excep) 
	    {
	      excep.printStackTrace();
	    }	
			closeDbConnection();
		}	
		return result;
	}
	
	/*
	 * When a new book is being added, this method queries the author table to see if the author already exists.
	 * if the author does already exist, returns the id of the author.
	 * If the author does not already exist, adds the new author to the database
	 * @param - authorFName - the author's first name
	 * @param - authorLname - the author's last name
	 * @returns int - either the pre-exisitng author's id or the newly generated id from the add
	 */
	public int updateAuthorTableOrReturnId(String authorFName, String authorLName)
	{
		int authorId = -1;
		int result = -1;
		try
		{			
			//Search the author table to see if the author already exists
			PreparedStatement myPrepStmt = myConn.prepareStatement("SELECT AuthorID FROM author " +
																														 "WHERE Last_Name = ? AND First_name = ?");
			myPrepStmt.setString(1, authorLName);
			myPrepStmt.setString(2, authorFName);
			myRslt = myPrepStmt.executeQuery();
			
			if(!myRslt.next())
			{
				//new author
				myPrepStmt = myConn.prepareStatement("INSERT INTO author " + 
							"(Last_Name, First_name) " +
							"VALUES(?, ?)");
				myPrepStmt.setString(1, authorLName);
				myPrepStmt.setString(2, authorFName);
				result = myPrepStmt.executeUpdate();
				if(result != 1)
				{
					return authorId;
				}
				
				//Find the newly generated AuthorId
				myPrepStmt = myConn.prepareStatement("SELECT AuthorID FROM author");
				myRslt = myPrepStmt.executeQuery();
				if(result != 1)
				{
					return authorId;
				}
				myRslt.afterLast();
				while(myRslt.previous())
				{
					authorId = myRslt.getInt(1);
					break;
				}
			}
			else
			{
				authorId = myRslt.getInt(1);
			}
			
		}catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Error:  Message is " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}		
		return authorId;
		
	}
	
	/*
	 * deletes a borrower from the database - borrower table and book loan table
	 * @param id - the id of the borrower to delete
	 * @returns int indicating if the delete was successful
	 */
	public int deleteBorrowerById(String id)
	{
		openDbConnection();
		int returnedValue = 0;
		try
		{
			myConn.setAutoCommit(false);
			//delete from book_loan table
			PreparedStatement myPrepStmt = myConn.prepareStatement("DELETE FROM book_loan " +
																														"WHERE Borrower_Borrower_ID = ?");
			myPrepStmt.setInt(1, Integer.parseInt(id));
			returnedValue = myPrepStmt.executeUpdate();
			
			//delete from borrower table
			myPrepStmt = myConn.prepareStatement("DELETE FROM borrower " + 
					 																 "WHERE Borrower_ID = ?");
			myPrepStmt.setInt(1, Integer.parseInt(id));
			returnedValue = myPrepStmt.executeUpdate();
			
		}catch(Exception ex)
		{
			//rollback transaction
			if (myConn != null) 
			{
        try 
        {
        	JOptionPane.showMessageDialog(null, "Problem Deleting Borrower", "Delete Unsuccessful", JOptionPane.ERROR_MESSAGE);
          myConn.rollback();
        }catch(SQLException excep) 
        {
            excep.printStackTrace();
        }			
			}
		}finally
		{
			//commit transaction
			try
			{
				myConn.setAutoCommit(true);
			}catch(SQLException excep) 
	    {
	      excep.printStackTrace();
	    }	
			closeDbConnection();
		}	
		return returnedValue;
	}
	
	/*
	 * deletes a book from the database
	 * @param id - the id of the book to delete
	 * @returns int indicating if the delete was successful
	 */
	public int deleteBookById(int id)
	{
		openDbConnection();
		int returnedValue = 0;
		try
		{
			myConn.setAutoCommit(false);
			//Delete entries from the book_author junction table
			PreparedStatement myPrepStmt = myConn.prepareStatement("DELETE FROM book_author WHERE Book_BookID = ?");
			 myPrepStmt.setInt(1, id);
			 returnedValue = myPrepStmt.executeUpdate();
			 
			 //Delete from the book table
			 myPrepStmt = myConn.prepareStatement("DELETE FROM book " + 
					 "WHERE BookID = ?");
			myPrepStmt.setInt(1, id);
			returnedValue = myPrepStmt.executeUpdate();
			if(returnedValue != 1)
			{
				return returnedValue;
			}

		}catch(Exception ex)
		{
			//rollback transaction
			if (myConn != null) 
			{
        try 
        {
        	JOptionPane.showMessageDialog(null, "Error Updating Tables. Rollback has been performed", "Error", JOptionPane.ERROR_MESSAGE);
            myConn.rollback();
        }catch(SQLException excep) 
        {
            excep.printStackTrace();
        }			
			}
		}finally
		{
			//commit transaction
			try
			{
				myConn.setAutoCommit(true);
			}catch(SQLException excep) 
	    {
	      excep.printStackTrace();
	    }	
			closeDbConnection();
		}	
		return returnedValue;
	}
	
	/*
	 * Finds the id of a book by the title and edition
	 * @param title - the title of the book
	 * @param edition - the edition of the book
	 * @returns - the id of the book 
	 */
	public int getBookIdByTitleEdition(String title, String edition)
	{
		openDbConnection();
		try
		{
			PreparedStatement myPrepStmt = myConn.prepareStatement("SELECT * FROM book WHERE Title = ? "
																														+ "AND Edition_Number = ?");
			myPrepStmt.setString(1, title);
			myPrepStmt.setInt(2, Integer.parseInt(edition));
			
			myRslt = myPrepStmt.executeQuery();
			
			while(myRslt.next())
			{
				return myRslt.getInt(1);
			}
			return -1;
				
		}catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Error:  Message is " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return -1;
		}finally
		{
			closeDbConnection();
		}
	}
	
	/*
	 * returns the id for a borrower 
	 * @param borrowerfirstName - the first name of the borrower
	 * @param borrowerLastName - the last name of the borrower
	 * @param borrowerEmail - the email of the borrower
	 * @returns int - the borrower id
	 */
	public int getBorrowerIdByName(String borrowerFirstName, String borrowerLastName, String borrowerEmail)
	{
		openDbConnection();
		try
		{
			PreparedStatement myPrepStmt = myConn.prepareStatement("SELECT * FROM borrower WHERE Last_Name = ? "
																														+ "AND First_Name = ? AND Borrower_email = ?");
			myPrepStmt.setString(1, borrowerLastName);
			myPrepStmt.setString(2, borrowerFirstName);
			myPrepStmt.setString(3, borrowerEmail);
			
			myRslt = myPrepStmt.executeQuery();
			
			while(myRslt.next())
			{
				return myRslt.getInt(1);
			}
			return -1;
				
		}catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Error:  Message is " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return -1;
		}finally
		{
			closeDbConnection();
		}
	}
	
	/*
	 * Tries to update the status of the book in the database  to 0(unavailable) and makes an entry into the book_loan table
	 * @param - bookId - the id of the book 
	 * @param - borrowerId - the id of the borrower checking out the book
	 * @param comment - comment for the loan
	 * @param dateOut - the date the book is being checked out
	 * @param dateDue - the date the book is due back
	 * @returns int indicating if the operation was successful
	 */
	public int checkOutBook(int bookId, int borrowerId, String comment, String dateOut, String dateDue)
	{
		openDbConnection();
		
		int returnedValue = 0;
		try
		{
			myConn.setAutoCommit(false); //start transaction
			
			//Update the loans table
			PreparedStatement myPrepStmt = myConn.prepareStatement("INSERT INTO book_loan " + 
					 "(Book_BookID, Borrower_Borrower_ID, Comment, Date_out, Date_due) " +
					 "VALUES (?, ?, ?, ?, ?)");
			
			myPrepStmt.setInt(1, bookId);
			myPrepStmt.setInt(2, borrowerId);
			myPrepStmt.setString(3, comment);
			myPrepStmt.setString(4, dateOut);
			myPrepStmt.setString(5, dateDue);
			
			returnedValue = myPrepStmt.executeUpdate();
			
			if(returnedValue != 1)
			{
				return returnedValue;
			}
			
			//Update the book table -> set book to unavailable
			myPrepStmt = myConn.prepareStatement("UPDATE book " + 
					"SET Available = 0 " +
					"WHERE BookID = ?");
			myPrepStmt.setInt(1, bookId);
			returnedValue = myPrepStmt.executeUpdate();
			
		}catch(Exception ex)
		{
			//rollback transaction
			if (myConn != null) 
			{
        try 
        {
        	JOptionPane.showMessageDialog(null, "Error Updating Tables. Rollback has been performed", "Error", JOptionPane.ERROR_MESSAGE);
            myConn.rollback();
        }catch(SQLException excep) 
        {
            excep.printStackTrace();
        }			
			}
		}finally
		{
			//commit transaction
			try
			{
				myConn.setAutoCommit(true);
			}catch(SQLException excep) 
	    {
	      excep.printStackTrace();
	    }	
			closeDbConnection();
		}	
		return returnedValue;
	}
	
	/*
	 * Tries to update the status of the book in the database to 1(available) and updates the date returned value in the book_loan table
	 * @param - bookId - the id of the book 
	 * @param - borrowerId - the id of the borrower checking out the book
	 * @param comment - comment for the loan
	 * @param dateOut - the date the book is being checked out
	 * @param dateDue - the date the book is due back
	 * @returns int indicating if the operation was successful
	 */
	public int checkInBook(int id)
	{
		openDbConnection();
		int returnedValue = 0;
		try
		{
			myConn.setAutoCommit(false);
			//Update the loans table
			PreparedStatement myPrepStmt = myConn.prepareStatement("SELECT * FROM book_loan WHERE Book_BookID = ?");
			myPrepStmt.setInt(1, id);
			myRslt = myPrepStmt.executeQuery();
			//The last returned result will be the last time this book was checked out
			myRslt.afterLast();
			while(myRslt.previous())
			{
					//Save the fields so we know that we are updating the right entry
					int borrowerId = myRslt.getInt(2);
					String dateOut = myRslt.getString(4);
					
					//Update the date returned field
					 myPrepStmt = myConn.prepareStatement("UPDATE book_loan " + 
																								"SET Date_returned = ? " +
																								"WHERE Book_BookID = ? AND Borrower_Borrower_ID = ? " + 
																								"AND Date_out = ?");
					 myPrepStmt.setString(1, LocalDate.now().toString());
					 myPrepStmt.setInt(2, id);
					 myPrepStmt.setInt(3, borrowerId);
					 myPrepStmt.setString(4, dateOut);
					 
					 returnedValue = myPrepStmt.executeUpdate();
					 if(returnedValue == 1)
					 {
						 break;
					 }
					 else
					 {
						 return returnedValue;
					 }
			}
																													
			
			//Update the book table -> set book back to available
			 myPrepStmt = myConn.prepareStatement("UPDATE book " + 
					"SET Available = 1 " +
					"WHERE BookID = ?");
			myPrepStmt.setInt(1, id);
			returnedValue = myPrepStmt.executeUpdate();
			
		}catch(Exception ex)
		{
			//rollback transaction
			if (myConn != null) 
			{
        try 
        {
        	JOptionPane.showMessageDialog(null, "Error Updating Tables. Rollback has been performed", "Error", JOptionPane.ERROR_MESSAGE);
            myConn.rollback();
        }catch(SQLException excep) 
        {
            excep.printStackTrace();
        }			
			}
		}finally
		{
			//commit transaction
			try
			{
				myConn.setAutoCommit(true);
			}catch(SQLException excep) 
	    {
	      excep.printStackTrace();
	    }	
			closeDbConnection();
		}	
		return returnedValue;
	}

	/*
	 * Queries the database to return all books for a specified author
	 * @param - the author's name
	 * @returns - a table model of the data
	 */
	public TableModel getBooksByAuthor(String authorName)
	{
		openDbConnection();
		TableModel mdl = null;
		try
		{
			String firstName = authorName.split(" ")[0];
			String lastName = authorName.split(" ")[1];
			int authorId = -1;
			PreparedStatement myPrepStmt = myConn.prepareStatement("SELECT AuthorID from author " +
																														 "WHERE First_name = ? AND Last_Name = ?");
			myPrepStmt.setString(1, firstName);
			myPrepStmt.setString(2, lastName);
			myRslt = myPrepStmt.executeQuery();
			while(myRslt.next())
			{
				authorId = myRslt.getInt(1);
			}
			
			//get list of all book ID's from junction table for author
			myPrepStmt = myConn.prepareStatement("SELECT Book_BookID from book_author " +
					 "WHERE Author_AuthorID = ?");
			
			myPrepStmt.setInt(1, authorId);
			myRslt = myPrepStmt.executeQuery();
			String bookIds = "";
			while(myRslt.next())
			{
				bookIds += myRslt.getInt(1) + ",";
			}
			if(bookIds != "")
			{
				bookIds = bookIds.substring(0, bookIds.length() -1);
				//Get all books with the book id
				String queryString = "SELECT Title, Edition_Number, GROUP_CONCAT(First_name, ' ', Last_Name separator ',') AS Authors FROM book " + 
						 "INNER JOIN book_author on BookID = Book_BookID INNER JOIN Author on Author_AuthorID = AuthorID " + 
						 "WHERE BookID IN (" + bookIds +
						 ") GROUP BY Title " + 
				     "ORDER BY Title";

				myRslt = myStmt.executeQuery(queryString);
				mdl = DbUtils.resultSetToTableModel(myRslt);
			}
		}catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Problem Generating Report", "Error", JOptionPane.ERROR_MESSAGE);
		}finally
		{
			closeDbConnection();
		}
		return mdl;
	}

	/*
	 * Queries the database to return all books for a specified subject
	 * @param - the subject
	 * @returns - a table model of the data
	 */
	public TableModel getBooksBySubject(String subject)
	{
		openDbConnection();
		TableModel mdl = null;
		try
		{			
			PreparedStatement myPrepStmt = myConn.prepareStatement("SELECT Title, Edition_Number, GROUP_CONCAT(First_name, ' ', Last_Name separator ',') AS Authors FROM book " + 
					 "INNER JOIN book_author on BookID = Book_BookID INNER JOIN Author on Author_AuthorID = AuthorID " + 
					 "WHERE Subject = ?" +
					 "GROUP BY Title " + 
			     "ORDER BY Title");
			
			myPrepStmt.setString(1, subject);
			myRslt = myPrepStmt.executeQuery();
			if(myRslt.next())
			{
				mdl = DbUtils.resultSetToTableModel(myRslt);
			}				
		}catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Problem Generating Report", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}finally
		{
			closeDbConnection();
		}
		return mdl;
	}

}
//end-class