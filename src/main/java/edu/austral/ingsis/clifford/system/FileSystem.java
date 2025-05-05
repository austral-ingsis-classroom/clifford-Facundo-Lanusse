package edu.austral.ingsis.clifford.system;

import edu.austral.ingsis.clifford.result.CommandResult;
import edu.austral.ingsis.clifford.result.Result;

import java.util.List;

public interface FileSystem {
  Result<CommandResult> run(String commandLine);

  List<String> getCurrentPath();
}
