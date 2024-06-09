package exception;

/**
 * Exception thrown when user enters an command that does not support redirection
 * 
 * @author Yuanyuan Li
 */
@SuppressWarnings("serial")
public class RedirectionNotSupportException extends Exception {

  /**
   * Class constructor
   * 
   * @param message A specific error message
   */
  public RedirectionNotSupportException(String message) {
    super(message);
  }

}
