import java.io.*;
import java.util.*;

public class User{
   public ArrayList<String> friends = new ArrayList<String>();
   public ArrayList<Post> posts = new ArrayList<Post>();
   File data;
   public String name;
   public String username; //no spaces or , etc
   public String password;
   public String imageLocation; //this is probably the wrong type.

   public User(){} //for testing

   public User(File file) throws IOException{
      data = file;
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
      name = line.next();
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
   }

   public void toFile(String root) throws FileNotFoundException{
      PrintWriter writer = new PrintWriter(new File(root + "/" + username + ".txt"));
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
