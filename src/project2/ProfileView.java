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
import java.awt.BorderLayout;
import java.awt.EventQueue;
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

import project2.MenuBar;
import java.util.*;

public class ProfileView implements View{
	private User user;
	private ArrayList<Post> friendsPosts;
	private HashMap<String,User> users;
	private JFrame frame;
	private Controller controller;
	private String viewer;
	private JPanel contentPane;
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
	public ProfileView(String viewer,User user, HashMap<String,User> users, ArrayList<Post> friendsPosts, Controller controller) {
		frame = new JFrame();
		this.user = user;
		this.friendsPosts = friendsPosts;
		this.users = users;
		this.controller = controller;
		this.viewer = viewer;
	}
	
	public void close() {
		frame.dispose();
	}
	
	public void draw(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 896, 611);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		
		JLabel lblProfileNameAnd = new JLabel(user.getName());
		lblProfileNameAnd.setIcon(new ImageIcon(user.getImage()));
		lblProfileNameAnd.setVerticalTextPosition(JLabel.BOTTOM);
		lblProfileNameAnd.setHorizontalTextPosition(JLabel.CENTER);
		
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
			friendsList.add(temp);
		}
		
		

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
			System.out.println(post.getText());
			tempPanel = new PostPanel(user, post, users,controller);
			userWall.add(tempPanel);
		}
		
		MenuBar menuBar = new MenuBar(viewer,user,users,controller);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblProfileNameAnd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(friendsListScroll, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
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
						.addComponent(lblProfileNameAnd)
						.addComponent(menuBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(wallScroll, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE)
						.addComponent(friendsListScroll, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		
		
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
		contentPane.setLayout(gl_contentPane);
		frame.pack();
		frame.setVisible(true);
	}
}
