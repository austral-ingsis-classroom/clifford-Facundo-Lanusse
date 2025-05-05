package edu.austral.ingsis.clifford.commandFactory;

import edu.austral.ingsis.clifford.commands.Command;
import edu.austral.ingsis.clifford.commands.Ls;
import edu.austral.ingsis.clifford.result.Result;
import edu.austral.ingsis.clifford.result.Success;

public class LsFactory implements CommandFactory {
  @Override
  public Result<Command> parse(String[] arguments) {
    String order = null;

    for (String arg : arguments) {
      switch (arg) {
        case "--ord=desc" -> order = "desc";
        case "--ord=asc" -> order = "asc";
      }
    }

    return new Success<>(new Ls(order));
  }
}
