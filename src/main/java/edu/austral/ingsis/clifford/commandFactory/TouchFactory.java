package edu.austral.ingsis.clifford.commandFactory;

import edu.austral.ingsis.clifford.commands.Cd;
import edu.austral.ingsis.clifford.commands.Command;
import edu.austral.ingsis.clifford.commands.Touch;
import edu.austral.ingsis.clifford.result.Failure;
import edu.austral.ingsis.clifford.result.Result;
import edu.austral.ingsis.clifford.result.Success;

public class TouchFactory implements CommandFactory{
    @Override
    public Result<Command> parse(String[] arguments) {
        if (arguments.length != 1) {
            return new Failure<>("touch command requires exactly one argument");
        }
        return new Success<>(new Touch(arguments[0]));
    }
}
