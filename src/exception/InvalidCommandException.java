package exception;

/**
 * Exception thrown when user enters an command that is not supported by the Jshell
 * 
 * @author Luoliang Cai
 */
@SuppressWarnings("serial")
public class InvalidCommandException extends Exception {
  /**
   * Class constructor that creates an instance of an InvalidCommand exception
   * 
   * @param message detailed message that specifies the cause of the exception
   */
  public InvalidCommandException(String message) {
    super(message);
  }
}
