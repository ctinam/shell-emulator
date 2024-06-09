package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import filesystem.Directory;
import filesystem.FileSystemContent;

/**
 * JUnit test for FileSystemContent class
 * 
 * @author Luoliang Cai
 */
public class FileSystemContentTest {
  private FileSystemContent testContent;

  /**
   * Initialize and instance of FileSystemContent
   */
  @Before
  public void setUp() {
    testContent = new FileSystemContent("testContent", "root/testContent");
  }

  /**
   * test if any new file system content made has an empty parent directory along with the correct
   * name and path
   */
  @Test
  public void newContentTest() {
    assertEquals(testContent.getName(), "testContent");
    assertEquals(testContent.getPath(), "root/testContent");
    assertEquals(testContent.getParent(), null);
  }

  /**
   * Test if set parent successfully changes the directories parent
   */
  @Test
  public void setParentTest() {
    Directory parent = new Directory("newDir", "root");
    testContent.setParent(parent);
    assertEquals(testContent.getParent(), parent);
  }

  @After
  public void tearDown() {
    testContent = null;
  }
}
