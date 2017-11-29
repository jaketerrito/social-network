package project2;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * The model that handles all of the data for the network. Is a singleton.
 * @author jterrito
 *
 */
public class Model {
	private static Model modelInstance;
	private String usersDir;
	private HashMap<String,User> userList;
	
	/**
	 * For use by getInstance.
	 * @param usersDir
	 */
	private Model(String usersDir) {
		this.usersDir = usersDir;
		readUsers();
	}
	
	/**
	 * Initializes the model from users in given directory.
	 * @param usersDir Directory in which all user data is stored.
	 * @return
	 */
	public static Model getInstance(String usersDir) {
		if(modelInstance == null) {
			modelInstance = new Model(usersDir);
		}
		return modelInstance;
	}
	
	/**
	 * Generates the HashMap of users from each file in the users directory.
	 */
	public void readUsers() {
		User temp;
		userList = new HashMap<String,User>();
		for(File file: new File(usersDir).listFiles()){
			temp = new User(file);
	        userList.put(temp.getUsername(), temp);
		}
	}
	
	/**
	 * Generates and overwrites file to store each user.
	 */
	public void storeUsers() {
		for(String username:userList.keySet()) {
			userList.get(username).toFile(usersDir);
		}
	}
	
	/**
	 * Creates list of all posts by given user's friends.
	 * @param currentUsername
	 * @return
	 */
	public ArrayList<Post> getFriendsPosts(String currentUsername){
		ArrayList<Post> posts = new ArrayList<Post>();
		for(String friendname:userList.get(currentUsername).getFriends()) {
			if(userList.get(friendname) == null) {
				System.out.println("Error: invalid friend");
				continue;
			}
			for(Post post:userList.get(friendname).getPosts()) {
				posts.add(post);
			}
		}
		return posts;
	}
	
	public HashMap<String,User> getUsers() {
		return userList;
	}
	
	public User getUser(String user) {
		return userList.get(user);
	}
	
	/**
	 * Creates new post from String provided by currentUser.
	 * @param currentUser
	 * @param post
	 */
	public void post(String currentUser, String post){
		User user = getUser(currentUser);
		user.post(post.replace("\n", ""));
	}
	
	/**
	 * Verifies given username and password combination.
	 * @param username
	 * @param password
	 * @return True if this is a valid combination.
	 */
	public boolean login(String username, String password) {
		User temp = getUser(username);
		if(temp == null) {
			return false;
		}
		if(temp.isPassword(password)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Generates list of users whose name contains the search term.
	 * @param term
	 * @return
	 */
	public ArrayList<User> search(String term){
		ArrayList<User> matches = new ArrayList<User>();
		for(String username: userList.keySet()) {
			if((userList.get(username)).getName().toLowerCase().contains(term.toLowerCase())) {
					matches.add(userList.get(username));
			}
		}
		return matches;
	}
	
	/**
	 * Adds new user from given information.
	 * @param username
	 * @param password
	 * @param name
	 * @param imageLocation
	 */
	public void register(String username, String password, String name, String imageLocation){
		if(imageLocation.equals("")) {
			imageLocation = "defaultPic.png";
		}
		userList.put(username,new User(username,password,name,imageLocation));
		userList.get(username).toFile(usersDir);
	}
	
	/**
	 * Determines whether provided username is valid.
	 * @param username
	 * @return Error in the username, or "" if there is no error.
	 */
	public String approveUsername(String username) {
		if(username.equals("")) {
			return "Need username";
		}
		if(username.contains(" ") || username.contains("\n") || username.contains(",")) {
			return "No special characters";
		}
		if(getUser(username) != null) {
			return "Username taken";
		}
		return "";
	}
	
	/**
	 * Determines whether provided password is valid.
	 * @param password
	 * @return Error in the password, or "" if there is no error.
	 */
	public String approvePassword(String password) {
		if(password.equals("")) {
			return "Need password";
		}
		if(password.contains(" ") || password.contains("\n") || password.contains(",")) {
			return "No special characters";
		}
		return "";
	}
	
	/**
	 * Adds new friend to current user's friend list, and adds current user to friend's list as well.
	 * @param currentUser
	 * @param friendName
	 */
	public void addFriend(String currentUser, String friendName) {
		User user = getUser(currentUser);
		user.addFriend(friendName);
		user = getUser(friendName);
		user.addFriend(currentUser);
	}
	
	/**
	 * Removes friend from current user's friend list, and removes current user from friend's list as well.
	 * @param currentUser
	 * @param friendName
	 */
	public void removeFriend(String currentUser, String friendName) {
		User user = getUser(currentUser);
		user.removeFriend(friendName);
		user = getUser(friendName);
		user.removeFriend(currentUser);
	}
	
	/**
	 * Adds currentUser to list of likes for user's post at the given time.
	 * @param currentUser
	 * @param user
	 * @param time
	 */
	public void like(String currentUser, String user,Long time) {
		getUser(user).like(time,currentUser);
	}
	
	/**
	 * Totally deletes any trace of current user from files and all other user's friends lists and posts.
	 * @param currentUser
	 */
	public void deactivate(String currentUser) {
		try {
		    Files.delete(Paths.get(usersDir + "/" + currentUser + ".txt"));
		} catch (Exception x) {
			System.out.println(x.getMessage());
		}
		//delete any reference to this user in likes or friends
		for(String username:userList.keySet()) {
			userList.get(username).removeFriend(currentUser);
		}
		userList.remove(currentUser);
		storeUsers();
	}
	
	/**
	 * Changes username to newusername in information of every user.
	 * @param username Current username
	 * @param newusername New username that will replace the current one.
	 */
	public void changeUsername(String username,String newusername) {
		try {
		    Files.delete(Paths.get(usersDir + "/" + username + ".txt"));
		} catch (Exception x) {
			System.out.println(x.getMessage());
		}
		User temp = userList.get(username);
		for(String name:userList.keySet()) {
			userList.get(name).changeUsername(username,newusername);
		}
		userList.put(newusername, temp);
		userList.remove(username);
		for(String hmm:userList.keySet()) {
			System.out.println(hmm + " " + userList.get(hmm).getUsername());
		}
	}
	
	public void changeImage(String username, String imageLocation) {
		getUser(username).setImage(imageLocation);
	}
	
	public void changePassword(String username,String password) {
		userList.get(username).setPassword(password);
	}
	
	public void changeName(String username,String name) {
		userList.get(username).setName(name);
	}
}
