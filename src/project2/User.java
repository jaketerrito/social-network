package project2;

import java.io.*;
import java.util.*;

public class User{
   private ArrayList<String> friends = new ArrayList<String>();
   private ArrayList<Post> posts = new ArrayList<Post>();
   private String name;
   private String username; //no spaces or , etc
   private String password;
   private String imageLocation; //this is probably the wrong type.

   public User(){} //for testing
   
   public String getUsername() {
	   return username;
   }
   
   public String getName() {
	   return name;
   }
   
   public void removeFriend(String friendName) {
	   friends.remove(friendName);
	   for(Post post: posts) {
			post.removeLike(friendName);
		}
   }
   
   public void addFriend(String friendName) {
	   if(friends.contains(friendName)) {
		   return;
	   }
	   friends.add(friendName);
   }
   
   public ArrayList<String> getFriends(){
	   return friends;
   }
   
   public ArrayList<Post> getPosts(){
	   return posts;
   }
   
   public void post(String text) {
	   posts.add(new Post(this,text));
	   Collections.sort(posts);
   }
   
   public void like(Long time, String liker) {
	   for(Post post: posts) {
		   if(post.getTime() == time) {
			   post.addLike(liker);
			   return;
		   }
	   }
   }
   
   public boolean isPassword(String guess) {
	   return password.equals(guess);
   }
   
   public void setImage(String imageLocation) {
	   this.imageLocation = imageLocation;
   }
   
   public User(String username, String password, String name, String imageLocation) {
	   this.username = username;
	   this.password = password;
	   this.name = name;
	   this.imageLocation = imageLocation;
   }
   
   public User(File file){
      try {
          Scanner line;
    	  BufferedReader in = new BufferedReader(new FileReader(file));
    	  line = new Scanner(in.readLine());
    	  line.next(); //username:
    	  username = line.next(); //should be checking earlier to make sure
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
}
