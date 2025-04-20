package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.elements.Directory;
import edu.austral.ingsis.clifford.system.InMemoryFileSystem;

public final class Mkdir implements Command {

  private final String dirName;

  public Mkdir(String dirName) {
    if (dirName == null || dirName.isBlank()) {
      throw new IllegalArgumentException("Directory name cannot be null or blank");
    }
    this.dirName = dirName;
  }

  @Override
  public String execute(InMemoryFileSystem fileSystem) {
    Directory currentDir = fileSystem.getCurrentDirectory();
    Directory newDir = new Directory(dirName, currentDir);
    currentDir.addChild(newDir);
    return "'" + newDir.getName() + "'" + " directory created";
  }
}
