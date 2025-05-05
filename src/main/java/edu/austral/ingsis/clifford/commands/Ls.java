package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.elements.Directory;
import edu.austral.ingsis.clifford.elements.FileSystemElements;
import edu.austral.ingsis.clifford.result.CommandResult;
import edu.austral.ingsis.clifford.result.Result;
import edu.austral.ingsis.clifford.result.Success;
import edu.austral.ingsis.clifford.system.InMemoryFileSystem;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class Ls implements Command {

  private String order;

  public Ls(String order) {
    this.order = order;
  }

  @Override
  public Result<CommandResult> execute(InMemoryFileSystem fileSystem) {
    Directory current = fileSystem.getCurrentDirectory();

    List<FileSystemElements> children = current.getChildren();

    List<FileSystemElements> orderList = orderList(order, children);

    return new Success<>(new CommandResult(orderList.stream().map(FileSystemElements::getName).collect(Collectors.joining(" ")),
            fileSystem));
  }

  public List<FileSystemElements> orderList(String order, List<FileSystemElements> children) {

    List<FileSystemElements> sorted = new ArrayList<>(children);

    boolean unordered = order == null;

    if (unordered) {
      return sorted;
    }
    switch (order) {
      case "asc" -> sorted.sort(Comparator.comparing(FileSystemElements::getName));
      case "desc" -> sorted.sort(Comparator.comparing(FileSystemElements::getName).reversed());
        // como uso sealed puedo no usar default
    }
    return sorted;
  }
}
