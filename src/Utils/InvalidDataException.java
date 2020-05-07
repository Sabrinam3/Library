package Utils;
import javax.swing.JOptionPane;

public class InvalidDataException extends Exception
{
	public InvalidDataException(String message)
	{
		JOptionPane.showMessageDialog(null, message, "Invalid Data", JOptionPane.ERROR_MESSAGE);
	}
}
//end-class