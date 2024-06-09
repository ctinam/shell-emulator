package driver;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class stores all user inputs
 * 
 * @author Man Hei Ho
 */
@SuppressWarnings("serial")
public class InputStorage implements Serializable {

  /**
   * This is an ArrayList storing all user inputs
   */
  private ArrayList<String> userInputHistory;

  /**
   * Class constructor
   */
  public InputStorage() {
    this.userInputHistory = new ArrayList<String>();
  }

  /**
   * This method add and store a user input
   * 
   * @param userInput This is a String of user input
   */
  public void addUserInput(String userInput) {
    if (userInput != null && !userInput.trim().isEmpty()) {
      this.userInputHistory.add(userInput);
    }
  }

  /**
   * This methods returns a list of all user input at the time the method is called
   * 
   * @return an ArrayList of all user input
   */
  @SuppressWarnings("unchecked")
  public ArrayList<String> getUserInputHistory() {
    return (ArrayList<String>) this.userInputHistory.clone();
  }
}
