package Utils;
import javax.swing.JOptionPane;

public class emptyFieldException extends Exception
{
	public emptyFieldException(String fieldName)
	{
		String message = fieldName + " cannot be empty! Please fill out all fields.";
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
//end-class