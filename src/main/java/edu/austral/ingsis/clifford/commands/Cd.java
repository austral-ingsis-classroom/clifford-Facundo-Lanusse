package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.elements.Directory;
import edu.austral.ingsis.clifford.result.Result;
import edu.austral.ingsis.clifford.system.InMemoryFileSystem;

public final class Cd implements Command {

  private final String path;

  public Cd(String path) {
    if (path == null || path.isEmpty()) {
      throw new IllegalArgumentException("Path cannot be null or empty");
    }
    this.path = path;
  }

  @Override
  public String execute(InMemoryFileSystem fileSystem) {
    Result<Directory> result = fileSystem.resolvePath(path);

    return result.fold(
        dir -> {
          fileSystem.setCurrentDirectory(dir);
          return "moved to directory '" + dir.getName() + "'";
        },
        error -> error);
  }
}
