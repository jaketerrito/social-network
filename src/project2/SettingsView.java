package project2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

//exit and deactivate button
public class SettingsView implements View{
	private JFrame frame;
	private JTextField txtOldPassword;
	private JTextField txtNewPassword;
	private JTextField txtConfirmNewPassword;
	private JTextField txtUpdatesInformation;
	private JTextField txtPassFailMessage;
	private JTextField txtNewUsername;
	private JTextField txtIncorrectPassword;
	private JTextField textUsernameInput;
	private JTextField txtUserFailMessage;
	private JTextField txtNewImage;
	private JTextField txtImageInput;
	private JButton btnChooseFile;
	private JTextField txtFailMessage;
	private JPasswordField newPasswordField;
	private JPasswordField confirmPasswordField;
	private JTextField textNewName;
	private JTextField textNewNameInput;
	private JTextField textNameFailMessage;
	private JButton btnSaveUpdates;
	private JPasswordField oldPasswordField;
	private JButton btnBack;
	private JButton btnDeactivateAccount;
	private Controller controller;
	private User user;
	
	public SettingsView(User user,Controller controller) {
		this.controller = controller;
		this.user = user;
	}
	public void close() {
		frame.dispose();
	}
	public void draw() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		txtOldPassword = new JTextField();
		txtOldPassword.setEditable(false);
		txtOldPassword.setText("Old Password:");
		txtOldPassword.setColumns(10);
		
		txtNewPassword = new JTextField();
		txtNewPassword.setEditable(false);
		txtNewPassword.setText("New Password:");
		txtNewPassword.setColumns(10);
		
		txtConfirmNewPassword = new JTextField();
		txtConfirmNewPassword.setEditable(false);
		txtConfirmNewPassword.setText("Confirm Password:");
		txtConfirmNewPassword.setColumns(10);
		
		txtUpdatesInformation = new JTextField();
		txtUpdatesInformation.setHorizontalAlignment(SwingConstants.CENTER);
		txtUpdatesInformation.setEditable(false);
		txtUpdatesInformation.setText("Update "+ user.getName() + "'s Information");
		txtUpdatesInformation.setColumns(10);
		
		txtPassFailMessage = new JTextField();
		txtPassFailMessage.setEditable(false);
		txtPassFailMessage.setText("");
		txtPassFailMessage.setColumns(10);
		
		txtNewUsername = new JTextField();
		txtNewUsername.setText("New Username:");
		txtNewUsername.setEditable(false);
		txtNewUsername.setColumns(10);
		
		txtIncorrectPassword = new JTextField();
		txtIncorrectPassword.setEditable(false);
		txtIncorrectPassword.setText("");
		txtIncorrectPassword.setColumns(10);
		
		textUsernameInput = new JTextField();
		textUsernameInput.setColumns(10);
		
		txtUserFailMessage = new JTextField();
		txtUserFailMessage.setEditable(false);
		txtUserFailMessage.setText("");
		txtUserFailMessage.setColumns(10);
		
		txtNewImage = new JTextField();
		txtNewImage.setEditable(false);
		txtNewImage.setText("New Image:");
		txtNewImage.setColumns(10);
		
		txtImageInput = new JTextField();
		txtImageInput.setColumns(10);
		
