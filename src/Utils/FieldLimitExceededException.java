package Utils;
import javax.swing.JOptionPane;

public class FieldLimitExceededException extends Exception
{
	public FieldLimitExceededException(String field, String limit)
	{
		String message = field + " length cannot exceed " + limit + " characters.";
		JOptionPane.showMessageDialog(null, message, "Field Limit Exceeded", JOptionPane.ERROR_MESSAGE);
	}
}
//end-class