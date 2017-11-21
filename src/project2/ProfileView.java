package project2;

import java.io.File;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;


public class ProfileView implements View{
	private User usr;
	private ArrayList<Post> friendsPosts = new ArrayList<Post>();
	
	public ProfileView(User usr,ArrayList<Post> friendsPosts) {
		this.usr = usr;
		this.friendsPosts = friendsPosts;
	}
	
	public void close() {
		System.out.println("Closing Profiile!");
	}
	
	public void draw(){
        System.out.println("name: " + usr.getName());
        System.out.println("location: the picture");
        //Friends
        System.out.println("friends: ");
        for(String friend: usr.getFriends()){
           System.out.print(friend +", ");
        }
        System.out.println();
        //Wall
        System.out.println("MYWALL:");
        for(Post post:usr.getPosts()){
           System.out.println("post:");
           System.out.println("\t" + post.getUsername() + " " + post.getTime());
           System.out.println("\t" + post.getText());
           System.out.print("\tlikes: ");
           for(String like:post.getLikes()){
              System.out.print(like + ", ");
           }
           System.out.println();
           System.out.println();
        }
        
        //NewsFeed
        System.out.println("NEWSFEED:");
        for(Post post: friendsPosts) {
        	System.out.println("post:");
            System.out.println("\t" + post.getUsername() + " " + post.getTime());
            System.out.println("\t" + post.getText());
            System.out.print("\tlikes: ");
            for(String like:post.getLikes()){
               System.out.print(like + ", ");
            }
            System.out.println();
            System.out.println();
        }
		
		
		/*JFrame frame = new JFrame();
		
		JTextField name = new JTextField();
	    name.setEditable(false);
	    name.setText("Jake");
	    
	    JTextField post = new JTextField();
	    post.setEditable(false);
	    post.setText("I like this software! 10/10 would recomend! WOWWOWOWEOWEOWERAWOERWOEAROWAEROAWEORWE\nAOROAWEROWEAORWEAORWOEAR");
	    

	    frame.setSize(400, 200);	      
	    frame.setPreferredSize(new Dimension(400,200)); 
	    frame.add(new JLabel(new ImageIcon("src/project2/image.jpg")));
	    frame.add(name);
	    frame.add(post);
	    frame.setLayout(new FlowLayout());
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);*/
	}
}
