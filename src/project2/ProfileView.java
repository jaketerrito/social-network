package project2;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class ProfileView implements View{
	private User user;
	private ArrayList<Post> friendsPosts;
	private ArrayList<String> friendsNames;
	private JFrame frame;
	private Controller controller;
	private String viewer;
	
	public String getUser(){
		return user.getUsername();
	}
	
	public ProfileView(String viewer,User user,ArrayList<String> friendsNames, ArrayList<Post> friendsPosts, Controller controller) {
		this.user = user;
		this.friendsPosts = friendsPosts;
		this.friendsNames = friendsNames;
		this.controller = controller;
		this.viewer = viewer;
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
		gbc_friendsListScroll.fill = GridBagConstraints.BOTH;
		gbc_friendsListScroll.insets = new Insets(0, 0, 5, 5);
		gbc_friendsListScroll.anchor = GridBagConstraints.WEST;
		gbc_friendsListScroll.gridx = 0;
		gbc_friendsListScroll.gridy = 1;
		//friendsListScroll.setPreferredSize(new Dimension(150, 200));
        friendsListScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        friendsListScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		frame.getContentPane().add(friendsListScroll, gbc_friendsListScroll);
		
		JTextField friendsTitle = new JTextField("Friends:");
		friendsTitle.setEditable(false);

		friendsListScroll.setColumnHeaderView(friendsTitle);
		JButton temp;
		for(int i = 0; i < friendsNames.size();i++){
			final int index = i;
			temp = new JButton(friendsNames.get(index));
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
		userWall.setLayout(new BoxLayout(userWall, BoxLayout.Y_AXIS));
		
		JScrollPane userWallScroll = new JScrollPane(userWall);
		GridBagConstraints gbc_userWallScroll = new GridBagConstraints();
		gbc_userWallScroll.insets = new Insets(0, 0, 5, 0);
		gbc_userWallScroll.fill = GridBagConstraints.BOTH;
		gbc_userWallScroll.gridx = 2;
		gbc_userWallScroll.gridy = 1;
		frame.getContentPane().add(userWallScroll, gbc_userWallScroll);
		userWallScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		userWallScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		JTextField wallTitle = new JTextField("Wall:");
		wallTitle.setEditable(false);

		userWallScroll.setColumnHeaderView(wallTitle);
		PostPanel tempPanel;
		for(Post post: user.getPosts()){
			tempPanel = new PostPanel(user, post, friendsNames,controller);
			userWall.add(tempPanel);
		}
		
		if(viewer.equals(user.getUsername())) {
			JPanel newsFeed = new JPanel();
			newsFeed.setLayout(new BoxLayout(newsFeed, BoxLayout.Y_AXIS));
		
			JScrollPane newsFeedScroll = new JScrollPane(newsFeed);
			GridBagConstraints gbc_newsFeedScroll = new GridBagConstraints();
			gbc_newsFeedScroll.fill = GridBagConstraints.BOTH;
			gbc_newsFeedScroll.gridx = 2;
			gbc_newsFeedScroll.gridy = 2;
			frame.getContentPane().add(newsFeedScroll, gbc_newsFeedScroll);
			newsFeedScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			newsFeedScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
			JTextField feedTitle = new JTextField("News Feed:");
			feedTitle.setEditable(false);

			newsFeedScroll.setColumnHeaderView(feedTitle);
			for(Post post: friendsPosts){
				tempPanel = new PostPanel(user, post, friendsNames,controller);
				newsFeed.add(tempPanel);
			}
		}
		frame.pack();
		frame.setVisible(true);
	}
}
