package storage;

import java.io.*;
import java.util.Queue;
import java.util.Stack;
import driver.InputProcessor;
import driver.InputStorage;
import exception.*;
import filesystem.*;

/**
 * This class implements loadJShell command and load the previous saved JShell session into the new
 * JShell session at the beginning
 * 
 * @author Yuanyuan Li
 *
 */

public class JShellLoading {

  private FileSystem fileSystem;
  private InputStorage inputHistory;
  private Stack<String> dirStack;
  private Queue<String> fileSystemQueue;
  private String currentPath;
  private String[] inValidPathnameChar = {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "{",
      "}", "~", "|", "<", ">", "?", " ", "/", "."};


  /**
   * Class constructor
   */
  public JShellLoading() {

  }

  /**
   * This method is for checking the validation of loadJShell command input
   * 
   * @param userCommand parsed user input command
   * @param inputHistory user input command history
   * @return boolean whether the user input is valid
   * @throws InvalidCommandException
   * @throws InvalidArgumentException
   * @throws RedirectionNotSupportException
   * @throws InvalidNameException
   */
  public boolean checkValidation(InputProcessor userCommand, InputStorage inputHistory)
      throws InvalidCommandException, InvalidArgumentException, RedirectionNotSupportException,
      InvalidNameException {
    String[] argument = userCommand.getArgument();
    // loadJShell not execute at the very first stage
    if (inputHistory.getUserInputHistory().size() != 1)
      throw new InvalidCommandException(
          "loadJShell command can only execute before any command has been executed");
    // takes no argument
    else if (argument == null)
      throw new InvalidArgumentException(
          "Invalid argument: saveJShell should take a FileName as an argument");
    // takes too many arguments
    else if (argument.length != 1)
      throw new InvalidArgumentException("Invalid argument: saveJShell can only take one argument");
    // takes redirection operator
    else if (userCommand.getRedirectionOperator() != null)
      throw new RedirectionNotSupportException("Redirection does not support saveJShell command");
    // invalid FileName
    else {
      for (String character : inValidPathnameChar) {
        if (argument[0].contains(character)) {
          throw new InvalidNameException("Invalid name: FileName contains invalid characters");
        }
      }
    }
    return true;
  }

  /**
   * This method will load the previous session of JShell
   * 
   * @param filePath User input file pathname
   * @throws IOException
   */
  @SuppressWarnings("unchecked")
  public void loadJShell(String filePath) throws IOException {
    try {
      FileHandler fileHandler = new FileHandler();
      if (fileHandler.fileExist(filePath)) {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
        // deserialize dirStack, inputHistory, fileSystemQueue, and currentWorkingDirectory
        this.dirStack = (Stack<String>) ois.readObject();
        this.inputHistory = (InputStorage) ois.readObject();
        this.fileSystemQueue = (Queue<String>) ois.readObject();
        this.currentPath = (String) ois.readObject();
        ois.close();
      }
    } catch (IOException e) {
      throw new IOException("Error: Deserialization fail");
    } catch (ClassNotFoundException e) {
    }
  }

  /**
   * This method will return inputHistory
   * 
   * @return InputHistory
   */
  public InputStorage setInputHistory() {
    return this.inputHistory;
  }

  /**
   * This method will return dirStack
   * 
   * @return DirectoryStack
   */
  public Stack<String> setDirectoryStack() {
    return this.dirStack;
  }

  /**
   * This method will add a directory into the fileSystem
   * 
   * @param pathname Directory pathname
   * @throws InvalidPathException
   * @throws ItemAlreadyExistsException
   * @throws InvalidNameException
   */
  private void addDirectory(String pathname)
      throws InvalidNameException, ItemAlreadyExistsException, InvalidPathException {
    int Index = pathname.indexOf(":") + 1;
    this.fileSystem.makeDirectory(pathname.substring(Index));
  }

  /**
   * This method will add a file into the fileSystem
   * 
   * @param pathname File pathname
   * @param content File content
   * @throws InvalidPathException
   * @throws ItemAlreadyExistsException
   * @throws InvalidNameException
   */
  private void addFile(String pathname, String content)
      throws InvalidNameException, ItemAlreadyExistsException, InvalidPathException {
    int tagIndex1 = pathname.indexOf(":") + 1;
    int tagIndex2 = content.indexOf(":") + 1;
    this.fileSystem.makeFile(pathname.substring(tagIndex1), content.substring(tagIndex2));
  }

  /**
   * This method will return fileSystem
   * 
   * @return FileSystem object
   * @throws InvalidPathException
   * @throws ItemAlreadyExistsException
   * @throws InvalidNameException
   */
  public FileSystem setFileSystem()
      throws InvalidNameException, ItemAlreadyExistsException, InvalidPathException {
    // initialize file system
    this.fileSystem = JFileSystem.buildFileSystem();
    // take out the root information
    this.fileSystemQueue.poll();
    while (fileSystemQueue.peek() != null) {
      String s = fileSystemQueue.poll();
      int tagIndex = s.indexOf(":");
      String tag = s.substring(0, tagIndex);
      // add directory into the fileSystem
      if (tag.equals("Directory")) {
        addDirectory(s);
      }
      // add File and its content into the fileSystem
      else if (tag.equals("FilePath")) {
        addFile(s, fileSystemQueue.poll());
      }
    }
    Directory currentWorkingDirectory = this.fileSystem.findDirectory(this.currentPath);
    this.fileSystem.setCurrentDirectory(currentWorkingDirectory);
    return this.fileSystem;
  }
}
