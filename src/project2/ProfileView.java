package project2;

import java.io.File;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.*;


public class ProfileView implements View{
	private User user;
	private ArrayList<Post> friendsPosts;
	private ArrayList<String> friends;
	private JFrame frame;
	private Controller controller;
	
	public ProfileView(User user,ArrayList<String> friends, ArrayList<Post> friendsPosts, Controller controller) {
		this.user = user;
		this.friendsPosts = friendsPosts;
		this.friends = friends;
		this.controller = controller;
	}
	
	public void close() {
		frame.dispose();
	}
	
	public void draw(){
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblProfileNameAnd = new JLabel(user.getName());
		lblProfileNameAnd.setIcon(new ImageIcon(user.getImage()));
		lblProfileNameAnd.setVerticalTextPosition(JLabel.BOTTOM);
		lblProfileNameAnd.setHorizontalTextPosition(JLabel.CENTER);
		GridBagConstraints gbc_lblProfileNameAnd = new GridBagConstraints();
		gbc_lblProfileNameAnd.insets = new Insets(0, 0, 5, 5);
		gbc_lblProfileNameAnd.gridx = 0;
		gbc_lblProfileNameAnd.gridy = 0;
		frame.getContentPane().add(lblProfileNameAnd, gbc_lblProfileNameAnd);
		
		
		JPanel friendsList = new JPanel();
		friendsList.setLayout(new BoxLayout(friendsList, BoxLayout.Y_AXIS));
		
		JScrollPane friendsListScroll = new JScrollPane(friendsList);
		GridBagConstraints gbc_friendsListScroll = new GridBagConstraints();
		gbc_friendsListScroll.fill = GridBagConstraints.VERTICAL;
		gbc_friendsListScroll.insets = new Insets(0, 0, 5, 5);
		gbc_friendsListScroll.anchor = GridBagConstraints.WEST;
		gbc_friendsListScroll.gridx = 0;
		gbc_friendsListScroll.gridy = 1;
		friendsListScroll.setPreferredSize(new Dimension(150, 100));
        friendsListScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(friendsListScroll, gbc_friendsListScroll);
		
		JTextField friendsTitle = new JTextField("Friends:");
		friendsTitle.setEditable(false);
		friendsList.add(friendsTitle);
		JButton temp;
		for(int i = 0; i < friends.size();i++){
			final int index = i;
			temp = new JButton(friends.get(index));
			temp.addActionListener(new
			         ActionListener()
			         {
			            public void actionPerformed(ActionEvent event)
			            {
			               controller.viewProfile(user.getFriends().get(index));
			            }
			         });
			friendsList.add(temp);
		}
		
		
		
		JPanel userWall = new JPanel();
		userWall.setToolTipText("Add jpanel posts");
		GridBagConstraints gbc_userWall = new GridBagConstraints();
		gbc_userWall.insets = new Insets(0, 0, 5, 0);
		gbc_userWall.fill = GridBagConstraints.BOTH;
		gbc_userWall.gridx = 2;
		gbc_userWall.gridy = 1;
		frame.getContentPane().add(userWall, gbc_userWall);
		userWall.setLayout(new BoxLayout(userWall, BoxLayout.X_AXIS));
		
		JScrollBar scrollBar_2 = new JScrollBar();
		userWall.add(scrollBar_2);
		
		JPanel newsFeed = new JPanel();
		newsFeed.setToolTipText("ADD special post jpanels");
		GridBagConstraints gbc_newsFeed = new GridBagConstraints();
		gbc_newsFeed.fill = GridBagConstraints.BOTH;
		gbc_newsFeed.gridx = 2;
		gbc_newsFeed.gridy = 2;
		frame.getContentPane().add(newsFeed, gbc_newsFeed);
		newsFeed.setLayout(new BoxLayout(newsFeed, BoxLayout.X_AXIS));
		
		JScrollBar scrollBar_1 = new JScrollBar();
		newsFeed.add(scrollBar_1);
		
		frame.pack();
		frame.setVisible(true);
		/*
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
		
		
		*/
	}
}