		btnChooseFile = new JButton("Choose File");
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jFileChooser = new JFileChooser();
            	jFileChooser.setCurrentDirectory(new File("~/sbh"));
            	int result = jFileChooser.showOpenDialog(new JFrame());
            	if (result == JFileChooser.APPROVE_OPTION) {
            		File selectedFile = jFileChooser.getSelectedFile();
            		System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            		txtImageInput.setText(selectedFile.getAbsolutePath());
            	}
			}
		});
		
		txtFailMessage = new JTextField();
		txtFailMessage.setEditable(false);
		txtFailMessage.setText("");
		txtFailMessage.setColumns(10);
		
		newPasswordField = new JPasswordField();
		
		confirmPasswordField = new JPasswordField();
		
		textNewName = new JTextField();
		textNewName.setEditable(false);
		textNewName.setText("New Name:");
		textNewName.setColumns(10);
		
		textNewNameInput = new JTextField();
		textNewNameInput.setColumns(10);
		
		textNameFailMessage = new JTextField();
		textNameFailMessage.setText("");
		textNameFailMessage.setEditable(false);
		textNameFailMessage.setColumns(10);
		
		btnSaveUpdates = new JButton("Save Updates");
		btnSaveUpdates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//ALL THE ACTION! if empty do nothing 
				if(!user.isPassword(String.copyValueOf(oldPasswordField.getPassword()))) {
					txtIncorrectPassword.setText("Incorrect Password");
				} else {
					txtIncorrectPassword.setText("");
				}
				
				txtUserFailMessage.setText(controller.approveUsername(textUsernameInput.getText()));
				if(txtUserFailMessage.getText().equals("") && txtIncorrectPassword.getText().equals("")) {
					controller.changeUsername(user.getUsername(),textUsernameInput.getText());
				}
				txtPassFailMessage.setText(controller.approvePassword(String.copyValueOf(newPasswordField.getPassword())));
				if(!(String.copyValueOf(newPasswordField.getPassword())).equals(String.copyValueOf(confirmPasswordField.getPassword()))){
            		txtPassFailMessage.setText("Does not match.");
            	}
				if(txtPassFailMessage.getText().equals("") && txtIncorrectPassword.getText().equals("")) {
					controller.changePassword(user.getUsername(),String.copyValueOf(newPasswordField.getPassword()));
				}
				
				if(textNewNameInput.getText().equals("")) {
					textNameFailMessage.setText("Need name");
				} else if (txtIncorrectPassword.getText().equals("")){
					controller.changeName(user.getUsername(),textNewNameInput.getText());
				}
				
				
				txtFailMessage.setText(controller.approveImage(txtImageInput.getText()));
				if(txtFailMessage.getText().equals("") && txtIncorrectPassword.getText().equals("")) {
					controller.changeImage(txtImageInput.getText());
				}
			}
		});
		
		
		oldPasswordField = new JPasswordField();
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.home();
			}
		});
		
		btnDeactivateAccount = new JButton("Deactivate Account");
		btnDeactivateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!user.isPassword(String.copyValueOf(oldPasswordField.getPassword()))) {
					txtIncorrectPassword.setText("Incorrect Password");
				} else {
					controller.deactivate();
				}
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtUpdatesInformation, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtOldPassword, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(oldPasswordField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
									.addGap(11)
									.addComponent(txtIncorrectPassword, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
									.addGap(12)))
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(textNewName, Alignment.LEADING)
								.addComponent(txtNewUsername, Alignment.LEADING)
								.addComponent(txtNewPassword, Alignment.LEADING)
								.addComponent(txtConfirmNewPassword, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(confirmPasswordField)
										.addComponent(newPasswordField)
										.addComponent(textUsernameInput, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(txtUserFailMessage, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED))
										.addComponent(txtPassFailMessage, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(textNewNameInput, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textNameFailMessage, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))
							.addGap(13))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtNewImage, 101, 101, 101)
								.addComponent(btnBack))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtImageInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnChooseFile)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnSaveUpdates)
								.addComponent(txtFailMessage, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
							.addContainerGap())))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(143)
					.addComponent(btnDeactivateAccount)
					.addContainerGap(164, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtUpdatesInformation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtOldPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtIncorrectPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(oldPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addComponent(btnDeactivateAccount)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNewUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textUsernameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtUserFailMessage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNewPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(newPasswordField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtConfirmNewPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(confirmPasswordField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPassFailMessage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textNewName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textNewNameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textNameFailMessage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNewImage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtImageInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnChooseFile)
						.addComponent(txtFailMessage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(3)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSaveUpdates)
						.addComponent(btnBack))
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
		frame.setVisible(true);
	}
}
