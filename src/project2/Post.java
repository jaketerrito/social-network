package project2;

import java.util.*;
public class Post implements Comparable<Post>{
   private String username;
   private Long time;
   private String post;
   private ArrayList<String> likes = new ArrayList<String>();
   public Post(){} //for testing
   
   //Construct new post
   public Post(User user, String post) {
	   this.post = post;
	   this.username = user.getUsername();
	   this.time = System.currentTimeMillis();
   }
   
   //construct from text file
   public Post(String username, String info){
      Scanner sc = new Scanner(info);
      this.username = username;
      this.time = sc.nextLong();
      Scanner users = new Scanner(sc.next());
      users.useDelimiter(",");
      while(users.hasNext()){
         likes.add(users.next());
      }
      users.close();
      sc.useDelimiter("\\n");
      this.post = sc.next().substring(1);
      sc.close();
   }
   
   public long getTime() {
	   return time;
   }
   
   public String getUsername() {
	   return username;
   }
   
   public String getText() {
	   return post;
   }
   
   public ArrayList<String> getLikes(){
	   return likes;
   }
   
   public void removeLike(String username) {
	   likes.remove(username);
   }
   
   public void addLike(String username) {
	   if(likes.contains(username)) {
		   return;
	   }
	   likes.add(username);
   }
   
   @Override
   public int compareTo(Post other) {
	   return (int) (other.getTime() - time);
   }
   
   public String toString(){
      StringBuilder s = new StringBuilder();
      s.append(time);
      s.append(" ,");
      for(String like: likes){
         s.append(like + ",");
      }
      s.append(" " + post);
      return s.toString();
   }
}
