package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import command.RemoveDirectoryClass;
import driver.InputProcessor;
import driver.ValidationGate;
import filesystem.Directory;
import filesystem.FileSystem;
import output.Output;

/**
 * This is the JUnit test class for RemoveDirectoryClass
 * 
 * @author Man Hei Ho
 */
public class RemoveDirectoryClassTest {

  private FileSystem fs;
  private ValidationGate vg;
  private RemoveDirectoryClass rm;

  @Before
  public void setup() {
    this.fs = new MockFileSystem();
    this.vg = new MockValidationGate();
    this.rm = new RemoveDirectoryClass(this.fs, this.vg);
  }

  /**
   * Test RemoveDirectoryClass runCommand() with user entering no argument
   */
  @Test
  public void testRemoveDirectoryRunCommandWithNoArgument() {
    InputProcessor userInput = new MockInputProcessor("rm", null, null, null);
    Output output = this.rm.runCommand(userInput);

    assertEquals(null, output.getOutputMessage());

    if (output.getException() == null)
      fail("Should throw exception.");
    String expectedError = "rm takes one directory pathname as argument.";
    assertEquals(expectedError, output.getException().getMessage());
  }

  /**
   * Test RemoveDirectoryClass runCommand() with user entering more than one argument
   */
  @Test
  public void testRemoveDirectoryRunCommandWithMoreThanOneArgument() {
    String[] args = {"ValidPathnameOne", "ValidPathname2"};
    InputProcessor userInput = new MockInputProcessor("rm", args, null, null);
    Output output = this.rm.runCommand(userInput);

    assertEquals(null, output.getOutputMessage());

    if (output.getException() == null)
      fail("Should throw exception.");
    String expectedError = "rm takes one directory pathname as argument.";
    assertEquals(expectedError, output.getException().getMessage());
  }

  /**
   * Test RemoveDirectoryClass runCommand() with user entering one invalid argument
   */
  @Test
  public void testRemoveDirectoryRunCommandWithOneInvalidPath() {
    String[] args = {"InvalidPathname"};
    InputProcessor userInput = new MockInputProcessor("rm", args, null, null);
    Output output = this.rm.runCommand(userInput);

    assertEquals(null, output.getOutputMessage());

    if (output.getException() == null)
      fail("Should throw exception.");
    String expectedError = "InvalidPathname is not a valid pathname.";
    assertEquals(expectedError, output.getException().getMessage());
  }

  /**
   * Test RemoveDirectoryClass runCommand() with user entering one valid file path
   */
  @Test
  public void testRemoveDirectoryRunCommandWithOneValidFilePath() {
    String[] args = {"FilePath"};
    InputProcessor userInput = new MockInputProcessor("rm", args, null, null);
    Output output = this.rm.runCommand(userInput);

    assertEquals(null, output.getOutputMessage());

    if (output.getException() == null)
      fail("Should throw exception.");
    String expectedError = "FilePath does not refer to an existing directory";
    assertEquals(expectedError, output.getException().getMessage());
  }

  /**
   * Test RemoveDirectoryClass runCommand() with one valid non-existing directory path
   */
  @Test
  public void testRemoveDirectoryRunCommandWithOneValidNonexistingDirectoryPath() {
    String[] args = {"NonexistingValidPathname"};
    InputProcessor userInput = new MockInputProcessor("rm", args, null, null);
    Output output = this.rm.runCommand(userInput);

    assertEquals(null, output.getOutputMessage());

    if (output.getException() == null)
      fail("Should throw exception.");
    String expectedError = "NonexistingValidPathname does not refer to an existing directory";
    assertEquals(expectedError, output.getException().getMessage());
  }

  /**
   * Test RemoveDirectoryClass runCommand() with user entering one valid existing directory path
   */
  @Test
  public void testRemoveDirectoryRunCommandWithOneValidExistingDirectoryPath() {
    String[] args = {"DirectoryPath"};
    InputProcessor userInput = new MockInputProcessor("rm", args, null, null);
    Output output = this.rm.runCommand(userInput);

    assertEquals(null, output.getOutputMessage());
    assertEquals(null, output.getException());
    assertEquals(false, ((MockFileSystem) this.fs).directories.contains("DirectoryPath"));
  }

  /**
   * Test RemoveDirectoryClass runCommand() to remove root
   */
  @Test
  public void testRemoveDirectoryRunCommandToRemoveRoot() {
    String[] args = {"/"};
    InputProcessor userInput = new MockInputProcessor("rm", args, null, null);
    Output output = this.rm.runCommand(userInput);

    assertEquals(null, output.getOutputMessage());

    if (output.getException() == null)
      fail("Should throw exception.");
    String expectedError = "Cannot delete root directorry.";
    assertEquals(expectedError, output.getException().getMessage());
  }

  /**
   * Test RemoveDirectoryClass runCommand() to remove current working directory
   */
  @Test
  public void testRemoveDirectoryRunCommandToRemoveCurrentWorkingDirectory() {
    String path = "CurrentDirValidPath";
    Directory currentDir = new MockDirectory(path, path, null, null);
    this.fs.setCurrentDirectory(currentDir);
    String[] args = {path};
    InputProcessor userInput = new MockInputProcessor("rm", args, null, null);
    Output output = this.rm.runCommand(userInput);

    assertEquals(null, output.getOutputMessage());

    if (output.getException() == null)
      fail("Should throw exception.");
    String expectedError = "Cannot delete current working directory.";
    assertEquals(expectedError, output.getException().getMessage());
  }

  /**
   * Test RemoveDirectoryClass runCommand() to remove parent of current working directory
   */
  @Test
  public void testRemoveDirectoryRunCommandToRemoveParentOfCurrentWorkingDirectory() {
    Directory root = new MockDirectory("/", "/", null, null);
    this.fs = new MockFileSystemVerTwo(root, null, null, null, null);
    this.rm = new RemoveDirectoryClass(this.fs, this.vg);

    Directory currentDir = new MockDirectory("child", "child", null, null);
    this.fs.setCurrentDirectory(currentDir);

    String[] args = {"ValidPath"};
    InputProcessor userInput = new MockInputProcessor("rm", args, null, null);
    Output output = this.rm.runCommand(userInput);

    assertEquals(null, output.getOutputMessage());

    if (output.getException() == null)
      fail("Should throw exception.");
    String expectedError =
        "ValidPath cannot be deleted because it is one of the parents of the current directory.";
    assertEquals(expectedError, output.getException().getMessage());
  }

}
