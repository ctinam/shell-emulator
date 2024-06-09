package command;

import output.Output;
import driver.InputProcessor;

/**
 * This is the parent class for all command classes in JShell
 * 
 * @author Man Hei Ho
 */
public abstract class Command {

  /**
   * This method would implement the functionality of each command.
   * 
   * @param userInput This contains the parsed user input
   * @return An Output object representing the output from the command class
   */
  abstract public Output runCommand(InputProcessor userInput);
}
