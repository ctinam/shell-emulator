package exception;

/**
 * Exception thrown when user tries to create a file or directory with invalid characters in the
 * name
 * 
 * @author Luoliang Cai
 */
@SuppressWarnings("serial")
public class InvalidNameException extends Exception {

  /**
   * Constructor that creates instance of ItemAlreadyExistsException
   * 
   * @param message detailed message that specifies the cause of the exception
   */
  public InvalidNameException(String message) {
    super(message);
  }
}
