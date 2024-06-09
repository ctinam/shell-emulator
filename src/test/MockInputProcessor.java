package test;

import driver.InputProcessor;

/**
 * This is a mock InputProcessor class for JUnit Testing
 * 
 * @author Man Hei Ho
 */
public class MockInputProcessor extends InputProcessor {

  private String cmd;
  private String[] args;
  private String redirOper;
  private String redirPath;

  public MockInputProcessor(String cmd, String[] args, String redirOper, String redirPath) {
    this.cmd = cmd;
    this.args = args;
    this.redirOper = redirOper;
    this.redirPath = redirPath;
  }

  public String getCommand() {
    return this.cmd;
  }

  public String[] getArgument() {
    return this.args;
  }

  public String getRedirectionOperator() {
    return this.redirOper;
  }

  public String getRedirectionPathname() {
    return this.redirPath;
  }
}
