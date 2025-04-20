package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.system.InMemoryFileSystem;

public final class Pwd implements Command {
  @Override
  public String execute(InMemoryFileSystem fileSystem) {
    return fileSystem.getCurrentPath();
  }
}
