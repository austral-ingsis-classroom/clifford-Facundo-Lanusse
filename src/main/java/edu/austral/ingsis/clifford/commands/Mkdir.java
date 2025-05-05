package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.elements.Directory;
import edu.austral.ingsis.clifford.result.CommandResult;
import edu.austral.ingsis.clifford.result.Result;
import edu.austral.ingsis.clifford.result.Success;
import edu.austral.ingsis.clifford.system.InMemoryFileSystem;
import java.util.List;

public final class Mkdir implements Command {

  private final String dirName;

  public Mkdir(String dirName) {
    if (dirName == null || dirName.isBlank()) {
      throw new IllegalArgumentException("Directory name cannot be null or blank");
    }
    this.dirName = dirName;
  }

  @Override
  public Result<CommandResult> execute(InMemoryFileSystem fileSystem) {
    Directory currentDir = fileSystem.getCurrentDirectory();
    Directory newDir = new Directory(dirName, List.of());

    Directory updatedCurrentDir = currentDir.addChildByName(newDir);
    InMemoryFileSystem updatedFs = fileSystem.replaceDirectoryAtCurrentPath(updatedCurrentDir);

    return new Success<>(
        new CommandResult("'" + newDir.getName() + "' directory created", updatedFs));
  }
}
