package Utils;
/**
 * Program Name:DbUtils.java
 * Purpose:provides a static method that will 
 * 				convert a ResultSet object passed in from a query into a
 *         TableModel object, which can then be used to create
 *         a JTable to display the data.
 * Coder: Charles, from Technojeeves.com/joomla/index.pho/free/59-resultset-to-tablemodel
 * 
 * Date: Jul 10, 2012 Revised by Bill Pulling...set the type of the Vectors to <String> and <Object> 
 * 
 * REVISED July 12, 2018, to accommodate change in constructor method from JDK 1.8
 *         to JDK 1.9 for the DefaultTableModel object. 
 *         See REVISION note below. 
 *         NOTE: Special thanks to Lynn Koudsi of Section 02 for suggesting the change to a Vector of vectors for the 
 *               method parameter to make it JDK 1.9 compliant.
 */

import java.sql.ResultSet;
import java.sql.ResultSetMetaData; 
import java.util.Vector; 
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
 

public class DbUtils
{
	/*
	 * Method Name: resultSetToTableModel
	 * Purpose: converts a ResultsSet object to a TableModel object
	 *          for use in a JTable object
	 * Accepts: a ReesultsSet object from a SQL query
	 * REturns: a TableModel object which can then be used
	 *          as an argument in the constructor method of JTable
	 *          to construct a JTable to display the data.
	 */
     public static TableModel resultSetToTableModel(ResultSet rs)
     {
         try {
        	 //get the metadata for number of columns and column names
             ResultSetMetaData metaData = rs.getMetaData();
             int numberOfColumns = metaData.getColumnCount();
             Vector<String> columnNames = new Vector<String>();
 
            // Get the column names and store in vector
             for (int column = 0; column < numberOfColumns; column++)
             {
                 columnNames.addElement(metaData.getColumnLabel(column + 1));
                 //NOTE: need to do the (+1) because metaData columns indexing starts at 1
                 //but JTable column numbering starts at 0....G-r-r-r-r! 
             }

             // Get all rows data and store in a Vector.
             
             //REVISION JULY 12, 2018: Needed to change the creation of the vector to 
             // store the rows to a two dimensional "vector of vectors" to satisfy
             // the constructor for the version 9 DefaultTableModel. (Lynn Koudsi suggested this! Well done!)
             // NOTE TO ME: run the old code under 1.8 to see if the method changed?
             // ANSWER: it has changed. Original code runs fine under 1.8, but it
             // has a compile error under 1.9/1.10
             
             //WORK-AROUND: need to create a 2D Vector for the first parameter in 
             // the call to the constructor for DefaultTableModel
             
             /*former version used under JDK1.8
              *
             Vector<Object> rows = new Vector<Object>(); 
             while (rs.next())
             {
                 Vector<Object> newRow = new Vector<Object>();
 
                for (int i = 1; i <= numberOfColumns; i++)
                {
                     newRow.addElement(rs.getObject(i));
                }//end for

                 rows.addElement(newRow);
             }//end while
             
              */
             
             //new version as of July 12, 2018
             Vector<Vector<Object>> rows = new Vector<Vector<Object>>();
 
             while (rs.next())
             {
                 Vector<Object> newRow = new Vector<Object>();
 
                for (int i = 1; i <= numberOfColumns; i++)
                {
                     newRow.addElement(rs.getObject(i));
                }//end for

                 rows.addElement(newRow);
             }//end while

            //return the DefaultTableModel object to the line that called it		
             return new DefaultTableModel(rows, columnNames);
         } catch (Exception e) 
         {
        	 System.out.println("Exception in DbUtils method resultSetToTableModel()...");
             e.printStackTrace();
             return null;
         }//end catch
     }//end method
 }//end class
