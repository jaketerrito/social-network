package project2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

/**
 * A JPanel with list of results from user's search
 * @author jterrito
 *
 */
public class SearchView extends JPanel{

	private JTextField txtSearchForblah;


	/**
	 * Creates a JPanel with search results for the given term from a list of the matching users. 
	 * @param term
	 * @param matches List of users returned from model's search method.
	 * @param controller
	 */
	public SearchView(String term, ArrayList<User> matches, Controller controller) {
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//Create's scrollable list of results, all clickable to view their profile.
		JPanel matchList = new JPanel();
		matchList.setLayout(new BoxLayout(matchList, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(matchList);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JButton temp;
		BufferedImage img = null;
		for(User user : matches) {
			temp = new JButton(user.getName());
			temp.addActionListener(new
			         ActionListener()
			         {
			            public void actionPerformed(ActionEvent event)
			            {
			               controller.viewProfile(user.getUsername());
			            }
			         });
			try {
			    img = ImageIO.read(new File(user.getImage()));
			} catch (IOException e) {
			    e.printStackTrace();
			}
			Image dimg = img.getScaledInstance(20,20,Image.SCALE_SMOOTH);
			temp.setIcon(new ImageIcon(dimg));
			matchList.add(temp);
		}
		
		
		txtSearchForblah = new JTextField();
		txtSearchForblah.setEditable(false);
		txtSearchForblah.setText("Search for: \""+ term + "\"");
		txtSearchForblah.setColumns(10);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.home();
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(txtSearchForblah, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnHome, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtSearchForblah, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
						.addComponent(btnHome))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE))
		);
		setLayout(gl_contentPane);
	}
}
