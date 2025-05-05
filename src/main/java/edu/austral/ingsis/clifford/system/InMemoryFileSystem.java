package edu.austral.ingsis.clifford.system;

import edu.austral.ingsis.clifford.commands.Command;
import edu.austral.ingsis.clifford.elements.Directory;
import edu.austral.ingsis.clifford.elements.FileSystemElements;
import edu.austral.ingsis.clifford.result.CommandResult;
import edu.austral.ingsis.clifford.result.Failure;
import edu.austral.ingsis.clifford.result.Result;
import java.util.Collections;
import java.util.List;

public class InMemoryFileSystem implements FileSystem {

  private final Directory root;
  private final Directory current;
  private final List<String> currentPath;
  private final CommandParser commandParser;

  // hacer todo inmutable
  public InMemoryFileSystem(
      Directory root, Directory current, List<String> currentPath, CommandParser commandParser) {
    this.root = root;
    this.current = current;
    this.currentPath = Collections.unmodifiableList(currentPath);
    this.commandParser = commandParser;
  }

  public InMemoryFileSystem(CommandParser parser) {
    this.root = new Directory("/", List.of());
    this.current = root;
    this.currentPath = List.of();
    this.commandParser = parser;
  }

  // usar result
  @Override
  public Result<CommandResult> run(String commandLine) {
    Result<Command> cmdParsed = commandParser.parse(commandLine);
    return cmdParsed.fold(
        command -> command.execute(this), errorMessage -> new Failure<>(errorMessage));
  }

  @Override
  public List<String> getCurrentPath() {
    return currentPath;
  }

  public InMemoryFileSystem changeDirectoryTo(Directory newDir, List<String> newPath) {
    return new InMemoryFileSystem(root, newDir, newPath, commandParser);
  }

  public Directory getCurrentDirectory() {
    return current;
  }

  public Directory getRoot() {
    return root;
  }

  public InMemoryFileSystem replaceDirectoryAtCurrentPath(Directory updated) {
    Directory newRoot = replaceDirectoryRecursive(root, currentPath, updated);
    return new InMemoryFileSystem(newRoot, updated, currentPath, commandParser);
  }

  private Directory replaceDirectoryRecursive(
      Directory node, List<String> currentPath, Directory updated) {
    if (currentPath.isEmpty()) {
      return updated;
    }
    String head = currentPath.get(0);
    List<String> tail = currentPath.subList(1, currentPath.size());
    Result<FileSystemElements> result = node.getChild(head);

    return result.fold(
        child -> {
          if (child instanceof Directory dir) {
            Directory replacedChild = replaceDirectoryRecursive(dir, tail, updated);
            Result<Directory> replacedNode = node.replaceChild(replacedChild);

            return replacedNode.fold(
                success -> success, error -> node // si falla, devolvés el original
                );
          }
          return node;
        },
        err -> node);
  }

  public CommandParser getCommandParser() {
    return commandParser;
  }
}
