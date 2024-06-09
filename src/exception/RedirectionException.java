package exception;

/**
 * Exception thrown when any error occur in redirection process
 * 
 * @author Yuanyuan Li
 */
@SuppressWarnings("serial")
public class RedirectionException extends Exception {

  /**
   * Class constructor
   * 
   * @param message A specific error message
   */
  public RedirectionException(String message) {
    super(message);
  }

}
