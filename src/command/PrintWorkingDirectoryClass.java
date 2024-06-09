package command;

import output.Output;
import driver.InputProcessor;
import exception.InvalidArgumentException;
import filesystem.FileSystem;

/**
 * This class sendss the absolute path of the current working directory to output
 * 
 * @author Luoliang Cai
 */
public class PrintWorkingDirectoryClass extends Command {

  private FileSystem fs;

  /**
   * Constructor that creates instance of pwd command
   * 
   * @param fileSystem The file system which the command will retrieve the working directory from
   */
  public PrintWorkingDirectoryClass(FileSystem fileSystem) {
    this.fs = fileSystem;
  }

  /**
   * Displays the absolute path of the current working directory
   * 
   * @param userInput The parsed userInput
   * @return an Output instance that holds any printable output and error messages
   */
  public Output runCommand(InputProcessor userInput) {
    // check if command is run with correct number of arguments
    if (userInput.getArgument() != null) {
      InvalidArgumentException e =
          new InvalidArgumentException(userInput.getCommand() + " can not take any arguments");
      return new Output(null, e);
    }
    return new Output(this.fs.getCurrentDirectory().getPath(), null);
  }
}
