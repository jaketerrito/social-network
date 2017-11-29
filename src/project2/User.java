package project2;

import java.io.*;
import java.util.*;

/**
 * A user and all their information.
 * @author jterrito
 * 
 */
public class User{
   private ArrayList<String> friends = new ArrayList<String>();
   private ArrayList<Post> posts = new ArrayList<Post>();
   private String name;
   private String username;
   private String password;
   private String imageLocation;
   
   /**
    * Creates user object with given information.
    * @param username 
    * @param password
    * @param name
    * @param imageLocation The path of the user's profile image.
    */
   public User(String username, String password, String name, String imageLocation) {
	   this.username = username;
	   this.password = password;
	   this.name = name;
	   this.imageLocation = imageLocation;
   }
   
   /**
    * Creates user object from file.
    * @param file
    */
   public User(File file){
	      try {
	          Scanner line;
	    	  BufferedReader in = new BufferedReader(new FileReader(file));
	    	  line = new Scanner(in.readLine());
	    	  line.next(); //username:
	    	  username = line.next(); 
	    	  line.close();
	    	  line = new Scanner(in.readLine());
	    	  line.next(); //password:
	    	  password = line.next();
	    	  line.close();
	    	  line = new Scanner(in.readLine());
	    	  line.next(); //name:
	    	  line.useDelimiter("\\n");
	          name = line.next().substring(1);
	    	  line.close();
	    	  line = new Scanner(in.readLine());
	    	  line.next(); //image:
	    	  imageLocation = line.next();
	    	  line.close();
	    	  line = new Scanner(in.readLine());
	    	  line.next(); //friends:
	    	  line.useDelimiter(",| ");
	    	  while(line.hasNext()){
	    		  friends.add(line.next());
	    	  }
	    	  line.close();
	    	  in.readLine(); //posts:
	    	  while(in.ready()){
	    		  posts.add(new Post(username,in.readLine()));
	    	  }
	    	  in.close();
	      }catch(IOException e) {
	    	  System.out.println(e.getMessage());
	      }
	   }
   
   /**
    * Writes the user object into a file.
    * @param root Folder to store the file.
    */
   public void toFile(String root){
	      PrintWriter writer;
		   try {
	    	  writer = new PrintWriter(new File(root + "/" + username + ".txt"));
	      }catch(FileNotFoundException e) {
	    	  System.out.println(e.getMessage());
	    	  return;
	      }
	      writer.println("username: " + username);
	      writer.println("password: " + password);
	      writer.println("name: " + name);
	      writer.println("image: " + imageLocation);
	      writer.print("friends: ");
	      for(String friend: friends){
	         writer.print(friend + ",");
	      }
	      writer.println();
	      writer.println("posts:");
	      for(Post post: posts){
	         writer.println(post.toString());
	      }
	      writer.flush();
	      writer.close();
	   }
   
   /**
    * Removes any traces of a friend from user's posts and friend list.
    * @param friendName
    */
   public void removeFriend(String friendName) {
	   friends.remove(friendName);
	   for(Post post: posts) {
			post.removeLike(friendName);
		}
   }
   
   /**
    * Adds a new friend to user's friends list, ignoring duplicates.
    * @param friendName
    */
   public void addFriend(String friendName) {
	   if(friends.contains(friendName)) {
		   return;
	   }
	   friends.add(friendName);
   }
   
   /**
    * Changes username of this user or a friend of this user, within this users posts and friends list.
    * @param oldName The original username.
    * @param newName The new username.
    */
   public void changeUsername(String oldName, String newName) {
	   if(oldName.equals(username)) {
		   username = newName;
	   }
	   if(friends.contains(oldName)) {
		   friends.remove(oldName);
		   friends.add(newName);
	   }
	   for(Post post: posts) {
		   if(post.getLikes().contains(oldName)) {
			   post.removeLike(oldName);
			   post.addLike(newName);
		   }
		}
   }
 
   /**
    * Generates new post with given string.
    * @param text
    */
   public void post(String text) {
	   posts.add(new Post(this,text));
   }
   
   /**
    * Takes the post from the given time and add's the username, 'liker', to the list of likes.
    * @param time
    * @param liker
    */
   public void like(Long time, String liker) {
	   for(Post post: posts) {
		   if(post.getTime() == time) {
			   post.addLike(liker);
			   return;
		   }
	   }
   }
   
   /**
    * Returns whether or not the guess is this users password.
    * @param guess
    * @return
    */
   public boolean isPassword(String guess) {
	   return password.equals(guess);
   }
   
   public void setPassword(String password) {
	   this.password = password;
   }
   
   public void setName(String name) {
	   this.name = name;
   }
   
   public void setImage(String imageLocation) {
	   this.imageLocation = imageLocation;
   }
   
   /*************
    * Get Functions
    ************/
   
   public String getUsername() {
	   return username;
   }
   
   public String getName() {
	   return name;
   }
   
   public ArrayList<String> getFriends(){
	   return friends;
   }
   
   public ArrayList<Post> getPosts(){
	   return posts;
   }
   
   public String getImage() {
	   return imageLocation;
   }
}
