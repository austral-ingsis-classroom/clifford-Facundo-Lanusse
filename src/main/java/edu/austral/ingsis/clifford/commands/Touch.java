package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.elements.Directory;
import edu.austral.ingsis.clifford.elements.File;
import edu.austral.ingsis.clifford.result.CommandResult;
import edu.austral.ingsis.clifford.result.Result;
import edu.austral.ingsis.clifford.result.Success;
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
  public Result<CommandResult> execute(InMemoryFileSystem fileSystem) {
    Directory currentDir = fileSystem.getCurrentDirectory();
    File newFile = new File(fileName);

    Directory updatedDir = currentDir.addChildByName(newFile);
    InMemoryFileSystem updatedFs = fileSystem.replaceDirectoryAtCurrentPath(updatedDir);

    return new Success<>(new CommandResult("'" + newFile.getName() + "' file created", updatedFs));
  }
}
