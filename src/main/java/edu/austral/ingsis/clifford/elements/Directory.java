package edu.austral.ingsis.clifford.elements;

import java.util.ArrayList;
import java.util.List;

public final class Directory extends FileSystemElements {

  // manejo de dirctorio como nodos intermedios guardando una lista con sus hijos
  private final List<FileSystemElements> children;

  public Directory(String name, Directory parent) {
    super(name, parent);
    children = new ArrayList<>();
  }

  @Override
  public boolean isDirectory() {
    return true;
  }

  public void addChild(FileSystemElements child) {
    children.add(child);
  }

  public void removeChild(FileSystemElements child) {
    children.remove(child);
  }

  public FileSystemElements getChild(String child) {
    for (FileSystemElements childrenElement : children) {
      if (childrenElement.getName().equals(child)) {
        return childrenElement;
      }
    }
    return null;
  }

  public List<FileSystemElements> getChildren() {
    return children;
  }
}
