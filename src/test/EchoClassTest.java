package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import command.EchoClass;
import output.*;

/**
 * This is a JUnit test for EchoClass class
 * 
 * @author Yuanyuan Li
 */
public class EchoClassTest {
  private EchoClass echo;
  private Output output;

  @Before
  public void setUp() throws Exception {
    this.echo = new EchoClass();
    this.output = new Output(null, null);
  }

  @Test
  /**
   * This test is for valid command line
   */
  public void validCommandLine() {
    String[] argument = {"\"text text\""};
    MockInputProcessor userInput = new MockInputProcessor("echo", argument, null, null);
    this.output = this.echo.runCommand(userInput);
    assertEquals(null, this.output.getException());
    assertEquals("text text", this.output.getOutputMessage());
  }

  @Test
  /**
   * This test is for invalid string
   */
  public void invalidString() {
    String[] argument = {" \\\"i.n!va)lidS%$t~ring"};
    MockInputProcessor userInput = new MockInputProcessor("echo", argument, null, null);
    this.output = this.echo.runCommand(userInput);
    assertEquals(null, this.output.getOutputMessage());
    assertEquals("Invalid argument: invalid string", this.output.getException().getMessage());
  }

  @Test
  /**
   * This test is for no argument
   */
  public void noArgument() {
    MockInputProcessor userInput = new MockInputProcessor("echo", null, null, null);
    this.output = this.echo.runCommand(userInput);
    assertEquals(null, this.output.getOutputMessage());
    assertEquals("Invalid argument: no string provided", this.output.getException().getMessage());
  }

  @Test
  /**
   * This test is for more than one arguments
   */
  public void moreArgument() {
    String[] argument = {"arg1", "arg2"};
    MockInputProcessor userInput = new MockInputProcessor("echo", argument, null, null);
    this.output = this.echo.runCommand(userInput);
    assertEquals(null, this.output.getOutputMessage());
    assertEquals("Invalid argument: echo should take no more than one string as an argument",
        this.output.getException().getMessage());
  }

}
