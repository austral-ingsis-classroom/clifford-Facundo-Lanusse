package edu.austral.ingsis.clifford.commandFactory;

import edu.austral.ingsis.clifford.commands.Command;
import edu.austral.ingsis.clifford.result.Result;

// se encarga de ejecutar cada comando del mapa
public interface CommandFactory {
  Result<Command> parse(String[] arguments);
}
