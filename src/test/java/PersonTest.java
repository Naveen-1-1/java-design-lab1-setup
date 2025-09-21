import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for the Person class.
 */
public class PersonTest {

  private Person john;

  /**
   * Create a person before each test.
   */
  @Before
  public void setUp() {
    john = new Person("John", "Doe", 1945);
  }

  @Test
  public void testFirstName() {
    assertEquals("John", john.getFirstName());
  }

  @Test
  public void testSecondName() {
    assertEquals("Doe", john.getLastName());
  }

  @Test
  public void testRuntimeExceptionFirstName() {
    john = new Person("john", "Doe", 1945);
    try {
      john.getFirstName();
      assert false;
    } catch (RuntimeException e) {
      assert true;
    } catch (Exception e) {
      assert false;
    }
  }

  @Test
  public void testRuntimeExceptionLastName() {
    john = new Person("John", "doe", 1945);
    try {
      john.getLastName();
      assert false;
    } catch (RuntimeException e) {
      assert true;
    } catch (Exception e) {
      assert false;
    }
  }

  @Test
  public void testYearOfBirth() {
    assertEquals(1945, john.getYearOfBirth());
  }

}
