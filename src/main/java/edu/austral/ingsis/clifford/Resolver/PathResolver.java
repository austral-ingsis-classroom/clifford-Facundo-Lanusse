package edu.austral.ingsis.clifford.Resolver;

import edu.austral.ingsis.clifford.elements.Directory;
import edu.austral.ingsis.clifford.elements.FileSystemElements;
import edu.austral.ingsis.clifford.result.Failure;
import edu.austral.ingsis.clifford.result.Result;
import edu.austral.ingsis.clifford.result.Success;

public class PathResolver {
  private final String path;
  private final Directory current;
  private final Directory root;

  public PathResolver(String path, Directory root, Directory current) {
    this.path = path;
    this.root = root;
    this.current = current;
  }

  // Resuelve rutas tipo: ".", "..", "/", "~/facu-lanusse"
  public Result<Directory> resolvePath(String path) {
    String[] parts = splitPath(path); // separa los distinos nombres cuanod aparece una "/"
    Directory directory =
        determineStartingDirectory(path); // si arranca con "/" entonces es root sino otro

    Directory parent = directory.getParent();
    boolean hasParent = parent != null;

    for (String part : parts) {
      switch (part) {
        case "", "." -> {
          continue;
        }
        case ".." -> {
          if (hasParent) {
            directory = parent;
          }
        }
        default -> {
          FileSystemElements child = directory.getChild(part);
          boolean hasChild = child != null;

          if (!hasChild) {
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

  private Directory determineStartingDirectory(String path) {
    return path.startsWith("/") ? root : current;
  }

  private static String[] splitPath(String path) {
    return path.split("/");
  }
}
