package project2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginView implements View {
	private Controller controller;
	
	public LoginView(Controller controller){
		this.controller = controller;
	}
	
	public void draw  () {		
		JFrame frame = new JFrame();
	    final int FIELD_WIDTH = 25;
	    JTextField name = new JTextField();
	    name.setEditable(false);
	    JTextField pass = new JTextField();
	    pass.setEditable(false);
	    JTextField usrname = new JTextField(FIELD_WIDTH);
	    name.setText("username:");
	    JPasswordField usrpass = new JPasswordField(FIELD_WIDTH);
	    pass.setText("password:");
	    JButton loginButton = new JButton("Login");
	    JTextField wrong = new JTextField();
	    wrong.setEditable(false);
	    wrong.setText("   INVALID USERNAME AND PASSWORD COMBINATION   ");
	    loginButton.addActionListener(new
	    	ActionListener()
	    	{
	    		public void actionPerformed(ActionEvent event)
	    		{
	    			if(controller.login(usrname.getText(),String.copyValueOf(usrpass.getPassword()))){
	    				frame.setVisible(false);
	    			}else{
	    				usrname.setText("");
	    				usrpass.setText("");
	    				frame.add(wrong);
	    				frame.pack();
	    			}
	    		}
	        });
	      frame.setSize(400, 200);	      
	      frame.setPreferredSize(new Dimension(400,200)); 
	      frame.add(name);
	      frame.add(usrname);
	      frame.add(pass);
	      frame.add(usrpass);
	      frame.add(loginButton);
	      frame.setLayout(new FlowLayout());
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setVisible(true);
	   }
	}

