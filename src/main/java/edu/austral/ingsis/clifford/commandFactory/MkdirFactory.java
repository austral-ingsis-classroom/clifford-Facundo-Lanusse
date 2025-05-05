package edu.austral.ingsis.clifford.commandFactory;

import edu.austral.ingsis.clifford.commands.Command;
import edu.austral.ingsis.clifford.commands.Mkdir;
import edu.austral.ingsis.clifford.result.Failure;
import edu.austral.ingsis.clifford.result.Result;
import edu.austral.ingsis.clifford.result.Success;

public class MkdirFactory implements CommandFactory {
  @Override
  public Result<Command> parse(String[] arguments) {
    if (arguments.length != 1) {
      return new Failure<>("mkdir command requires exactly one argument");
    }
    return new Success<>(new Mkdir(arguments[0]));
  }
}
