package project2;

import static org.junit.Assert.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.junit.Test;

public class ModelTests {
	public static void main(String args[]) throws InterruptedException {
		Model model = Model.getInstance("resources/testUsers");
		postSortingTest();
		clear();
		deactivateTest(model);
		clear();
		newsFeedOrderTest(model);
		clear();
		searchTest(model);
		clear();
		System.out.println("Tests Passed");
	}
	
	private static void clear(){
		for(File file: new File("resources/testUsers").listFiles()){
			try {
			    Files.delete(file.toPath());
			} catch (Exception x) {
				System.out.println(x.getMessage());
			}
		}
	}
	
	@Test
	static void postSortingTest() throws InterruptedException {
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
	
	@Test
	static void deactivateTest(Model model) {
		//test and other person post some stuff
		model.register("DeleteHim", "pass", "Total Loser", "asfd");
		model.register("Bob", "pass", "Bob", "asfd");
		model.register("Bill", "pass", "Bill", "asfd");
		model.register("Joe", "pass", "Joe", "asfd");
		model.post("Joe","I like turtles");
		ArrayList<Post> joePosts = model.getUser("Joe").getPosts();
		
		model.addFriend("DeleteHim","Bill");
		model.addFriend("DeleteHim","Bob");
		model.addFriend("DeleteHim","Joe");
		model.like("DeleteHim","Joe",joePosts.get(0).getTime());
		
		assertFalse(model.getUser("DeleteHim") == null);
		assertTrue(model.getUser("Bill").getFriends().contains("DeleteHim"));
		assertTrue(model.getUser("Bob").getFriends().contains("DeleteHim"));
		assertTrue(model.getUser("Joe").getFriends().contains("DeleteHim"));
		assertTrue(model.getUser("Joe").getPosts().get(0).getLikes().contains("DeleteHim"));
		
		model.deactivate("DeleteHim");
		
		assertTrue(model.getUser("DeleteHim") == null);
		assertFalse(model.getUser("Bill").getFriends().contains("DeleteHim"));
		assertFalse(model.getUser("Bob").getFriends().contains("DeleteHim"));
		assertFalse(model.getUser("Joe").getFriends().contains("DeleteHim"));
		assertFalse(model.getUser("Joe").getPosts().get(0).getLikes().contains("DeleteHim"));
	}
	
	@Test
	static void newsFeedOrderTest(Model model) throws InterruptedException {
		//test and other person post some stuff
		model.register("testPerson", "pass", "Testy", "a location");
		model.register("otherPerson", "pass", "Other", "asdf");
		model.post("testPerson","This is the oldest post.");
		Thread.sleep(100);
		model.post("testPerson","This is the second oldest post.");
		Thread.sleep(100);
		model.post("otherPerson","Third oldest post");
		Thread.sleep(100);
		model.post("testPerson","This is the 4th oldest post.");
		Thread.sleep(100);
		model.post("otherPerson","Fifth oldest post");
		//I request news feed
		model.register("Me", "pass", "me", "asdf");
		model.addFriend("Me","testPerson");
		model.addFriend("Me","otherPerson");
		//Makes sure they are in order from newest to oldest
		ArrayList<Post> posts = model.getFriendsPosts("Me");
		Collections.sort(posts);
		assertTrue(posts.get(0).getText().equals("Fifth oldest post"));
		assertTrue(posts.get(1).getText().equals("This is the 4th oldest post."));
		assertTrue(posts.get(2).getText().equals("Third oldest post"));
		assertTrue(posts.get(3).getText().equals("This is the second oldest post."));
		assertTrue(posts.get(posts.size()-1).getText().equals("This is the oldest post."));
	}
	
	@Test
	static void searchTest(Model model) {
		model.register("jake", "pass", "jake territo", "asd");
		model.register("lake", "pass", "lake smith", "asd");
		model.register("snake", "pass", "snake derrito", "asd");
		
		assertTrue(model.search("Ake").contains(model.getUser("jake")));
		assertTrue(model.search("akE").contains(model.getUser("lake")));
		assertTrue(model.search("aKe").contains(model.getUser("snake")));
		
		assertTrue(model.search("eRrito").contains(model.getUser("snake")));
		assertTrue(model.search("erritO").contains(model.getUser("jake")));
		assertFalse(model.search("errito").contains(model.getUser("lake")));
	}

}
