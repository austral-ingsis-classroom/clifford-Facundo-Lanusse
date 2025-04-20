package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.elements.Directory;
import edu.austral.ingsis.clifford.elements.FileSystemElements;
import edu.austral.ingsis.clifford.system.InMemoryFileSystem;
import java.util.List;
import java.util.stream.Collectors;

public final class Ls implements Command {

  private String order;

  public Ls(String order) {
    this.order = order;
  }

  @Override
  public String execute(InMemoryFileSystem fileSystem) {
    Directory current = fileSystem.getCurrentDirectory();

    List<FileSystemElements> children = current.list(order);

    return children.stream().map(FileSystemElements::getName).collect(Collectors.joining(" "));
  }
}
