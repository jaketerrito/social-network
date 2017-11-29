package project2;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.util.*;
import java.text.*;
import java.awt.Dimension;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
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
	public PostPanel(User user,Post post,HashMap<String,User> users,Controller controller) {
		setPreferredSize(new Dimension(500,180));
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
	       like = users.get(like).getName();
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
			btnUser = new JButton(user.getName());
		}else {
			btnUser = new JButton(users.get(post.getUsername()).getName());
		}
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(users.get(post.getUsername()).getImage()));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(20,20,Image.SCALE_SMOOTH);
		btnUser.setIcon(new ImageIcon(dimg));
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user.getUsername().equals(post.getUsername())) {
					controller.viewProfile(user.getUsername());
				}else {
					controller.viewProfile(post.getUsername());
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
							.addComponent(btnUser, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtTime, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtrPostText, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addComponent(btnLikes, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE))
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
					.addContainerGap(12, Short.MAX_VALUE))
		);
		setLayout(groupLayout);


	}

}
