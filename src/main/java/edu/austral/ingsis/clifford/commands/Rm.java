package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.elements.Directory;
import edu.austral.ingsis.clifford.elements.FileSystemElements;
import edu.austral.ingsis.clifford.system.InMemoryFileSystem;

public final class Rm implements Command {

  private final String target;
  private final boolean recursive;

  public Rm(String target, boolean recursive) {
    this.target = target;
    this.recursive = recursive;
  }

  @Override
  public String execute(InMemoryFileSystem fileSystem) {
    Directory currentDir = fileSystem.getCurrentDirectory();
    FileSystemElements removable = currentDir.getChild(target);

    boolean targetExists = removable != null;

    if (!targetExists) {
      return "file does not exist";
    }
    // excepción si intento borrar un directorio sin el flag --recursive
    if (!recursive && removable.isDirectory()) {
      return "cannot remove '" + removable.getName() + "', is a directory";
    }
    if (recursive) {
      FileSystemElements directory = currentDir.getChild(target);
      currentDir.removeChild(directory);
      return "'" + directory.getName() + "' removed";
    }
    FileSystemElements file = currentDir.getChild(target);
    currentDir.removeChild(file);
    return "'" + file.getName() + "' removed";
  }
}
