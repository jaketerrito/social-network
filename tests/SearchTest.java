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
		
		assertTrue(model.search("Ake").contains("jake"));
		assertTrue(model.search("akE").contains("lake"));
		assertTrue(model.search("aKe").contains("snake"));
		
		assertTrue(model.search("eRrito").contains("snake"));
		assertTrue(model.search("erritO").contains("jake"));
		assertFalse(model.search("errito").contains("lake"));
	}

}
