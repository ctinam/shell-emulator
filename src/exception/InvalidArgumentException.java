package exception;

/**
 * Exception thrown when user enter argument(s) that is not valid or match what is expected.
 * Argument(s) are strings the user has input after the command.
 * 
 * @author Christina Ma
 */
@SuppressWarnings("serial")
public class InvalidArgumentException extends Exception {
  /**
   * Class Constructor
   * 
   * @param message A descriptive message that explains the reasoning behind the exception.
   */
  public InvalidArgumentException(String message) {
    super(message);
  }
}
