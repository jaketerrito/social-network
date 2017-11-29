package project2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginView extends JPanel {
	private Controller controller;
	public LoginView(Controller controller){
		this.controller = controller;	
	    final int FIELD_WIDTH = 25;
	    JTextField name = new JTextField();
	    name.setEditable(false);
	    
	    JTextField pass = new JTextField();
	    pass.setEditable(false);
	    
	    JTextField usrname = new JTextField(FIELD_WIDTH);
	    name.setText("username:");
	    
	    JPasswordField usrpass = new JPasswordField(FIELD_WIDTH);
	    pass.setText("password:");
	    
	    JTextField wrong = new JTextField();
	    wrong.setEditable(false);
	    wrong.setText("   Invalid username or password.   ");
	    
	    JButton loginButton = new JButton("Login");
	    JButton registerButton = new JButton("Register");
	    registerButton.addActionListener(new
		    	ActionListener()
		    	{
		    		public void actionPerformed(ActionEvent event)
		    		{
		    			controller.register();
		    		}
		        });
	    
	    
	    loginButton.addActionListener(new
	    	ActionListener()
	    	{
	    		public void actionPerformed(ActionEvent event)
	    		{
	    			if(controller.login(usrname.getText(),String.copyValueOf(usrpass.getPassword()))){
	    				setVisible(false);
	    			}else{
	    				usrname.setText("");
	    				usrpass.setText("");
	    				add(wrong);
	    				controller.buttonClick();
	    			}
	    		}
	        });
	    //frame.setSize(400, 200);	      
	    //frame.setPreferredSize(new Dimension(400,200)); 
	      add(name);
	      add(usrname);
	      add(pass);
	      add(usrpass);
	      add(loginButton);
	      add(registerButton);
	      setLayout(new FlowLayout());
	   }
	}

