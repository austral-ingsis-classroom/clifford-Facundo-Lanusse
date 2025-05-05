package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.result.CommandResult;
import edu.austral.ingsis.clifford.result.Result;
import edu.austral.ingsis.clifford.result.Success;
import edu.austral.ingsis.clifford.system.InMemoryFileSystem;

public final class Pwd implements Command {
  @Override
  public Result<CommandResult> execute(InMemoryFileSystem fileSystem) {
    String path = "/" + String.join("/", fileSystem.getCurrentPath());
    return new Success<>(new CommandResult(path, fileSystem));
  }
}
