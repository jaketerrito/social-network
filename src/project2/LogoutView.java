package project2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * A JPanel that confirms the user has logged out.
 * @author jterrito
 *
 */
public class LogoutView extends JPanel {
	public LogoutView(Controller controller){
	    JTextField message = new JTextField("Successfully Logged Out");
	    message.setEditable(false);
	    
	    JButton exitButton = new JButton("Exit");
	    exitButton.addActionListener(new
	    	ActionListener()
	    	{
	    		public void actionPerformed(ActionEvent event)
	    		{
	    			controller.exit();
	    		}
	        });
	      add(message);
	      add(exitButton);
	      setLayout(new FlowLayout());
	   }
	}
