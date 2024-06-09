package command;

import driver.InputProcessor;
import exception.InvalidArgumentException;
import exception.InvalidNameException;
import exception.InvalidPathException;
import exception.ItemAlreadyExistsException;
import filesystem.FileSystem;
import output.Output;

/**
 * This class allows user to create one or more directories in the file system.
 * 
 * @author Luoliang Cai
 */
public class MakeDirectoryClass extends Command {
  private FileSystem fs;

  /**
   * Creates an instance of the MakeDirectory Class
   * 
   * @param fileSystem The file system where the directories will be created
   */
  public MakeDirectoryClass(FileSystem fileSystem) {
    this.fs = fileSystem;
  }

  /**
   * Creates up to two directories. Throws an exception if an issue occurs that prevent one of the
   * directories from being created
   * 
   * @param userInput The parsed command containing arguments of mkdir
   * @return an Output instance that holds any printable output and error messages
   */
  public Output runCommand(InputProcessor userInput) {
    // check if command is run with correct number of arguments
    if (userInput.getArgument() == null) {
      InvalidArgumentException e =
          new InvalidArgumentException(userInput.getCommand() + " must take at least one argument");
      return new Output(null, e);
    }
    for (int i = 0; i < userInput.getArgument().length; i++) {
      try {
        fs.makeDirectory(userInput.getArgument()[i]);
      } catch (InvalidNameException e) {
        InvalidNameException f =
            new InvalidNameException(mkdirMessage(e.getMessage(), i, userInput));
        return new Output(null, f);
      } catch (ItemAlreadyExistsException e) {
        ItemAlreadyExistsException f =
            new ItemAlreadyExistsException(mkdirMessage(e.getMessage(), i, userInput));
        return new Output(null, f);
      } catch (InvalidPathException e) {
        InvalidPathException f =
            new InvalidPathException(mkdirMessage(e.getMessage(), i, userInput));
        return new Output(null, f);
      }
    }
    return new Output(null, null);
  }

  /**
   * Creates a detailed error message when an error occurs
   * 
   * @param reason The reason for the error
   * @param dirNum Number that helps identify which directory the error occured on
   * @param userInput The parsed command containing arguments of mkdir
   * @return the detailed error message
   */
  private String mkdirMessage(String reason, int dirNum, InputProcessor userInput) {
    String message;
    if (dirNum == 0) {
      message = "Could not create any directory because " + reason;
    } else {
      message = "Could not create directory at " + userInput.getArgument()[dirNum] + " because "
          + reason + " but was able to create all directories before it";
    }
    return message;
  }
}
