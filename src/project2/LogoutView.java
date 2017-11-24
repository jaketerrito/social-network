package project2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LogoutView implements View {
	private Controller controller;
	private JFrame frame;
	public LogoutView(Controller controller){
		this.controller = controller;
	}
	
	public void close() {
		frame.dispose();
	}
	
	public void draw  () {		
		frame = new JFrame();
	    final int FIELD_WIDTH = 25;
	    JTextField message = new JTextField("Successfully Logged Out");
	    message.setEditable(false);
	    
	    JButton exitButton = new JButton("Exit");
	    exitButton.addActionListener(new
	    	ActionListener()
	    	{
	    		public void actionPerformed(ActionEvent event)
	    		{
	    			close();
	    		}
	        });
	      frame.setSize(400, 200);	      
	      frame.setPreferredSize(new Dimension(400,200)); 
	      frame.add(message);
	      frame.add(exitButton);
	      frame.setLayout(new FlowLayout());
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setVisible(true);
	   }
	}
