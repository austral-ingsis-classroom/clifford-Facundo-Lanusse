package edu.austral.ingsis.clifford.elements;

public abstract sealed class FileSystemElements permits Directory, File {

  private final String name;
  private final Directory parent;

  public FileSystemElements(String name, Directory parent) {
    this.name = name;
    this.parent = parent;
  }

  public String getName() {
    return name;
  }

  public Directory getParent() {
    return parent;
  }

  public String getPath() {
    if (parent == null) {
      return "/";
    }
    String parentPath = parent.getPath();
    if ("/".equals(parentPath)) {
      return "/" + name;
    } else {
      return parentPath + "/" + name;
    }
  }

  public abstract boolean isDirectory();
}
