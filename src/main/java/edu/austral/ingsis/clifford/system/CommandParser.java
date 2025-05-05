package edu.austral.ingsis.clifford.system;

import edu.austral.ingsis.clifford.commandFactory.CommandFactory;
import edu.austral.ingsis.clifford.commands.Command;
import edu.austral.ingsis.clifford.commands.Ls;
import edu.austral.ingsis.clifford.commands.Rm;
import edu.austral.ingsis.clifford.result.Failure;
import edu.austral.ingsis.clifford.result.Result;
import edu.austral.ingsis.clifford.result.Success;

import java.util.Arrays;
import java.util.Map;

public class CommandParser {
  private Map<String, CommandFactory> commandMap;

  public CommandParser(Map<String, CommandFactory> cmd) {
    this.commandMap = cmd;
  }

  public Result<Command> parse(String input) {
    // elimina espacion sobrantes y despue separa la linea por espacios "mkdir fotos" → ["mkdir",
    // "fotos"]
    String[] tokens = input.trim().split("\\s+");
    if (tokens.length == 0) {
      return new Failure<>("Empty input");
    }
    // toma la primera palabra
    String command = tokens[0];
    // toma el resto de las palabras a partir de la segunda
    String[] args = Arrays.copyOfRange(tokens, 1, tokens.length);

    //recivo los comandos de mi mapa de comandos
    CommandFactory factory = commandMap.get(command);
    if (factory == null) {
      return new Failure<>("Unknown command: " + command);
    }

    /*
    respeto principio de open-close
    cada vez que agrego comandos solo tengo que pasarle un nuevo mapa y con los nuevos comandos
    aca ejecuto cada comando
    * */
    try {
      return factory.parse(args);
    } catch (IllegalArgumentException e) {
      return new Failure<>("Invalid argument for command: " + command + ": " + e.getMessage());
    }
  }
}
