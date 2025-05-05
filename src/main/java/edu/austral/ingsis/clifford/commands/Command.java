package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.result.CommandResult;
import edu.austral.ingsis.clifford.result.Result;
import edu.austral.ingsis.clifford.system.InMemoryFileSystem;

public sealed interface Command permits Cd, Ls, Mkdir, Pwd, Rm, Touch {
  Result<CommandResult> execute(InMemoryFileSystem fileSystem);
}
