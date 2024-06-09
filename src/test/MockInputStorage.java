package test;

import java.util.ArrayList;
import driver.InputStorage;

/**
 * This is a mock InputStorage class for JUnit testing
 * 
 * @author Man Hei Ho
 */
@SuppressWarnings("serial")
public class MockInputStorage extends InputStorage {

  private ArrayList<String> mockStorage;

  public MockInputStorage(ArrayList<String> inputHistory) {
    this.mockStorage = inputHistory;
  }

  public ArrayList<String> getUserInputHistory() {
    return this.mockStorage;
  }
}
