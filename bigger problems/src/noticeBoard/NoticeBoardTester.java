package noticeBoard;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

public class NoticeBoardTester {
	
	private Notice notice1;
	private Notice notice2;
	
	@Before
	public void setUp() {
		// create two notice objects with different constructors
		notice1 = new Notice("Hello", "This is a test notice", "Alice", "1234567890");
		notice2 = new Notice("Goodbye", "This is another test notice", "Bob", "0987654321", "2023-08-06T17:53:55");
	}

	@Test
	public void testGetCreationTime() {
		// check that the creation time of notice2 is the given time
		assertEquals(LocalDateTime.parse("2023-08-06T17:53:55"), notice2.getCreationTime());
	}

	@Test
	public void testGetTitle() {
		// check that the title of notice1 is "Hello"
		assertEquals("Hello", notice1.getTitle());
		
		// check that the title of notice2 is "Goodbye"
		assertEquals("Goodbye", notice2.getTitle());
	}

	@Test
	public void testGetContent() {
		// check that the content of notice1 is "This is a test notice"
		assertEquals("This is a test notice", notice1.getContent());
		
		// check that the content of notice2 is "This is another test notice"
		assertEquals("This is another test notice", notice2.getContent());
	}

	@Test
	public void testGetContact() {
		// check that the contact of notice1 is "Alice (1234567890)"
		assertTrue(notice1.getContact().equals("{Contact: 1234567890 (Alice)}"));
	}

}