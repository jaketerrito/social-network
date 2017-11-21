import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import project2.Model;

class SearchTest {

	@Test
	void test() {
		Model model = new Model("testUsers");
		model.register("jake", "pass", "jake territo", "asd");
		model.register("lake", "pass", "lake smith", "asd");
		model.register("snake", "pass", "snake derrito", "asd");
		
		for(String name: model.search("ake")) {
			System.out.println(name);
		}
		
		assertTrue(model.search("ake").contains("jake"));
		assertTrue(model.search("ake").contains("lake"));
		assertTrue(model.search("ake").contains("snake"));
		
		assertTrue(model.search("errito").contains("snake"));
		assertTrue(model.search("errito").contains("jake"));
		assertFalse(model.search("errito").contains("lake"));
	}

}
