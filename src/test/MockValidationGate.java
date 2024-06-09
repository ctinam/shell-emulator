package test;

import driver.ValidationGate;

/**
 * This is a a Mock ValidationGate Class used for the JUnit test classes.
 * 
 * @author Christina Ma
 */
public class MockValidationGate extends ValidationGate {
  /**
   * Class Constructor
   */
  public MockValidationGate() {}

  /**
   * This method validates if the given command supports redirection.
   * 
   * @param command A string containing the user's command
   * @return Return true if command support redirection, false otherwise.
   */
  public boolean isValidToRedirect(String command) {
    if (command.contains("ValidCommandSupportRedirection")) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * This method validates if the given command is valid.
   * 
   * @param command A string containing the user's command
   * @return Return true if command is valid, otherwise false
   */
  public boolean isValidCommand(String command) {
    if (command.contains("ValidCommand")) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * This method validates if the given pathname uses valid characters.
   * 
   * @param path A string containing the pathname (can be a relative or absolute pathname)
   * @return Return true if pathname is valid, otherwise false
   */
  public boolean isValidPathname(String path) {
    if (path.contains("ValidPathname") || path.contains("DirectoryPath")
        || path.contains("PathOfExistingItem") || path.contains("FilePath")
        || path.contains("ValidPath") || path.equals("/") || path.equals("Nonexisting")) {
      return true;
    } else {
      return false;
    }

  }

  /**
   * This method validates if the given string does not have double questions within the string
   * itself aside from the double quotations that surround the string.
   * 
   * @param str The given string
   * @return Return true if string is a valid string, otherwise false
   */
  public boolean isValidString(String str) {
    if (str.contains("ValidString") || str.equals("\"ValidDirNameOne\"")
        || str.equals("\"ValidDirNameTwo\"") || str.equals("\"ValidFileNameOne\"")
        || str.equals("\"ValidFileNameTwo\"") || str.equals("\"Nonexisting\"")) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * This method validates if the given string can be converted into a valid integer.
   * 
   * @param num The given string
   * @return Return true if num is an integer, otherwise false
   */
  public boolean isValidInteger(String num) {
    if (num.equals("0") || num.equals("10") || num.equals("1") || num.equals("5")
        || num.equals("100")) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * This method validates if the given string follow the character restrictions for directory and
   * file naming.
   * 
   * @param dirName The given string
   * @return Return true if dirName is a valid file or directory name, otherwise false
   */
  public boolean isValidDirectoryName(String dirName) {
    if (dirName.contains("ValidDirectoryName") || dirName.contains("ValidFileName")
        || dirName.contains("ValidDirName") || dirName.equals("FilePathOne")
        || dirName.equals("NonexistingValidFilePath") || dirName.equals("FilePathTwo")) {
      return true;
    } else {
      return false;
    }
  }


  /**
   * Validates if the given pathname is an absolute path but does not validate if the pathname is
   * valid.
   * 
   * @param pathname The given pathname
   * @return Return true if pathname is a absolute path, otherwise false
   */
  public boolean isValidFullPath(String path) {
    if (path.contains("ValidFullPath") || path.contains("ValidAbsolutePath")) {
      return true;
    }
    return false;
  }

}
