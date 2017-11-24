package project2;

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

public class ProfileViewA extends JFrame {

	private JPanel contentPane;
	private JTextField txtFriendsList;
	private JTextField txtWall;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfileViewA frame = new ProfileViewA();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProfileViewA(String viewer,User user,ArrayList<String> friendsNames, ArrayList<Post> friendsPosts, Controller controller) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 896, 611);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
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
		JScrollPane wallScroll = new JScrollPane(userWall);
		wallScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		wallScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		PostPanel tempPanel;
		if(viewer != null) {
			for(Post post: user.getPosts()){
				tempPanel = new PostPanel(user, post, friendsNames,controller);
				userWall.add(tempPanel);
			}
		} else {
			for(Post post: friendsPosts){
				tempPanel = new PostPanel(user, post, friendsNames,controller);
				userWall.add(tempPanel);
			}
		}
		
		MenuBar menuBar = new MenuBar();
		
		JButton btnAddFriend = new JButton("Add Friend");
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
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
	}
}
