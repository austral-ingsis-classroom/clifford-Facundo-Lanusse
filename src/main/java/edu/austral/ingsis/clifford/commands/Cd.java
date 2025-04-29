package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.Resolver.PathResolver;
import edu.austral.ingsis.clifford.elements.Directory;
import edu.austral.ingsis.clifford.result.Result;
import edu.austral.ingsis.clifford.system.InMemoryFileSystem;

public final class Cd implements Command {

  private final String path;

  public Cd(String path) {
    this.path = path;
  }

  @Override
  public String execute(InMemoryFileSystem fileSystem) {
    Directory root = fileSystem.getRoot();
    Directory currentDir = fileSystem.getCurrentDirectory();

    PathResolver pathResolver = new PathResolver(path, root, currentDir);
    Result<Directory> resolvedPath = pathResolver.resolvePath(path);

    return resolvedPath.fold(
        dir -> {
          fileSystem.setCurrentDirectory(dir);
          return "moved to directory '" + dir.getName() + "'";
        },
        error -> error);
  }
}
