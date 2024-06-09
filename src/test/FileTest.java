package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import filesystem.File;

/**
 * JUnit test for File class
 * 
 * @author Luoliang Cai
 */
public class FileTest {
  private File testFile;

  @Before
  public void setUp() {
    testFile = new File("testFile", "/testfile", "some text");
  }

  /**
   * Test if append method successfully appends text to end of a file
   */
  @Test
  public void testAppendText() {
    testFile.appendText(" more text");
    assertEquals(testFile.getText(), "some text more text");
  }

  /**
   * Test if overwrite method successfully overwrites text in file with new text
   */
  @Test
  public void testOverwriteText() {
    testFile.overwriteText("new text");
    assertEquals(testFile.getText(), "new text");
  }

  @After
  public void tearDown() {
    testFile = null;
  }

}
