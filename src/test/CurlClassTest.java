package test;

import static org.junit.Assert.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.Before;
import org.junit.Test;
import command.CurlClass;
import exception.InvalidPathException;
import output.Output;

/**
 * This is a JUnit test for CurlClass class
 * 
 * @author Yuanyuan Li
 */
public class CurlClassTest {
  private MockFileSystem fs;
  private Output output;
  private CurlClass curl;

  @Before
  public void setUp() throws Exception {
    this.fs = new MockFileSystem();
    this.output = new Output(null, null);
    this.curl = new CurlClass(this.fs);
  }

  @Test
  /**
   * This test is for testing getFileName method
   */
  public void getFileNameTest() throws NoSuchMethodException, SecurityException,
      IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    Method testMethod = this.curl.getClass().getDeclaredMethod("getFileName", String.class);
    testMethod.setAccessible(true);
    String result = (String) testMethod.invoke(this.curl,
        "http://www.cs.cmu.edu/spok/grimmtmp/!{0@}7#~3$|.<>^t?&x(t)");
    assertEquals("073txt", result);
  }

  @Test
  /**
   * This test is for testing getURL method (valid URL)
   */
  public void getURLTest1() throws NoSuchMethodException, SecurityException, IllegalAccessException,
      IllegalArgumentException, InvocationTargetException, InvalidPathException {
    Method testMethod = this.curl.getClass().getDeclaredMethod("getURL", String.class);
    testMethod.setAccessible(true);
    this.output =
        (Output) testMethod.invoke(curl, "https://www.runoob.com/java/java-string-replace.html");
    assertEquals(null, this.output.getException());
    assertEquals(null, this.output.getOutputMessage());
  }

  @Test
  /**
   * This test is for testing getURL method (invalid URL)
   */
  public void getURLTest2() throws NoSuchMethodException, SecurityException, IllegalAccessException,
      IllegalArgumentException, InvocationTargetException {
    Method testMethod = this.curl.getClass().getDeclaredMethod("getURL", String.class);
    testMethod.setAccessible(true);
    this.output = (Output) testMethod.invoke(curl, "073.txt");
    assertEquals("invalid URL", this.output.getException().getMessage());
    assertEquals(null, this.output.getOutputMessage());
  }

  @Test
  /**
   * This test is for testing no argument
   */
  public void noArgument() {
    MockInputProcessor userInput = new MockInputProcessor("curl", null, null, null);
    this.output = this.curl.runCommand(userInput);
    assertEquals("curl should takse one URL as argument", this.output.getException().getMessage());
    assertEquals(null, this.output.getOutputMessage());
  }

  @Test
  /**
   * This test is for testing more than one argument
   */
  public void moreArgument() {
    String[] argument = {"URL1", "URL2"};
    MockInputProcessor userInput = new MockInputProcessor("curl", argument, null, null);
    this.output = this.curl.runCommand(userInput);
    assertEquals("curl takes no more than one URL as argument",
        this.output.getException().getMessage());
    assertEquals(null, this.output.getOutputMessage());
  }

}
