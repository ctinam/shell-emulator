package output;

/**
 * ErrorHandler
 * 
 * This class will handle the exception message
 * 
 * @author Yuanyuan Li
 *
 */
public class ErrorHandler {

  /**
   * Class Constructor
   */
  public ErrorHandler() {

  }

  /**
   * This method will return a string that contains the exception message
   * 
   * @param e Exception
   * @return The message in e
   */
  public String getErrorMessage(Exception e) {
    if (e == null)
      return null;
    return e.getMessage();
  }

  /**
   * Method overload (GetErrorMessage)
   * 
   * @param e String
   * @return The res
   */
  public String getErrorMessage(String e) {
    if (e == null)
      return null;
    return e;
  }

  /**
   * This method will print the exception message
   * 
   * @param e Exception
   */
  public void printErrorMessage(Exception e) {
    if (e == null)
      return;
    System.setErr(System.out);
    System.err.println(e);
  }

  /**
   * Method overload (PrintErrorMessage)
   * 
   * @param e String
   */
  public void printErrorMessage(String e) {
    if (e == null)
      return;
    System.setErr(System.out);
    System.err.println(e);
  }
}
