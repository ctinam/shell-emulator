package test;

import static org.junit.Assert.*;
import java.util.LinkedList;
import org.junit.Test;
import command.TreeClass;
import driver.InputProcessor;
import filesystem.Directory;
import filesystem.File;
import output.Output;

/**
 * This is a JUnit test class for TreeClass.
 * 
 * @author Christina Ma
 */
public class TreeClassTest {
  private TreeClass tree;

  /**
   * This test is for when no additional arguments have been provided at the root with no
   * sub-directories
   */
  @Test
  public void testRunCommandWithEmptyRoot() {
    MockFileSystem mfs = new MockFileSystem();
    this.tree = new TreeClass(mfs);
    InputProcessor mockUserInput = new MockInputProcessor("tree", null, null, null);
    Output treeOutput = tree.runCommand(mockUserInput);
    assertEquals("/", treeOutput.getOutputMessage());
    assertEquals(null, treeOutput.getException());
  }

  /**
   * This test is for when the user has provided additional arguments with the tree command
   */
  @Test
  public void testRunCommandWithArguments() {
    MockFileSystem mfs = new MockFileSystem();
    this.tree = new TreeClass(mfs);

    String[] argument = {"input"};
    MockInputProcessor mockUserInput = new MockInputProcessor("tree", argument, null, null);
    Output treeOutput = tree.runCommand(mockUserInput);
    assertEquals(null, treeOutput.getOutputMessage());
    assertEquals("tree takes no arguments", treeOutput.getException().getMessage());
  }

  /**
   * This test is for when the root has two sub-directories and one sub-directory has two files.
   */
  @Test
  public void testRunCommandWithMultipleSubdir() {
    // Forming a linked list so that it will act as the sub-files of mockDir
    LinkedList<File> file1 = new LinkedList<File>();
    file1.add(new File("ValidFileNameOne", "FilePathOne"));
    file1.add(new File("ValidFileNameTwo", "FilePathTwo"));

    // Forming a linked list so that it will act as the sub-directories of mockDir
    MockDirectory newDir1 = new MockDirectory("ValidDirNameOne", "DirectoryPathOne",
        new LinkedList<Directory>(), file1);
    MockDirectory newDir2 = new MockDirectory("ValidDirNameTwo", "DirectoryPathTwo",
        new LinkedList<Directory>(), new LinkedList<File>());
    LinkedList<Directory> dirs = new LinkedList<Directory>();
    dirs.add(newDir1);
    dirs.add(newDir2);

    // Creating a mock directory that will serve as the root of the mock file system
    MockDirectory mockDir =
        new MockDirectory("ValidDirNameHome", "ValidPath", dirs, new LinkedList<File>());
    MockFileSystemVerTwo mfs = new MockFileSystemVerTwo(mockDir, newDir1, newDir2, null, null);
    this.tree = new TreeClass(mfs);

    Output output = tree.runCommand(new MockInputProcessor("tree", null, null, null));
    assertEquals("ValidDirNameHome\n" + "    ValidDirNameOne\n" + "        ValidFileNameOne\n"
        + "        ValidFileNameTwo\n" + "    ValidDirNameTwo", output.getOutputMessage());
    assertEquals(null, output.getException());
  }

  /**
   * This test is for when the root has one sub-directory
   */
  @Test
  public void testRunCommandWithOneSubdir() {
    // Creating a mock directory
    MockDirectory newDir1 = new MockDirectory("ValidDirNameOne", "DirectoryPathOne",
        new LinkedList<Directory>(), new LinkedList<File>());

    // Forming a linked list so that it will act as the sub-directories of mockDir
    LinkedList<Directory> dirs = new LinkedList<Directory>();
    dirs.add(newDir1);

    // Creating a mock directory that will serve as the root of the mock file system
    MockDirectory mockDir =
        new MockDirectory("ValidDirNameHome", "ValidPath", dirs, new LinkedList<File>());
    MockFileSystemVerTwo mfs = new MockFileSystemVerTwo(mockDir, newDir1, null, null, null);
    this.tree = new TreeClass(mfs);

    Output output = tree.runCommand(new MockInputProcessor("tree", null, null, null));
    assertEquals("ValidDirNameHome\n" + "    ValidDirNameOne", output.getOutputMessage());
    assertEquals(null, output.getException());
  }

  /**
   * This test is for when the root has one file
   */
  @Test
  public void testRunCommandWithOneFile() {
    // Creating mock file
    LinkedList<File> file1 = new LinkedList<File>();
    file1.add(new File("ValidFileNameOne", "FilePathOne"));

    // Creating a mock directory that will serve as the root of the mock file system
    MockDirectory mockDir =
        new MockDirectory("ValidDirNameHome", "ValidPath", new LinkedList<Directory>(), file1);
    MockFileSystemVerTwo mfs = new MockFileSystemVerTwo(mockDir, null, null, null, null);
    this.tree = new TreeClass(mfs);

    Output output = tree.runCommand(new MockInputProcessor("tree", null, null, null));
    assertEquals("ValidDirNameHome\n" + "    ValidFileNameOne", output.getOutputMessage());
    assertEquals(null, output.getException());
  }

}
