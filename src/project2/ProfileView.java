package project2;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

public class ProfileView implements View{
	public void draw(){
		JFrame frame = new JFrame();
		
		JTextField name = new JTextField();
	    name.setEditable(false);
	    name.setText("Jake");
	    
	    JTextField post = new JTextField();
	    post.setEditable(false);
	    post.setText("I like this software! 10/10 would recomend! WOWWOWOWEOWEOWERAWOERWOEAROWAEROAWEORWE\nAOROAWEROWEAORWEAORWOEAR");
	    
	    frame.setSize(400, 200);	      
	    frame.setPreferredSize(new Dimension(400,200)); 
	    frame.add(name);
	    frame.add(post);
	    frame.setLayout(new FlowLayout());
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	}
}
