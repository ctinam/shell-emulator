package filesystem;

/**
 * This class represents a file within the file system
 * 
 * @author Luoliang Cai
 */
public class File extends FileSystemContent {
  private String fileText = "";

  /**
   * Creates the default empty file
   * 
   * @param name The name of the file
   * @param path The full path of the file
   * @param text The text that the file contains
   */
  public File(String name, String path) {
    super(name, path);
  }

  /**
   * Creates an instance of a file with text inside it
   * 
   * @param name The name of the file
   * @param path The full path of the file
   * @param text The text that the file contains
   */
  public File(String name, String path, String text) {
    this(name, path);
    this.fileText = text;
  }

  /**
   * Overwrites original text in file with new text
   * 
   * @param newtext The text that will replace the original text in file
   */
  public void overwriteText(String newText) {
    this.fileText = newText;
  }

  /**
   * Append text to the end of a file
   * 
   * @param newText The text to be appended
   */
  public void appendText(String newText) {
    this.fileText = this.fileText + newText;
  }

  /**
   * delete all the text in the file
   */
  public void deleteAll() {
    this.fileText = "";
  }

  /**
   * Retrieve text in file
   */
  public String getText() {
    return this.fileText;
  }

}
