package edu.austral.ingsis.clifford.system;

import edu.austral.ingsis.clifford.commands.Command;
import edu.austral.ingsis.clifford.elements.Directory;
import edu.austral.ingsis.clifford.elements.FileSystemElements;
import edu.austral.ingsis.clifford.result.Failure;
import edu.austral.ingsis.clifford.result.Result;
import edu.austral.ingsis.clifford.result.Success;

public class InMemoryFileSystem implements FileSystem {

  private final Directory root;
  private Directory current;

  public InMemoryFileSystem() {
    this.root = new Directory("/", null);
    this.current = root;
  }

  @Override
  public String run(String commandLine) {
    try {
      Command command = CommandParser.parse(commandLine);
      return command.execute(this);
    } catch (IllegalArgumentException e) {
      return "Error: " + e.getMessage();
    }
  }

  @Override
  public String getCurrentPath() {
    return current.getPath();
  }

  public Directory getCurrentDirectory() {
    return current;
  }

  public void setCurrentDirectory(Directory newDirectory) {
    this.current = newDirectory;
  }

  public Directory getRoot() {
    return root;
  }

  // Resuelve rutas tipo: ".", "..", "/", "~/facu-lanusse"
  public Result<Directory> resolvePath(String path) {
    String[] parts = path.split("/"); // separa los distinos nombres cuanod aparece una "/"
    Directory directory =
        path.startsWith("/") ? root : current; // si arranca con "/" entonces es root sino otro

    for (String part : parts) {
      switch (part) {
        case "", "." -> {
          continue;
        }
        case ".." -> {
          if (directory.getParent() != null) {
            directory = directory.getParent();
          }
        }
        default -> {
          FileSystemElements child = directory.getChild(part);
          if (child == null) {
            return new Failure<>("'" + part + "' directory does not exist");
          }
          if (!child.isDirectory()) {
            return new Failure<>("'" + part + "' is not a directory");
          }
          directory = (Directory) child; // casteo porque ya me fije que es un directorio
        }
      }
    }
    return new Success<>(directory);
  }
}
