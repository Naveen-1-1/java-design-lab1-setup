import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for the Book class.
 */
public class BookTest {

  private Book book;
  private Person person;

  /**
   * Create a book before each test.
   */
  @Before
  public void setUp() {
    person = new Person("JK", "Rowling", 1985);
    book = new Book("Harry Potter", person, 40);
  }

  @Test
  public void testTitle() {
    assertEquals("Harry Potter", book.getTitle());
  }

  @Test
  public void testPrice() {
    assertEquals(40, book.getPrice(), 0.01);
  }

  @Test
  public void testAuthor() {
    assertEquals(person, book.getAuthor());
  }
}
