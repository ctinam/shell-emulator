package command;

import output.*;
import driver.*;
import exception.*;
import filesystem.*;

/**
 * This class receives one or multiple pathnames (absolute or relative) and output the content of
 * the file(s) with 3 newline breaks in between each file.
 * 
 * @author Christina Ma
 */

public class ConcatenationClass extends Command {

  /**
   * This is a ValidationGate object that validates user input
   */
  private ValidationGate valiGate;
  /**
   * This is a FileSystem object that stores all files and directories contents
   */
  private FileSystem filesys;

  /**
   * Constructor creates an instance of an object that executes all functions for cat command
   * 
   * @param valiGate This is a ValidationGate object that validates user input
   * @param filesys This is a file system storing all files and directories contents
   */
  public ConcatenationClass(ValidationGate valiGate, FileSystem filesys) {
    this.valiGate = valiGate;
    this.filesys = filesys;
  }

  /**
   * This method will retrieve the contents of each given pathname that refers to an existing file.
   * If a given pathname does not exist, the method halts and the rest of the pathnames will not be
   * executed.
   * 
   * @param userInput This stores all user input that has been parsed
   * @return An Output object stores the message to be printed and the exception to be thrown if an
   *         exception has occurred
   */
  public Output runCommand(InputProcessor userInput) {
    if (userInput.getArgument() == null || userInput.getArgument().length == 0) {
      return new Output(null, new InvalidArgumentException("cat takes in at least one argument"));
    }
    String[] filePaths = userInput.getArgument();
    String outputMessage = "";
    try {
      for (int i = 0; i < filePaths.length; i++) {
        // Checks if pathname of file is valid
        if (valiGate.isValidPathname(filePaths[i])) {
          // Appends file content to outputMessage
          outputMessage = appendFileContent(outputMessage, filePaths, i);
        } else {
          InvalidPathException e =
              new InvalidPathException(filePaths[i] + " is an invalid pathname.");
          while (outputMessage.endsWith("\n")) {
            outputMessage = outputMessage.substring(0, outputMessage.lastIndexOf("\n"));
          }
          return new Output(outputMessage, e);
        }
      }
    } catch (InvalidPathException e) {
      while (outputMessage.endsWith("\n")) {
        outputMessage = outputMessage.substring(0, outputMessage.lastIndexOf("\n"));
      }
      return new Output(outputMessage, e);
    }
    return new Output(outputMessage, null);
  }

  /**
   * The contents of the current working file will be appended to outputMessage.
   * 
   * @param outputMessage A string containing all file content concatenated together
   * @param filePaths An array of all the pathnames provided by the user
   * @param fileIndex Refers to the index in filePaths array that will be the current working file
   * @return Returns outputMessage whereby the current working file's content has been appended
   * @throws InvalidPathException Thrown when the current working file does not exist in the file
   *         system
   */
  private String appendFileContent(String outputMessage, String[] filePaths, int fileIndex)
      throws InvalidPathException {
    // Retrieves file
    File file = filesys.findFile(filePaths[fileIndex]);
    // Appends entire contents of the file
    if (fileIndex < filePaths.length - 1) {
      outputMessage += file.getText() + "\n\n\n"; // Appending 3 newline breaks to different
                                                  // files
    } else {
      outputMessage += file.getText(); // Append only one newline break for last file
    }
    return outputMessage;
  }
}

