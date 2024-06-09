package filesystem;

/**
 * This class represents an item (Ex. file, directory) inside the file system
 * 
 * @author Luoliang Cai
 */
public class FileSystemContent {
  /** The name of the item */
  protected String itemName;
  /** The absolute path of the item */
  protected String itemPath;
  /** The directory that the item is stored in */
  protected Directory parentDirectory = null;

  /**
   * Basic constructor that creates a new item to be stored in the file system
   * 
   * @param name The name of the item
   * @param path The absolute path of the item
   */
  public FileSystemContent(String name, String path) {
    this.itemPath = path;
    this.itemName = name;
  }

  /**
   * Sets the parent directory
   * 
   * @param parent The new parent directory
   */
  public void setParent(Directory parent) {
    this.parentDirectory = parent;
  }

  /**
   * Retrieves the parent directory of the directory
   * 
   * @return returns the parent directory
   */
  public Directory getParent() {
    return this.parentDirectory;
  }

  /**
   * Retrieves the name of the item in the file system
   * 
   * @return the name of the item
   */
  public String getName() {
    return this.itemName;
  }

  /**
   * Retrieves the full path of the item in the file system
   * 
   * @return the absolute path of the item
   */
  public String getPath() {
    return this.itemPath;
  }

}
