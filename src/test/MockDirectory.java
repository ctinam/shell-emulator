package test;

import java.util.Iterator;
import java.util.LinkedList;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystemContent;

/**
 * This is a mock Directory class for JUnit testing
 * 
 * @author Man Hei Ho
 */
public class MockDirectory extends Directory {

  public LinkedList<Directory> mockSubDir;
  public LinkedList<File> mockFiles;

  public MockDirectory(String name, String pathname, LinkedList<Directory> dirs,
      LinkedList<File> files) {
    super(name, pathname);
    this.mockSubDir = dirs;
    this.mockFiles = files;
  }

  public Iterator<FileSystemContent> getDirectoryItems() {
    LinkedList<FileSystemContent> directoryContents = new LinkedList<FileSystemContent>();
    directoryContents.addAll(this.mockSubDir);
    directoryContents.addAll(this.mockFiles);
    return directoryContents.iterator();
  }

  public Iterator<Directory> getSubdirectories() {
    return this.mockSubDir.iterator();
  }

  public Iterator<File> getFiles() {
    return this.mockFiles.iterator();
  }

  public void remove(String path) {
    if (path.contains("FilePath")) {
      this.mockFiles.remove(0);
    } else if (path.contains("DirectoryPath")) {
      this.mockSubDir.remove(0);
    }
  }

  public boolean hasChild(Directory dir) {

    if (dir.getPath().contains("child")) {
      return true;
    } else {
      return false;
    }
  }
}
