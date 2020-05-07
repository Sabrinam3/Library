package Views;
/*
 * Class to represent an Author of a book
 */
public class Author
{
	private String fName;
	private String lname;
	public Author(String f, String l)
	{
		this.fName = f;
		this.lname = l;
	}
	public String getfName()
	{
		return fName;
	}
	public String getLname()
	{
		return lname;
	}

}