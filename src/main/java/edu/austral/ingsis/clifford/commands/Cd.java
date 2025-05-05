package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.elements.Directory;
import edu.austral.ingsis.clifford.resolver.PathResolver;
import edu.austral.ingsis.clifford.result.CommandResult;
import edu.austral.ingsis.clifford.result.Failure;
import edu.austral.ingsis.clifford.result.ResolvedPath;
import edu.austral.ingsis.clifford.result.Result;
import edu.austral.ingsis.clifford.result.Success;
import edu.austral.ingsis.clifford.system.InMemoryFileSystem;
import java.util.List;

public final class Cd implements Command {

  private final String path;

  public Cd(String path) {
    this.path = path;
  }

  @Override
  public Result<CommandResult> execute(InMemoryFileSystem fileSystem) {
    Directory root = fileSystem.getRoot();
    Directory currentDir = fileSystem.getCurrentDirectory();
    List<String> currenPath = fileSystem.getCurrentPath();

    PathResolver pathResolver = new PathResolver(root, currentDir, currenPath);
    Result<ResolvedPath> resolvedPath = pathResolver.resolvePath(path);

    return resolvedPath.fold(
        rp -> {
          InMemoryFileSystem updateFs = fileSystem.changeDirectoryTo(rp.directory(), rp.path());

          String pathDisplay = updateFs.getCurrentDirectory().getName();
          return new Success<>(
              new CommandResult("moved to directory '" + pathDisplay + "'", updateFs));
        },
        Failure::new);
  }
}
