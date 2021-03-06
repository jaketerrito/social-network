package project2;

import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.util.*;
import java.awt.event.*;
/**
 * A panel that provides search bar, home button, settings button, refresh button, logout button, and ability to post.
 * @author jterrito
 *
 */
public class MenuBar extends JPanel {
	private JTextField txtSearch;
	private int clicks;

	public MenuBar(String viewer, User user, HashMap<String,User> users,Controller controller) {
		clicks = 0;
		txtSearch = new JTextField();
		txtSearch.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.search(txtSearch.getText());
			}
		});
		
		//Goes to home. If already at home, goes to current user's profile.
		JButton btnHome = new JButton("Home");
		if(viewer == null) {
			btnHome.setText(user.getName() + "'s Profile");
		}
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(viewer == null) {
					controller.viewProfile(user.getUsername());
				}else {
					controller.home();
				}
			}
		});
		
		JButton btnSettings = new JButton("Settings");
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.settings();
			}
		});
		
		JButton btnNewButton_1 = new JButton("Refresh\r\n");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.refresh();
			}
		});
		
		//Clears text on initial click. Posts text whe enter is hit.
		JTextArea txtrPostHere = new JTextArea();
		txtrPostHere.setText("Post to your wall");
		txtrPostHere.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	        	  if(clicks == 0) {
	        		  txtrPostHere.setText("");
	        	  }
	        	  clicks++;
	          } 
	    }); 
		txtrPostHere.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER && !(txtrPostHere.getText().equals(""))) {
					controller.post(txtrPostHere.getText());
				}
			}
		});

		//Posts on click if the user entered text.
		JButton btnPost = new JButton("Post");
		btnPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(txtrPostHere.getText().equals("")) && !(txtrPostHere.getText().equals("Post to your wall"))){
					controller.post(txtrPostHere.getText());
				}
			}
		});
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.logOut();
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnHome)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSettings)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLogOut)
					.addGap(22))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnPost, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
						.addComponent(txtrPostHere, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE))
					.addGap(54))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnHome)
						.addComponent(btnSettings)
						.addComponent(btnNewButton_1)
						.addComponent(btnLogOut))
					.addGap(18)
					.addComponent(txtrPostHere, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnPost)
					.addContainerGap(21, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
