package Utils;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public interface ComponentGenerator extends Styles
{
	public default JLabel createTextFieldLabel(String text)
	{
		JLabel label = new JLabel(text,JLabel.CENTER);
		label.setFont(textFieldFont);
		label.setForeground(labelTextColor);
		return label;
	}
	
	public default JButton makeButton(String text)
	{
		JButton btn = new JButton(text);
		btn.setBackground(tabBackgroundSelected);
		btn.setForeground(Color.WHITE);
		btn.setBorder(new LineBorder(labelTextColor, 2));
		btn.setMaximumSize(new Dimension(250, 50));
		btn.setFont(buttonFont);
		return btn;
	}
}
//end-class