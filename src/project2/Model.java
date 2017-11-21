package project2;

import java.io.*;
import java.nio.file.*;
import java.util.*;

//generate posts and users with a factory?
//generate pieces of view with a factory?

public class Model {
	private String usersDir;
	private String username;
	
	public Model(String usersDir) {
		this.usersDir = usersDir;
	}
	
	//makes list of users from database
	//should not read if toFile is happening or if a function is readingUsers and using that list to write files
	public HashMap<String,User> readUsers() {
		User temp;
		HashMap<String,User> userList = new HashMap<String,User>();
		for(File file: new File(usersDir).listFiles()){
			temp = new User(file);
	        userList.put(temp.getUsername(), temp);
		}
		return userList;
	}
	
	
	public void post(String post){
		User user = getUser(username);
		user.post(post);
		user.toFile(usersDir);
	}
	
	public ArrayList<Post> getFriendsPosts(){
		ArrayList<Post> posts = new ArrayList<Post>();
		HashMap<String,User> users = readUsers();
		User temp;
		for(String friendname:users.get(username).getFriends()) {
			temp = users.get(friendname);
			if(temp == null) {
				System.out.println("Error: invalid friend");
				continue;
			}
			for(Post post:temp.getPosts()) {
				posts.add(post);
			}
		}
		Collections.sort(posts);
		return posts;
	}
	
	public User getUser(String user) {
		return readUsers().get(user);
	}
	
	public boolean setUser(String username, String password) {
		this.username = username;
		User temp = getUser(username);
		if(temp == null) {
			return false;
		}
		if(temp.isPassword(password)) {
			return true;
		}else {
			temp = null;
		}
		return false;
	}
	
	public ArrayList<String> search(String sub){
		ArrayList<String> matches = new ArrayList<String>();
		HashMap<String,User> users = readUsers();
		for(String username: users.keySet()) {
			if((users.get(username)).getName().contains(sub)) {
					matches.add(username);
			}
		}
		return matches;
	}
	
	public void register(String username, String password, String name, String imageLocation){
		new User(username,password,name,imageLocation).toFile(usersDir);;
	}
	
	public void addFriend(String friendName) {
		User user = getUser(username);
		user.addFriend(friendName);
		user.toFile(usersDir);
		user = getUser(friendName);
		user.addFriend(username);
		user.toFile(usersDir);
	}
	
	public void like(String username,Long time) {
		User user = getUser(username);
		user.like(time,this.username);
		user.toFile(usersDir);
	}
	
	public void deactivate() {
		try {
		    Files.delete(Paths.get(usersDir + "/" + username + ".txt"));
		} catch (Exception x) {
			System.out.println(x.getMessage());
		}
		//delete any reference to this user in likes or friends
		HashMap<String,User> userList = readUsers();
		User temp;
		for(String username:userList.keySet()) {
			temp = userList.get(username);
			temp.removeFriend(this.username);
			temp.toFile(usersDir);
		}
	}
	
}
