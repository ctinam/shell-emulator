package exception;

/**
 * Exception thrown when user tries to retrieve a file or directory at a path where there is no file
 * or directory
 * 
 * @author Luoliang Cai
 */
@SuppressWarnings("serial")
public class InvalidPathException extends Exception {
  /**
   * Constructor that creates instance of InvalidPathException
   * 
   * @param message detailed message that specifies the cause of the exception
   */
  public InvalidPathException(String message) {
    super(message);
  }
}
