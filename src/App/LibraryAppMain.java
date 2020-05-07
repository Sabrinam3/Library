package App;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Controllers.LibraryController;
import Models.LibraryModel;
import Views.LibraryView;

/**
 * Program Name: LibraryApp.java
 * Purpose: Runs the application
 * Coder: Sabrina Tessier
 * Date: Started: Jul. 17, 2019
 * Finished : August 8, 2019
 * Version: 1.0
 */

public class LibraryAppMain
{
	public static void main(String[] args)
	{
			new LibraryController(new LibraryView(), new LibraryModel()).initController();
	}
}
//end-class