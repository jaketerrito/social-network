package project2;


import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.*;
public class RegisterView extends JPanel{
	private Controller controller;
	private JTextField usernameInput;
	private JTextField usernameFail;
	private JTextField txtUsername_1;
	private JTextField txtPassword;
	private JTextField txtConfirmPassword;
	private JPasswordField passwordInput;
	private JPasswordField passwordConfirmationInput;
	private JTextField passwordConfirmationFail;
	private JTextField txtFullName;
	private JTextField nameInput;
	private JTextField txtImage;
	private JTextField imageInput;
	private JButton fileSelectorButton;
	private JButton registerButton;
	private JTextField nameFail;
	/**
	 * @wbp.parser.entryPoint
	 */
	public RegisterView (Controller controller) {
		this.controller = controller;
/*		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		txtUsername_1 = new JTextField();
		txtUsername_1.setEditable(false);
		txtUsername_1.setText("Username:");
		GridBagConstraints gbc_txtUsername_1 = new GridBagConstraints();
		gbc_txtUsername_1.anchor = GridBagConstraints.EAST;
		gbc_txtUsername_1.insets = new Insets(0, 0, 5, 5);
		gbc_txtUsername_1.gridx = 0;
		gbc_txtUsername_1.gridy = 0;
		add(txtUsername_1, gbc_txtUsername_1);
		txtUsername_1.setColumns(10);
		
		usernameInput = new JTextField();
		GridBagConstraints gbc_usernameInput = new GridBagConstraints();
		gbc_usernameInput.insets = new Insets(0, 0, 5, 5);
		gbc_usernameInput.anchor = GridBagConstraints.WEST;
		gbc_usernameInput.gridx = 1;
		gbc_usernameInput.gridy = 0;
		add(usernameInput, gbc_usernameInput);
		usernameInput.setColumns(20);
		
		usernameFail = new JTextField();
		usernameFail.setEditable(false);
		GridBagConstraints gbc_usernameFail = new GridBagConstraints();
		gbc_usernameFail.anchor = GridBagConstraints.WEST;
		gbc_usernameFail.insets = new Insets(0, 0, 5, 5);
		gbc_usernameFail.gridx = 2;
		gbc_usernameFail.gridy = 0;
		add(usernameFail, gbc_usernameFail);
		
		txtPassword = new JTextField();
		txtPassword.setEditable(false);
		txtPassword.setText("Password:");
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.anchor = GridBagConstraints.EAST;
		gbc_txtPassword.insets = new Insets(0, 0, 5, 5);
		gbc_txtPassword.gridx = 0;
		gbc_txtPassword.gridy = 1;
		add(txtPassword, gbc_txtPassword);
		txtPassword.setColumns(10);
		
		passwordInput = new JPasswordField();
		passwordInput.setColumns(20);
		GridBagConstraints gbc_passwordInput = new GridBagConstraints();
		gbc_passwordInput.anchor = GridBagConstraints.WEST;
		gbc_passwordInput.insets = new Insets(0, 0, 5, 5);
		gbc_passwordInput.gridx = 1;
		gbc_passwordInput.gridy = 1;
		add(passwordInput, gbc_passwordInput);
		
		txtConfirmPassword = new JTextField();
		txtConfirmPassword.setEditable(false);
		txtConfirmPassword.setText("Confirm:");
		GridBagConstraints gbc_txtConfirmPassword = new GridBagConstraints();
		gbc_txtConfirmPassword.anchor = GridBagConstraints.EAST;
		gbc_txtConfirmPassword.insets = new Insets(0, 0, 5, 5);
		gbc_txtConfirmPassword.gridx = 0;
		gbc_txtConfirmPassword.gridy = 2;
		add(txtConfirmPassword, gbc_txtConfirmPassword);
		txtConfirmPassword.setColumns(10);
		
		passwordConfirmationInput = new JPasswordField();
		passwordConfirmationInput.setColumns(20);
		GridBagConstraints gbc_passwordConfirmationInput = new GridBagConstraints();
		gbc_passwordConfirmationInput.anchor = GridBagConstraints.WEST;
		gbc_passwordConfirmationInput.insets = new Insets(0, 0, 5, 5);
		gbc_passwordConfirmationInput.gridx = 1;
		gbc_passwordConfirmationInput.gridy = 2;
		add(passwordConfirmationInput, gbc_passwordConfirmationInput);
		
		passwordConfirmationFail = new JTextField();
		passwordConfirmationFail.setEditable(false);
		GridBagConstraints gbc_passwordConfirmationFail = new GridBagConstraints();
		gbc_passwordConfirmationFail.anchor = GridBagConstraints.WEST;
		gbc_passwordConfirmationFail.insets = new Insets(0, 0, 5, 5);
		gbc_passwordConfirmationFail.gridx = 2;
		gbc_passwordConfirmationFail.gridy = 2;
		add(passwordConfirmationFail, gbc_passwordConfirmationFail);
		
		
		txtFullName = new JTextField();
		txtFullName.setEditable(false);
		txtFullName.setText("Full Name:");
		GridBagConstraints gbc_txtFullName = new GridBagConstraints();
		gbc_txtFullName.anchor = GridBagConstraints.EAST;
		gbc_txtFullName.insets = new Insets(0, 0, 5, 5);
		gbc_txtFullName.gridx = 0;
		gbc_txtFullName.gridy = 3;
		add(txtFullName, gbc_txtFullName);
		txtFullName.setColumns(10);
		
		nameFail = new JTextField();
		nameFail.setEditable(false);
		GridBagConstraints gbc_nameFail = new GridBagConstraints();
		gbc_nameFail.anchor = GridBagConstraints.WEST;
		gbc_nameFail.insets = new Insets(0, 0, 5, 5);
		gbc_nameFail.gridx = 2;
		gbc_nameFail.gridy = 3;
		add(nameFail, gbc_nameFail);
		
		nameInput = new JTextField();
		GridBagConstraints gbc_nameInput = new GridBagConstraints();
		gbc_nameInput.insets = new Insets(0, 0, 5, 5);
		gbc_nameInput.anchor = GridBagConstraints.WEST;
		gbc_nameInput.gridx = 1;
		gbc_nameInput.gridy = 3;
		add(nameInput, gbc_nameInput);
		nameInput.setColumns(20);
		
		txtImage = new JTextField();
		txtImage.setEditable(false);
		txtImage.setText("Image:");
		GridBagConstraints gbc_txtImage = new GridBagConstraints();
		gbc_txtImage.anchor = GridBagConstraints.EAST;
		gbc_txtImage.insets = new Insets(0, 0, 0, 5);
		gbc_txtImage.gridx = 0;
		gbc_txtImage.gridy = 4;
		add(txtImage, gbc_txtImage);
		txtImage.setColumns(10);
		
		imageInput = new JTextField();
		GridBagConstraints gbc_imageInput = new GridBagConstraints();
		gbc_imageInput.insets = new Insets(0, 0, 0, 5);
		gbc_imageInput.anchor = GridBagConstraints.WEST;
		gbc_imageInput.gridx = 1;
		gbc_imageInput.gridy = 4;
		add(imageInput, gbc_imageInput);
		imageInput.setColumns(20);
		
		fileSelectorButton = new JButton("Choose Image");
		GridBagConstraints gbc_fileSelectorButton = new GridBagConstraints();
		gbc_fileSelectorButton.anchor = GridBagConstraints.WEST;
		gbc_fileSelectorButton.gridx = 2;
		gbc_fileSelectorButton.gridy = 4;
		add(fileSelectorButton, gbc_fileSelectorButton);
		fileSelectorButton.addActionListener(new
		         ActionListener()
		         {
		            public void actionPerformed(ActionEvent event)
		            {
		               
		            	JFileChooser jFileChooser = new JFileChooser();
		            	jFileChooser.setCurrentDirectory(new File("~/sbh"));
		            	int result = jFileChooser.showOpenDialog(new JFrame());
		            	if (result == JFileChooser.APPROVE_OPTION) {
		            		File selectedFile = jFileChooser.getSelectedFile();
		            		System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		            		imageInput.setText(selectedFile.getAbsolutePath());
		            	}
		            	controller.buttonClick();
		            }
		         });
		
		registerButton = new JButton("  Register  ");
		GridBagConstraints gbc_registerButton = new GridBagConstraints();
		gbc_registerButton.anchor = GridBagConstraints.CENTER;
		gbc_registerButton.gridx = 1;
		gbc_registerButton.gridy = 5;
		add(registerButton, gbc_registerButton);
		registerButton.addActionListener(new
		         ActionListener()
		         {
		            public void actionPerformed(ActionEvent event)
		            {
		            	if(nameInput.getText().equals("")) {
		            		nameFail.setText("Need a name");
		            	}else {
		            		nameFail.setText("");
		            	}
		            	
		            	String usernameProblem = controller.approveUsername(usernameInput.getText());
		            	usernameFail.setText(usernameProblem);

		            	String passwordProblem = controller.approvePassword(String.copyValueOf(passwordInput.getPassword()));
		            	passwordConfirmationFail.setText(passwordProblem);
		            	if(!(String.copyValueOf(passwordInput.getPassword())).equals(String.copyValueOf(passwordConfirmationInput.getPassword()))){
		            		passwordConfirmationFail.setText("Does not match.");
		            	}
		            	
		            	if(passwordConfirmationFail.getText().equals("") && usernameFail.getText().equals("") && !(nameInput.getText().equals(""))) {
		            		controller.registerUser(usernameInput.getText(),(String.copyValueOf(passwordInput.getPassword())),nameInput.getText(),imageInput.getText());
		            	}
		            	
		            	passwordInput.setText("");
	    				passwordConfirmationInput.setText("");
	    				controller.buttonClick();
		            }
		         });
		
		
		
	}

}
