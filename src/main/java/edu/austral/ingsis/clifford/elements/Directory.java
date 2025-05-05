package edu.austral.ingsis.clifford.elements;

import edu.austral.ingsis.clifford.result.Failure;
import edu.austral.ingsis.clifford.result.Result;
import edu.austral.ingsis.clifford.result.Success;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//todo inmutable
public final class Directory extends FileSystemElements {

  // manejo de dirctorio como nodos intermedios guardando una lista con sus hijos
  private final List<FileSystemElements> children;

  public Directory(String name, List<FileSystemElements> children) {
    super(name);
    this.children = Collections.unmodifiableList(new ArrayList<>(children));
  }

  @Override
  public boolean isDirectory() {
    return true;
  }

  public Directory addChildByName(FileSystemElements child) {
    List<FileSystemElements> newChildren = new ArrayList<>(children);
    newChildren.add(child);
    return new Directory(getName(), newChildren);
  }

  public Directory removeChildByName(FileSystemElements child) {
    List<FileSystemElements> newChildren = new ArrayList<>(children);
    newChildren.remove(child);
    return new Directory(getName(), newChildren);
  }

  public Result<FileSystemElements> getChild(String name) {

    return children.stream()
            .filter( child -> child.getName().equals(name))
            .findFirst()
            .<Result<FileSystemElements>>map(Success::new)
            .orElse(new Failure<>("'" + name + "' directory does not exist"));
  }

  public List<FileSystemElements> getChildren() {
    return children;
  }

  public Result<Directory> replaceChild(FileSystemElements newChild){
    List<FileSystemElements> newUpdatedChildren = new ArrayList<>(children);

    for(int i = 0; i < newUpdatedChildren.size(); i++){
      String fileName = newUpdatedChildren.get(i).getName();
      String newFileName = newChild.getName();

      if (fileName.equals(newFileName)){
        newUpdatedChildren.set(i, newChild);
        return new Success<>(new Directory(getName(), newUpdatedChildren));
      }
    }

    return new Failure<>("No such file or directory");
  }
}
