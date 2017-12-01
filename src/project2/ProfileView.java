package project2;

import java.util.Collections;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.image.BufferedImage;
import project2.MenuBar;
import java.util.*;
import javax.imageio.*;
import java.io.*;

/**
 * A JPanel for viewing user profiles and user's news feed. Contains friends list, username/picture, menubar, and a list of posts.
 * @author jterrito
 *
 */
public class ProfileView extends JPanel{
	private User user;
	private String viewer;
	private JTextField txtFriendsList;
	private JTextField txtWall;
	
	public String getUser(){
		return user.getUsername();
	}
	
	public User getSubject() {
		return user;
	}
	
	public String getViewer() {
		return viewer;
	}
	
	/**
	 * Creates a profile JPanel with image and friendlist of user. List of all posts from user, unless on home page where there is a list of all the user's friends posts.
	 * @param viewer The user currently logged in, null if on home page.
	 * @param user The user who is being viewed.
	 * @param users A list of every single user in the system.
	 * @param friendsPosts A list of all posts by the currently logged in user's friends.
	 * @param controller 
	 */
	public ProfileView(String viewer,User user, HashMap<String,User> users, ArrayList<Post> friendsPosts, Controller controller) {
		this.user = user;
		this.viewer = viewer;
		
		//Name and profile picture of user
		JLabel lblProfileNameAnd = new JLabel(user.getName());
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(user.getImage()));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(200,200,Image.SCALE_SMOOTH);
		lblProfileNameAnd.setIcon(new ImageIcon(dimg));
		lblProfileNameAnd.setVerticalTextPosition(JLabel.BOTTOM);
		lblProfileNameAnd.setHorizontalTextPosition(JLabel.CENTER);
		
		//List of friends, can click to view their profile
		JPanel friendsList = new JPanel();
		friendsList.setLayout(new BoxLayout(friendsList, BoxLayout.Y_AXIS));
		JScrollPane friendsListScroll = new JScrollPane(friendsList);
		friendsListScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        friendsListScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JButton temp;
		for(String username :users.get(user.getUsername()).getFriends()){
			temp = new JButton(users.get(username).getName());
			temp.addActionListener(new
			         ActionListener()
			         {
			            public void actionPerformed(ActionEvent event)
			            {
			               controller.viewProfile(users.get(username).getUsername());
			            }
			         });
			try {
			    img = ImageIO.read(new File(users.get(username).getImage()));
			} catch (IOException e) {
			    e.printStackTrace();
			}
			dimg = img.getScaledInstance(20,20,Image.SCALE_SMOOTH);
			temp.setIcon(new ImageIcon(dimg));
			
			friendsList.add(temp);
		}
		
		
		//List of posts, either of user or friends posts.
		JPanel userWall = new JPanel();
		userWall.setLayout(new BoxLayout(userWall, BoxLayout.Y_AXIS));
		JScrollPane wallScroll = new JScrollPane(userWall);
		wallScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		wallScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		PostPanel tempPanel;
		if(viewer != null) {
			friendsPosts = user.getPosts();
		}
		Collections.sort(friendsPosts);
		Post post;
		for(int i = 0; i < friendsPosts.size();i++){
			post = friendsPosts.get(i);
			tempPanel = new PostPanel(user, post, users,controller);
			userWall.add(tempPanel);
		}
		

		MenuBar menuBar = new MenuBar(viewer,user,users,controller);
		
		//Add or remove friend
		JButton btnAddFriend = new JButton("Add Friend");
		if(user.getFriends().contains(viewer)) {
			btnAddFriend.setText("Remove Friend");
		}
		System.out.println(viewer + user.getUsername());
		btnAddFriend.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {
               if(user.getFriends().contains(viewer)) {
            	   controller.removeFriend(user.getUsername());
               }else {
            	   controller.addFriend(user.getUsername());
               }
            }
         });
		
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblProfileNameAnd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(friendsListScroll, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
						.addComponent(btnAddFriend))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(menuBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(wallScroll, GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnAddFriend)
							.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
							.addComponent(lblProfileNameAnd))
						.addComponent(menuBar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(wallScroll, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE)
						.addComponent(friendsListScroll, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		//if at home or viewing your own profile, add friend shouldn't be there
		if(viewer == null || user.getUsername().equals(viewer)) {
			btnAddFriend.setVisible(false);
		} else{
			btnAddFriend.setVisible(true);
		}
				
		//Determines whether or not this user and viewer are friends and if the wall is visible to the viewer.
		if(viewer != null && !user.getFriends().contains(viewer) && !user.getUsername().equals(viewer)) {
			wallScroll.setVisible(false);
		}
		
		txtWall = new JTextField();
		txtWall.setEditable(false);
		if(viewer != null) {
			txtWall.setText("Wall:");
		} else {
			txtWall.setText("News Feed:");
		}
		wallScroll.setColumnHeaderView(txtWall);
		txtWall.setColumns(10);
		
		txtFriendsList = new JTextField();
		txtFriendsList.setEditable(false);
		txtFriendsList.setText("Friends List:");
		friendsListScroll.setColumnHeaderView(txtFriendsList);
		txtFriendsList.setColumns(10);
		setLayout(gl_contentPane);
		
	}
}
