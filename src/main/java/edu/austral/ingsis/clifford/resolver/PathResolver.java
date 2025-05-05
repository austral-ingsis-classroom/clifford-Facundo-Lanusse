package edu.austral.ingsis.clifford.resolver;

import edu.austral.ingsis.clifford.elements.Directory;
import edu.austral.ingsis.clifford.elements.FileSystemElements;
import edu.austral.ingsis.clifford.result.Failure;
import edu.austral.ingsis.clifford.result.ResolvedPath;
import edu.austral.ingsis.clifford.result.Result;
import edu.austral.ingsis.clifford.result.Success;
import java.util.ArrayList;
import java.util.List;

public class PathResolver {
  private final Directory root;
  private final Directory current;
  private final List<String> currentPath;

  public PathResolver(Directory root, Directory current, List<String> currentPath) {
    this.root = root;
    this.current = current;
    this.currentPath = currentPath;
  }

  public Result<ResolvedPath> resolvePath(String path) {
    List<String> normalizedPath = normalizePath(path);
    Directory directory = root;

    for (String part : normalizedPath) {
      Result<FileSystemElements> childResult = directory.getChild(part);

      if (childResult.isFailure()) return propagateFailure(childResult);

      FileSystemElements child = childResult.fold(s -> s, f -> null);
      if (!child.isDirectory()) return new Failure<>("'" + part + "' is not a directory");

      directory = (Directory) child;
    }

    return new Success<>(new ResolvedPath(directory, normalizedPath));
  }

  private List<String> normalizePath(String path) {
    String[] parts = path.split("/");
    List<String> pathList = path.startsWith("/") ? new ArrayList<>() : new ArrayList<>(currentPath);

    for (String part : parts) {
      switch (part) {
        case "", "." -> {} // Ignorar vacíos o puntos actuales
        case ".." -> {
          if (!pathList.isEmpty()) pathList.remove(pathList.size() - 1);
        }
        default -> pathList.add(part);
      }
    }
    return pathList;
  }

  private static <T> Failure<T> propagateFailure(Result<?> failure) {
    return failure.fold(ignored -> null, Failure::new);
  }
}
