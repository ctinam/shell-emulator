package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import command.ManClass;
import output.*;

/**
 * This is a JUnit test for ManClass
 * 
 * @author Yuanyuan Li
 */
public class ManClassTest {
  private ManClass man;
  private Output output;
  private final String[] allCommands = {"exit", "mkdir", "cd", "ls", "pwd", "pushd", "popd",
      "history", "cat", "echo", "man", "tree", "curl", "search", "cp", "mv", "rm"};

  @Before
  public void setUp() {
    this.man = new ManClass();
    this.output = new Output(null, null);
  }

  @Test
  /**
   * This test is for the valid command
   */
  public void validCommand() {
    for (int i = 0; i < this.allCommands.length; i++) {
      String[] argument = {this.allCommands[i]};
      MockInputProcessor userInput = new MockInputProcessor("man", argument, null, null);
      this.output = this.man.runCommand(userInput);
      assertEquals(null, this.output.getException());
    }
  }

  @Test
  /**
   * This test is for the invalid command
   */
  public void invalidCommand() {
    String[] argument = {"invalidCommand"};
    MockInputProcessor userInput = new MockInputProcessor("man", argument, null, null);
    this.output = this.man.runCommand(userInput);
    assertEquals("Error: Documentation not found, not a valid command",
        this.output.getException().getMessage());
    assertEquals(null, output.getOutputMessage());
  }

  @Test
  /**
   * This test is for more than one arguments
   */
  public void moreArgument() {
    String[] argument = {"cmd1", "cmd2"};
    MockInputProcessor userInput = new MockInputProcessor("man", argument, null, null);
    this.output = this.man.runCommand(userInput);
    assertEquals("Invalid argument: man command only takes one argument",
        this.output.getException().getMessage());
    assertEquals(null, this.output.getOutputMessage());
  }

  @Test
  /**
   * This test is for no argument
   */
  public void lessArgument() {
    MockInputProcessor userInput = new MockInputProcessor("man", null, null, null);
    this.output = this.man.runCommand(userInput);
    assertEquals("Invalid argument: no argument provide", this.output.getException().getMessage());
    assertEquals(null, this.output.getOutputMessage());
  }
}
