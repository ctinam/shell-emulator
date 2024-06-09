package storage;

import java.io.File;
import java.io.IOException;

/**
 * This class will handler the file in the real computer
 * 
 * @author Yuanyuan Li
 *
 */
public class FileHandler {

  /**
   * This method is for creating a file in the actual file system
   * 
   * @param fileName User input FileName
   * @throws IOException
   */
  public void processFile(String fileName) throws IOException {
    File file = null;
    try {
      file = new File(fileName);
      // if the file has already existed delete and create a new one
      if (file.exists())
        file.delete();
      file.createNewFile();
    }
    // error message
    catch (IOException e) {
      throw new IOException("Error: fail to create file");
    }
  }

  /**
   * This method is for checking whether file exists given file path
   * 
   * @param filePath The pathname of the file
   * @return boolean
   */
  public boolean fileExist(String fileName) {
    File file = null;
    file = new File(fileName);
    return file.exists();
  }
}
