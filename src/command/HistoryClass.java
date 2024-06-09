package command;

import java.util.ArrayList;
import output.Output;
import driver.InputProcessor;
import driver.InputStorage;
import driver.ValidationGate;
import exception.InvalidArgumentException;

/**
 * This class implements the history command and list and output all or a specified number of user
 * input history
 * 
 * @author Man Hei Ho
 */
public class HistoryClass extends Command {

  /**
   * This is an InputStorage object that stores all user input history
   */
  private InputStorage inputStorage;

  /**
   * This is a ValidationGate object that validates user input
   */
  private ValidationGate valiGate;

  /**
   * Class Constructor
   * 
   * @param inputStorage This stores all user input history
   * @param valiGate This is a ValidationGate object that validates user input
   */
  public HistoryClass(InputStorage inputStorage, ValidationGate valiGate) {
    this.inputStorage = inputStorage;
    this.valiGate = valiGate;
  }

  /**
   * This method executes the history command and output all or a specified number of user input
   * history
   * 
   * @param userInput This contains the parsed user input
   * @return An Output object representing the output from the command class
   */
  public Output runCommand(InputProcessor userInput) {
    if (userInput.getArgument() == null) {
      // no argument
      return this.printUserInputHistory();
    } else if (userInput.getArgument().length == 1
        && this.valiGate.isValidInteger(userInput.getArgument()[0])
        && Integer.parseInt(userInput.getArgument()[0]) >= 0) {
      // one argument that is a integer
      return this.printUserInputHistory(Integer.parseInt(userInput.getArgument()[0]));
    } else {
      InvalidArgumentException e =
          new InvalidArgumentException("history takes no argument or one number (>=0) as argument");
      return new Output(null, e);
    }
  }

  /**
   * This method list and output all user input history
   * 
   * @return An Output object representing the output from the command class
   */
  private Output printUserInputHistory() {
    // list and output all user input history
    return this.printUserInputHistory(this.inputStorage.getUserInputHistory().size());
  }

  /**
   * This method list and output a number of user input history as specified by numOfRecord
   * 
   * @param numOfRecord This is an integer indicating the number of user input history to output
   * @return An Output object representing the output from the command class
   */
  private Output printUserInputHistory(int numOfRecord) {
    // get all user inputs from inputStorage
    ArrayList<String> userInputHistory = this.inputStorage.getUserInputHistory();
    String outputMessage = "";

    // find where to start printing
    int startAt = Math.max(0, userInputHistory.size() - numOfRecord);
    for (int i = startAt; i < userInputHistory.size(); i++) {
      if (i == startAt) {
        outputMessage = Integer.toString(i + 1) + ". " + userInputHistory.get(i);
      } else {
        outputMessage =
            outputMessage + "\n" + Integer.toString(i + 1) + ". " + userInputHistory.get(i);
      }
    }
    return new Output(outputMessage, null);
  }
}
