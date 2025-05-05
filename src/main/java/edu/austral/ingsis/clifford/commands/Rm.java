package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.elements.Directory;
import edu.austral.ingsis.clifford.elements.FileSystemElements;
import edu.austral.ingsis.clifford.result.CommandResult;
import edu.austral.ingsis.clifford.result.Failure;
import edu.austral.ingsis.clifford.result.Result;
import edu.austral.ingsis.clifford.result.Success;
import edu.austral.ingsis.clifford.system.InMemoryFileSystem;

public final class Rm implements Command {

  private final String target;
  private final boolean recursive;

  public Rm(String target, boolean recursive) {
    this.target = target;
    this.recursive = recursive;
  }

  @Override
  public Result<CommandResult> execute(InMemoryFileSystem fileSystem) {
      Directory currentDir = fileSystem.getCurrentDirectory();

      return currentDir.getChild(target).fold(
              element -> {
                  if (element.isDirectory() && !recursive) {
                      return new Failure<>("cannot remove '" + element.getName() + "', is a directory");
                  }

                  Directory updatedDir = currentDir.removeChildByName(element);
                  InMemoryFileSystem updatedFs = fileSystem.replaceDirectoryAtCurrentPath(updatedDir);

                  return new Success<>(new CommandResult("'" + element.getName() + "' removed", updatedFs));
              },
              error -> new Failure<>("file does not exist")
      );
  }
}
