import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.*;
import project2.Model;
import project2.Post;



class NewsFeedOrderTest {

	@Test
	void test() throws InterruptedException {
		Model model = new Model("testUsers");
		//test and other person post some stuff
		model.register("testPerson", "pass", "Testy", "a location");
		model.register("otherPerson", "pass", "Other", "asdf");
		model.setUser("testPerson", "pass");
		model.post("This is the oldest post.");
		Thread.sleep(100);
		model.post("This is the second oldest post.");
		Thread.sleep(100);
		model.setUser("otherPerson", "pass");
		model.post("Third oldest post");
		Thread.sleep(100);
		model.setUser("testPerson", "pass");
		model.post("This is the 4th oldest post.");
		Thread.sleep(100);
		model.setUser("otherPerson", "pass");
		model.post("Fifth oldest post");
		//I request news feed
		model.register("Me", "pass", "me", "asdf");
		model.addFriend("testPerson");
		model.addFriend("otherPerson");
		//Makes sure they are in order from newest to oldest
		ArrayList<Post> posts = model.getFriendsPosts();
		assertTrue(posts.get(0).getText().equals("Fifth oldest post"));
		assertTrue(posts.get(1).getText().equals("This is the 4th oldest post."));
		assertTrue(posts.get(2).getText().equals("Third oldest post"));
		assertTrue(posts.get(3).getText().equals("This is the second oldest post."));
		assertTrue(posts.get(posts.size()-1).getText().equals("This is the oldest post."));
	}

}
