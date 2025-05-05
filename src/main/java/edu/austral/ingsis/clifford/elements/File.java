package edu.austral.ingsis.clifford.elements;

public final class File extends FileSystemElements {

  public File(String name) {
    super(name);
  }

  @Override
  public boolean isDirectory() {
    return false;
  }
}
