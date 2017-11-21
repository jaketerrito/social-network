import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import project2.Post;
import project2.User;

class PostSortingTest {
	/*
	 * Tests the ordering of posts based off time posted. Newest should be first and oldest should be last.
	 */
	@Test
	void test() throws InterruptedException {
		User jake = new User("jterrito", "pass", "jake","locationn");
		ArrayList<Post> posts = new ArrayList<Post>();
		Post oldest = new Post(jake,"OldestPost");
		Thread.sleep(100);
		posts.add(new Post(jake,"SecondOldestPost"));
		Thread.sleep(100);
		posts.add(new Post(jake,"MiddlePost"));
		Thread.sleep(100);
		posts.add(new Post(jake,"SecondNewestPost"));
		Thread.sleep(100);
		posts.add(new Post(jake,"NewestPost"));
		Thread.sleep(100);
		posts.add(oldest);
		Collections.sort(posts);
		assertTrue(posts.get(0).getText().equals("NewestPost"));
		assertTrue(posts.get(1).getText().equals("SecondNewestPost"));
		assertTrue(posts.get(posts.size()-2).getText().equals("SecondOldestPost"));
		assertTrue(posts.get(posts.size()-1).getText().equals("OldestPost"));
	}
}
