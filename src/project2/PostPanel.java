package project2;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JTextArea;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import java.text.*;
import java.awt.Dimension;
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
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton btnUser;
		if(user.getUsername().equals(post.getUsername())) {
			btnUser = new JButton(user.getName());
		} else {
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
		GridBagConstraints gbc_btnUser = new GridBagConstraints();
		gbc_btnUser.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnUser.insets = new Insets(0, 0, 5, 5);
		gbc_btnUser.gridx = 0;
		gbc_btnUser.gridy = 0;
		add(btnUser, gbc_btnUser);
		
		JTextArea txtrPostText = new JTextArea();
		//txtrPostText.setPreferredSize(new Dimension(30,30));
		txtrPostText.setEditable(false);
		txtrPostText.setText(post.getText());
		GridBagConstraints gbc_txtrPostText = new GridBagConstraints();
		gbc_txtrPostText.insets = new Insets(0, 0, 5, 0);
		gbc_txtrPostText.anchor = GridBagConstraints.NORTHWEST;
		gbc_txtrPostText.fill = GridBagConstraints.BOTH;
		gbc_txtrPostText.gridx = 1;
		gbc_txtrPostText.gridy = 0;
		add(txtrPostText, gbc_txtrPostText);
		
		txtTime = new JTextField();
		txtTime.setEditable(false);
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
	    Date resultdate = new Date(post.getTime());
		txtTime.setText(sdf.format(resultdate));
		GridBagConstraints gbc_txtTime = new GridBagConstraints();
		gbc_txtTime.anchor = GridBagConstraints.EAST;
		gbc_txtTime.insets = new Insets(0, 0, 0, 5);
		gbc_txtTime.gridx = 0;
		gbc_txtTime.gridy = 1;
		add(txtTime, gbc_txtTime);
		txtTime.setColumns(10);
		
		JButton btnLikes = new JButton("Like... " + post.getLikes().size() + " others like this");
		StringBuilder s = new StringBuilder();
	    for(String like: post.getLikes()){
	       s.append(like + ",");
	    }
		btnLikes.setToolTipText(s.toString());
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.like(post.getUsername(),post.getTime());
			}
		});
		GridBagConstraints gbc_btnLikes = new GridBagConstraints();
		gbc_btnLikes.gridx = 1;
		gbc_btnLikes.gridy = 1;
		add(btnLikes, gbc_btnLikes);

	}

}
