package project2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class SearchView implements View{

	private JPanel contentPane;
	private JTextField txtSearchForblah;
	private JFrame frame;
	private String term;
	private HashMap<String,User> users;
	private ArrayList<String> matches;
	private Controller controller;
	
	public SearchView(String term, HashMap<String,User> users, ArrayList<String> matches, Controller controller) {
		this.users = users;
		this.term = term;
		this.matches = matches;
		this.controller = controller;
		frame = new JFrame();
	}
	
	public void close() {
		frame.dispose();
	}
		
	public void draw() {
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 452, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		
		JPanel matchList = new JPanel();
		matchList.setLayout(new BoxLayout(matchList, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPane = new JScrollPane(matchList);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JButton temp;
		for(String username : matches) {
			temp = new JButton(users.get(username).getName());
			temp.addActionListener(new
			         ActionListener()
			         {
			            public void actionPerformed(ActionEvent event)
			            {
			               controller.viewProfile(users.get(username).getUsername());
			            }
			         });
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
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
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
		contentPane.setLayout(gl_contentPane);
	}
}
