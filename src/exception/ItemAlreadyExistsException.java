package exception;

/**
 * Exception thrown when attempting to add a file or directory in a location where a file or
 * directory already exists
 * 
 * @author Luoliang Cai
 */
@SuppressWarnings("serial")
public class ItemAlreadyExistsException extends Exception {
  /**
   * Constructor that creates instance of ItemAlreadyExistsException
   * 
   * @param message detailed message that specifies the cause of the exception
   */
  public ItemAlreadyExistsException(String message) {
    super(message);
  }
}
