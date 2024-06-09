package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import command.MakeDirectoryClass;
import driver.InputProcessor;
import exception.InvalidArgumentException;
import exception.InvalidNameException;
import exception.InvalidPathException;
import exception.ItemAlreadyExistsException;
import output.Output;

/**
 * JUnit test for MakeDirectoryClass
 * 
 * @author Luoliang Cai
 */
public class MakeDirectoryClassTest {
  private MockFileSystem fs;
  private MakeDirectoryClass mkdir;
  private InputProcessor input;

  @Before
  public void setUp() throws Exception {
    fs = new MockFileSystem();
    mkdir = new MakeDirectoryClass(fs);
  }

  @After
  public void tearDown() throws Exception {
    fs = null;
    mkdir = null;
    input = null;
  }

  /**
   * Test if correct exception is generated if user uses no arguments
   */
  @Test
  public void testRunCommandNoArguments() {
    input = new MockInputProcessor("mkdir", null, null, null);
    Output actual = mkdir.runCommand(input);
    assertEquals(actual.getOutputMessage(), null);
    assertTrue(actual.getException() instanceof InvalidArgumentException);
    assertEquals(actual.getException().getMessage(), "mkdir must take at least one argument");
  }

  /**
   * Test if correct exception is generated if user inputs an invalid path as one of the arguments
   */
  @Test
  public void testRunCommandInvalidPath() {
    String arguments[] = {"InvalidPath"};
    input = new MockInputProcessor("mkdir", arguments, null, null);
    Output actual = mkdir.runCommand(input);
    assertEquals(actual.getOutputMessage(), null);
    assertTrue(actual.getException() instanceof InvalidPathException);
    assertEquals(actual.getException().getMessage(),
        "Could not create any directory because InvalidPath is an invalid path");
  }

  /**
   * Test if correct exception is generated if user inputs a path with an invalid name as one of the
   * arguments
   */
  @Test
  public void testRunCommandInvalidName() {
    String arguments[] = {"InvalidName"};
    input = new MockInputProcessor("mkdir", arguments, null, null);
    Output actual = mkdir.runCommand(input);
    assertEquals(actual.getOutputMessage(), null);
    assertTrue(actual.getException() instanceof InvalidNameException);
    assertEquals(actual.getException().getMessage(),
        "Could not create any directory because InvalidName has an invalid name");
  }

  /**
   * Test if correct exception is generated if user inputs a path with an invalid name as one of the
   * arguments
   */
  @Test
  public void testRunCommandAlreadyExistingItem() {
    String arguments[] = {"PathOfExistingItem"};
    input = new MockInputProcessor("mkdir", arguments, null, null);
    Output actual = mkdir.runCommand(input);
    assertEquals(actual.getOutputMessage(), null);
    assertTrue(actual.getException() instanceof ItemAlreadyExistsException);
    assertEquals(actual.getException().getMessage(),
        "Could not create any directory because item already exists at PathOfExistingItem");
  }

  /**
   * Test if mkdir successfully creates one directory
   */
  @Test
  public void testRunCommandMakeOneDirectory() {
    String arguments[] = {"ValidPath"};
    input = new MockInputProcessor("mkdir", arguments, null, null);
    Output actual = mkdir.runCommand(input);
    assertEquals(actual.getOutputMessage(), null);
    assertEquals(actual.getException(), null);
  }

  /**
   * Test if mkdir successfully creates multiple directories
   */
  @Test
  public void testRunCommandMakeMultipleDirectories() {
    String arguments[] = {"ValidPath1", "ValidPath2", "ValidPath3"};
    input = new MockInputProcessor("mkdir", arguments, null, null);
    Output actual = mkdir.runCommand(input);
    assertEquals(actual.getOutputMessage(), null);
    assertEquals(actual.getException(), null);
    ArrayList<String> expected = new ArrayList<String>();
    expected.add("ValidPath1");
    expected.add("ValidPath2");
    expected.add("ValidPath3");
    assertEquals(fs.getDirectories(), expected);
  }

  /**
   * Test if mkdir successfully creates two directories where the second directory being created
   * successfully is reliant on the first directory being created successfully
   */
  @Test
  public void testRunCommandMakeMultipleDirectoriesInvalidName() {
    String arguments[] = {"ValidPath1", "ValidPath2", "InvalidName", "ValidPath3"};
    input = new MockInputProcessor("mkdir", arguments, null, null);
    Output actual = mkdir.runCommand(input);
    assertEquals(actual.getOutputMessage(), null);
    assertEquals(actual.getException().getMessage(),
        "Could not create directory at InvalidName because InvalidName has an invalid name but was "
            + "able to create all directories before it");
    assertTrue(actual.getException() instanceof InvalidNameException);
    ArrayList<String> expected = new ArrayList<String>();
    expected.add("ValidPath1");
    expected.add("ValidPath2");
    assertEquals(fs.getDirectories(), expected);
  }

  /**
   * Test if mkdir successfully creates two directories where the second directory being created
   * successfully is reliant on the first directory being created successfully
   */
  @Test
  public void testRunCommandMakeMultipleDirectoriesInvalidPath() {
    String arguments[] = {"ValidPath1", "ValidPath2", "InvalidPath", "ValidPath3"};
    input = new MockInputProcessor("mkdir", arguments, null, null);
    Output actual = mkdir.runCommand(input);
    assertEquals(actual.getOutputMessage(), null);
    assertEquals(actual.getException().getMessage(),
        "Could not create directory at InvalidPath because InvalidPath is an invalid path but was "
            + "able to create all directories before it");
    assertTrue(actual.getException() instanceof InvalidPathException);
    ArrayList<String> expected = new ArrayList<String>();
    expected.add("ValidPath1");
    expected.add("ValidPath2");
    assertEquals(fs.getDirectories(), expected);
  }

  /**
   * Test if mkdir successfully creates two directories where the second directory being created
   * successfully is reliant on the first directory being created successfully
   */
  @Test
  public void testRunCommandMakeMultipleDirectoriesItemAlreadyExists() {
    String arguments[] = {"ValidPath1", "ValidPath2", "PathOfExistingItem", "ValidPath3"};
    input = new MockInputProcessor("mkdir", arguments, null, null);
    Output actual = mkdir.runCommand(input);
    assertEquals(actual.getOutputMessage(), null);
    assertEquals(actual.getException().getMessage(),
        "Could not create directory at PathOfExistingItem because item already exists at "
            + "PathOfExistingItem but was able to create all directories before it");
    assertTrue(actual.getException() instanceof ItemAlreadyExistsException);
    ArrayList<String> expected = new ArrayList<String>();
    expected.add("ValidPath1");
    expected.add("ValidPath2");
    assertEquals(fs.getDirectories(), expected);
  }

}
