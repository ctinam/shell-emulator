package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import driver.InputStorage;

/**
 * JUnit test for InputStorage
 * 
 * @author Man Hei Ho
 */
public class InputStorageTest {

  private InputStorage inputHistory;

  @Before
  public void setup() {
    this.inputHistory = new InputStorage();
  }

  /**
   * Test InputStorage getUserInputHistory() with empty storage
   */
  @Test
  public void testGetUserInputHistoryOnEmptyStorage() {
    ArrayList<String> test = new ArrayList<String>();
    assertEquals(test, this.inputHistory.getUserInputHistory());
  }

  /**
   * Test InputStorage addUserInput() and getUserInputHistory() with one entry in storage
   */
  @Test
  public void testAddUserInputAndGetUserInputHistoryGivenOneInput() {
    ArrayList<String> test = new ArrayList<String>();
    test.add("random user input");

    this.inputHistory.addUserInput("random user input");

    assertEquals(test, this.inputHistory.getUserInputHistory());
  }

  /**
   * Test InputStorage addUserInput() and getUserInputHistory() with more than one entry in storage
   */
  @Test
  public void testAddUserInputAndGetUserInputHistoryGivenMultipleInput() {
    ArrayList<String> test = new ArrayList<String>();
    test.add("random user inputq duhf  ");
    test.add("   random user input2");
    test.add("   random user      input3");
    test.add(" random user    input4   ");

    this.inputHistory.addUserInput("random user inputq duhf  ");
    this.inputHistory.addUserInput("   random user input2");
    this.inputHistory.addUserInput("   random user      input3");
    this.inputHistory.addUserInput(" random user    input4   ");

    assertEquals(test, this.inputHistory.getUserInputHistory());
  }

}
