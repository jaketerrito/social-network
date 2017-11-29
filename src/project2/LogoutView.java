package project2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LogoutView extends JPanel {
	private Controller controller;
	public LogoutView(Controller controller){
		this.controller = controller;	
	    final int FIELD_WIDTH = 25;
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
	     // frame.setSize(400, 200);	      
	      //frame.setPreferredSize(new Dimension(400,200)); 
	      add(message);
	      add(exitButton);
	      setLayout(new FlowLayout());
	   //   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //  frame.setVisible(true);
	   }
	}
