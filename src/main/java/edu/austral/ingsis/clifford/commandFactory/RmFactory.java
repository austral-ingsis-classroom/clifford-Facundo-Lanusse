package edu.austral.ingsis.clifford.commandFactory;

import edu.austral.ingsis.clifford.commands.Command;
import edu.austral.ingsis.clifford.commands.Rm;
import edu.austral.ingsis.clifford.result.Failure;
import edu.austral.ingsis.clifford.result.Result;
import edu.austral.ingsis.clifford.result.Success;

public class RmFactory implements CommandFactory {
  @Override
  public Result<Command> parse(String[] arguments) {
    boolean isRecursive = false;
    String target = null;

    for (String arg : arguments) {
      if (arg.equals("--recursive")) {
        isRecursive = true;
      } else {
        target = arg;
      }
    }
    if (target == null) {
      return new Failure<>("Mising target");
    }

    return new Success<>(new Rm(target, isRecursive));
  }
}
