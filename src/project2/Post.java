package project2;

import java.util.*;

/**
 * A post by a user.
 * @author jterrito
 *
 */
public class Post implements Comparable<Post>{
   private String username;
   private Long time;
   private String post;
   private ArrayList<String> likes = new ArrayList<String>();
   
   /**
    * Constructs post with the given user and text, sets post time based of System.currentTimeMillis().
    * @param user
    * @param post
    */
   public Post(User user, String post) {
	   this.post = post;
	   this.username = user.getUsername();
	   this.time = System.currentTimeMillis();
   }
   
   /**
    * Constructs post for the given user from text that contains time, likes, and the text. 
    * @param username User that generated the post.
    * @param info Line of text containing the time, likes, and text of the post.
    */
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
   
   /**
    * Stores post as a string.
    */
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
}
