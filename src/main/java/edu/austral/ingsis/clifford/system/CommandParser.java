package edu.austral.ingsis.clifford.system;

import edu.austral.ingsis.clifford.commands.Cd;
import edu.austral.ingsis.clifford.commands.Command;
import edu.austral.ingsis.clifford.commands.Ls;
import edu.austral.ingsis.clifford.commands.Mkdir;
import edu.austral.ingsis.clifford.commands.Pwd;
import edu.austral.ingsis.clifford.commands.Rm;
import edu.austral.ingsis.clifford.commands.Touch;
import java.util.Arrays;

public class CommandParser {
  public static Command parse(String input) {
    // elimina espacion sobrantes y despue separa la linea por espacios "mkdir fotos" → ["mkdir",
    // "fotos"]
    String[] tokens = input.trim().split("\\s+");
    // toma la primera palabra
    String command = tokens[0];
    // toma el resto de las palabras a partir de la segunda
    String[] args = Arrays.copyOfRange(tokens, 1, tokens.length);

    return switch (command) {
      case "mkdir" -> new Mkdir(args[0]);
      case "touch" -> new Touch(args[0]);
      case "cd" -> new Cd(args[0]);
      case "ls" -> parseLs(args);
        // usa el metodo privado definido abajo que maneja si el rm es recursivo o no
      case "rm" -> parseRm(args);
      case "pwd" -> new Pwd();
      default -> throw new IllegalArgumentException("Unknown command: " + command);
    };
  }

  // resuleve el comando de rm recursivo
  private static Command parseRm(String[] args) {
    boolean recursive = false;
    String target = null;

    for (String arg : args) {
      if (arg.equals("--recursive")) {
        recursive = true;
      } else {
        target = arg;
      }
    }

    if (target == null) {
      throw new IllegalArgumentException("Missing target for rm");
    }

    return new Rm(target, recursive);
  }

  private static Command parseLs(String[] args) {

    String order = null;

    for (String arg : args) {
      switch (arg) {
        case "--ord=desc" -> order = "desc";
        case "--ord=asc" -> order = "asc";
      }
    }
    return new Ls(order);
  }
}
