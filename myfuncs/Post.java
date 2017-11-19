import java.util.*;
public class Post{
   public String username;
   public Long time;
   public String post;
   public ArrayList<String> likes = new ArrayList<String>();

   public Post(){} //for testing

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
   //System.currentTimeMillis();
}
