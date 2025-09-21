/**
 * This class represents a person The person has a first name, last name and an year of birth.
 */
public class Person {
  private String firstName;
  private String lastName;
  private int yearOfBirth;

  private final String nameRegex = "^[A-Z][a-zA-Z]*$";

  /**
   * Constructs a Person object and initializes it to the given first name, last name and year of
   * birth.
   *
   * @param firstName   the first name of this person
   * @param lastName    the last name of this person
   * @param yearOfBirth the year of birth of this person
   */

  public Person(String firstName, String lastName, int yearOfBirth) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.yearOfBirth = yearOfBirth;
  }

  /**
   * Get the first name of this person.
   * It throws a runtime exception if the
   * firstname does not start with upper caps and contains
   * characters other than A-Z or a-z.
   *
   * @return the first name of this person
   */
  public String getFirstName() {
    if (this.firstName.matches(nameRegex)) {
      return this.firstName;
    } else {
      throw new RuntimeException("Invalid first name");
    }
  }

  /**
   * Return the last name of this person.
   * It throws a runtime exception if the
   * firstname does not start with upper caps and contains
   * characters other than A-Z or a-z.
   *
   * @return the last name of this person
   */

  public String getLastName() {
    if (this.lastName.matches(nameRegex)) {
      return this.lastName;
    } else {
      throw new RuntimeException("Invalid last name");
    }
  }

  /**
   * Return the year of birth of this person.
   *
   * @return the year of birth of this person
   */
  public int getYearOfBirth() {
    return this.yearOfBirth;
  }
}

