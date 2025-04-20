package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.elements.Directory;
import edu.austral.ingsis.clifford.elements.File;
import edu.austral.ingsis.clifford.system.InMemoryFileSystem;

public final class Touch implements Command {

  private final String fileName;

  public Touch(String fileName) {
    if (fileName == null || fileName.isBlank()) {
      throw new IllegalArgumentException("FileName cannot be null or empty");
    }
    this.fileName = fileName;
  }

  @Override
  public String execute(InMemoryFileSystem fileSystem) {
    Directory parent = fileSystem.getCurrentDirectory();
    File newFile = new File(fileName, parent);
    parent.addChild(newFile);
    return "'" + newFile.getName() + "'" + " file created";
  }
}
