import java.io.*;
public class Test{
   public static void main(String args[]) throws IOException{
 
      User usr = new User();
      usr.name = "Kyle";
      usr.username = "kKyle";
      usr.password = "pass";
      usr.imageLocation = "aplace";
      //usr.friends.add("pa");
      //usr.friends.add("ma");
      //usr.friends.add("Barney");
      Post apost = new Post();
      apost.username = "kKyle";
      apost.time = System.currentTimeMillis();
      apost.post = "Yarharhar";
      //apost.likes.add("noONE");
      usr.posts.add(apost);
      usr.toFile("users");
     
      File dir = new File("users");
      for(File file: dir.listFiles()){
         usr = new User(file);
         System.out.println("name: " + usr.name);
         System.out.println("username: " + usr.username);
         System.out.println("password: " + usr.password);
         System.out.println("location: " + usr.imageLocation);
         System.out.println("friends: ");
         for(String friend:usr.friends){
            System.out.print(friend +", ");
         }
         System.out.println();
         for(Post post:usr.posts){
            System.out.println("post:");
            System.out.println("\t" + post.username + " " + post.time);
            System.out.println("\t" + post.post);
            System.out.print("\tlikes: ");
            for(String like:post.likes){
               System.out.print(like + ", ");
            }
            System.out.println();
            System.out.println();
         }
      }
      
      //edit a user and see what happens`
   }
}
