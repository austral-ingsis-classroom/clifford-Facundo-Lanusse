package edu.austral.ingsis.clifford.system;

import edu.austral.ingsis.clifford.commands.Command;
import edu.austral.ingsis.clifford.elements.Directory;

public class InMemoryFileSystem implements FileSystem {

  private final Directory root;
  private Directory current;

  public InMemoryFileSystem() {
    this.root = new Directory("/", null);
    this.current = root;
  }

  // usar result
  @Override
  public String run(String commandLine) {
    try {
      Command command = CommandParser.parse(commandLine);
      return command.execute(this);
    } catch (IllegalArgumentException e) {
      return "Error: " + e.getMessage();
    }
  }

  @Override
  public String getCurrentPath() {
    return current.getPath();
  }

  public Directory getCurrentDirectory() {
    return current;
  }

  public void setCurrentDirectory(Directory newDirectory) {
    this.current = newDirectory;
  }

  public Directory getRoot() {
    return root;
  }
}
