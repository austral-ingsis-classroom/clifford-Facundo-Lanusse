package edu.austral.ingsis.clifford.commandFactory;

import edu.austral.ingsis.clifford.commands.Cd;
import edu.austral.ingsis.clifford.commands.Command;
import edu.austral.ingsis.clifford.commands.Pwd;
import edu.austral.ingsis.clifford.result.Failure;
import edu.austral.ingsis.clifford.result.Result;
import edu.austral.ingsis.clifford.result.Success;

public class PwdFactory implements CommandFactory{
    @Override
    public Result<Command> parse(String[] arguments) {
        if (arguments.length != 0) {
            return new Failure<>("pwd does not require any arguments");
        }
        return new Success<>(new Pwd());
    }
}
