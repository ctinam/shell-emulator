package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import driver.CommandExecutor;
import driver.InputProcessor;
import driver.ValidationGate;
import exception.InvalidCommandException;

/**
 * This is a JUnit test class for CommandExecutor
 * 
 * @author Man Hei Ho
 */
public class CommandExecutorTest {

  private CommandExecutor executor;

  @Before
  public void setup() {
    ValidationGate valiGate = new MockValidationGate();
    this.executor = new CommandExecutor(valiGate, null, null, null, null, null);
  }

  /**
   * Test CommandExecutor runCommand() to run invalid command
   */
  @Test
  public void testRunCommandToRunInvalidCommand() {
    InputProcessor userInput = new MockInputProcessor("InvalidCommand", null, null, null);
    try {
      this.executor.runCommand(userInput);
    } catch (InvalidCommandException e) {
      String expectedError = "InvalidCommand is not a valid command.";
      assertEquals(expectedError, e.getMessage());
      return;
    } catch (Exception e) {
      fail("should catch InvalidCommandException");
      return;
    }
    fail("should catch InvalidCommandException");
  }

  /**
   * Test CommandExecutor runCommand() to run valid command
   */
  @Test
  public void testRunCommandToRunValidCommand() {
    InputProcessor userInput = new MockInputProcessor("ValidCommand", null, null, null);
    try {
      this.executor.runCommand(userInput);
    } catch (Exception e) {
      fail("should not catch any exception.");
      return;
    }
  }
}
