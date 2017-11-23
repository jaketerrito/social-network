package project2;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import java.text.*;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
public class PostPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTime;
	
	/**
	 * Create the panel.
	 */
	public PostPanel(User user,Post post, ArrayList<String> friendsNames,Controller controller) {
		setBackground(new Color(192, 192, 192));
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, null));
		
		JTextArea txtrPostText = new JTextArea();
		txtrPostText.setWrapStyleWord(true);
		txtrPostText.setLineWrap(true);
		txtrPostText.setEditable(false);
		txtrPostText.setText(post.getText());
		
		txtTime = new JTextField();
		txtTime.setBackground(new Color(192, 192, 192));
		txtTime.setEditable(false);
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
	    Date resultdate = new Date(post.getTime());
		txtTime.setText(sdf.format(resultdate));
		txtTime.setColumns(10);
		
		JButton btnLikes = new JButton("Like... " + post.getLikes().size() + " others like this");
		StringBuilder s = new StringBuilder();
		int i = 1;
		ArrayList<String> likes = post.getLikes();
	    for(String like: likes){
	       if(likes.size() == 1) {
	    	   s.append(like);
	       }else if(i == likes.size()) {
	    	   s.append(" and " + like);
	       } else {
	    	   s.append(like + ",");
	       }
	       i++;
	    }
		btnLikes.setToolTipText(s.toString());
		btnLikes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.like(post.getUsername(),post.getTime());
			}
		});
		
		JButton btnUser;
		if(user.getUsername().equals(post.getUsername())) {
			btnUser = new JButton(user.getUsername());
		}else {
			btnUser = new JButton(friendsNames.get(user.getFriends().indexOf(post.getUsername())));
		}
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user.getUsername().equals(post.getUsername())) {
					controller.viewProfile(user.getUsername());
				}else {
					controller.viewProfile(user.getFriends().get(user.getFriends().indexOf(post.getUsername())));
				}
			}
		});
		btnUser.setBackground(new Color(255, 255, 255));
		btnUser.setForeground(new Color(0, 0, 0));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnUser, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtTime, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtrPostText, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
						.addComponent(btnLikes, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnUser)
						.addComponent(txtTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtrPostText, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLikes)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);


	}

}
