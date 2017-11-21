import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import project2.Model;
import java.util.*;
import project2.Post;
class DeactivateTest {

	@Test
	void test() {
		Model model = new Model("testUsers");
		//test and other person post some stuff
		model.register("DeleteHim", "pass", "Total Loser", "asfd");
		model.register("Bob", "pass", "Bob", "asfd");
		model.register("Bill", "pass", "Bill", "asfd");
		model.register("Joe", "pass", "Joe", "asfd");
		model.setUser("Joe", "pass");
		model.post("I like turtles");
		ArrayList<Post> joePosts = model.getUser("Joe").getPosts();
		
		model.setUser("DeleteHim","pass");
		model.addFriend("Bill");
		model.addFriend("Bob");
		model.addFriend("Joe");
		model.like("Joe",joePosts.get(0).getTime());
		
		assertFalse(model.getUser("DeleteHim") == null);
		assertTrue(model.getUser("Bill").getFriends().contains("DeleteHim"));
		assertTrue(model.getUser("Bob").getFriends().contains("DeleteHim"));
		assertTrue(model.getUser("Joe").getFriends().contains("DeleteHim"));
		assertTrue(model.getUser("Joe").getPosts().get(0).getLikes().contains("DeleteHim"));
		
		model.deactivate();
		
		assertTrue(model.getUser("DeleteHim") == null);
		assertFalse(model.getUser("Bill").getFriends().contains("DeleteHim"));
		assertFalse(model.getUser("Bob").getFriends().contains("DeleteHim"));
		assertFalse(model.getUser("Joe").getFriends().contains("DeleteHim"));
		assertFalse(model.getUser("Joe").getPosts().get(0).getLikes().contains("DeleteHim"));
	}

}
