package exception;

/**
 * Exception thrown when there's an attempt to pop from an empty stack
 * 
 * @author Christina Ma
 */
@SuppressWarnings("serial")
public class EmptyStackException extends Exception {
  /**
   * Class Constructor
   * 
   * @param message A descriptive message that explains the reasoning behind the exception.
   */
  public EmptyStackException(String message) {
    super(message);
  }
}
