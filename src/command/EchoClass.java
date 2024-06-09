package command;

import output.*;
import driver.*;
import exception.*;

/**
 * This class implement the echo command and output String to shell or files
 * 
 * @author Yuanyuan Li
 */
public class EchoClass extends Command {

  /**
   * Class constructor
   */
  public EchoClass() {

  }

  /**
   * This method executes the echo command and output the STRING to shell or file
   * 
   * @param userInput This is an InputProcessor object that contains the parsed user input
   * @throws InvalidArgumentException
   * @throws RedirectionException
   */
  public Output runCommand(InputProcessor userInput) {
    try {
      ValidationGate valiGate = new ValidationGate();
      String[] argument = userInput.getArgument();
      // check whether takes an argument
      if (argument == null || argument.length == 0) {
        throw new InvalidArgumentException("Invalid argument: no string provided");
      }
      // check if there is correct number of argument
      else if (argument.length != 1) {
        throw new InvalidArgumentException(
            "Invalid argument: echo should take no more than one string as an argument");
      }
      // check whether the argument is a valid string
      else if (!valiGate.isValidString(argument[0])) {
        throw new InvalidArgumentException("Invalid argument: invalid string");
      }
      // output String
      else {
        String outputMes = argument[0].substring(1, argument[0].length() - 1);
        Output output = new Output(outputMes, null);
        return output;
      }
    }
    // catch exception
    catch (InvalidArgumentException e) {
      Output output = new Output(null, e);
      return output;
    }
  }
}
