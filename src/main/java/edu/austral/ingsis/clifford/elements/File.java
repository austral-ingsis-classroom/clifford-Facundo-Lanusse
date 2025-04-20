package edu.austral.ingsis.clifford.elements;

public final class File extends FileSystemElements {

  public File(String name, Directory parent) {
    super(name, parent);
  }

  @Override
  public boolean isDirectory() {
    return false;
  }
}
