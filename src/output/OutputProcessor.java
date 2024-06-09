package output;

import driver.InputProcessor;
import exception.InvalidNameException;
import exception.InvalidPathException;
import exception.ItemAlreadyExistsException;
import exception.RedirectionException;

/**
 * This class processes output messages
 * 
 * @author: Man Hei Ho
 */

public class OutputProcessor {

  /**
   * This is a RedirectionProcessor object that redirects output messages
   */
  private RedirectionProcessor redirProcessor;

  /**
   * Class constructor
   * 
   * @param redirProcessor This is a RedirectionProcessor object that redirects output messages
   */
  public OutputProcessor(RedirectionProcessor redirProcessor) {
    this.redirProcessor = redirProcessor;
  }

  /**
   * This method takes an output message and user input, and based on user input, processes and
   * displays the message to shell or redirect the output message. The output message will be ending
   * in a new line character
   * 
   * @param outputMessage This is a String of output message
   * @param userInput This object contains the parsed user input
   * @throws InvalidPathException
   * @throws ItemAlreadyExistsException
   * @throws InvalidNameException
   * @throws RedirectionException
   */
  public void processOutputMessage(String outputMessage, InputProcessor userInput)
      throws InvalidNameException, ItemAlreadyExistsException, InvalidPathException,
      RedirectionException {

    if (userInput.getRedirectionOperator() != null) {
      // redirect output
      this.redirProcessor.redirectOutputMessage(outputMessage, userInput);

    } else if (userInput.getRedirectionOperator() == null
        && userInput.getRedirectionPathname() == null && outputMessage != null
        && !outputMessage.isEmpty()) {
      // output the output message ending in a new line character to shell
      System.out.println(outputMessage);
    }
  }
}
