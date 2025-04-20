package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.system.InMemoryFileSystem;

public sealed interface Command permits Cd, Ls, Mkdir, Pwd, Rm, Touch {
  String execute(InMemoryFileSystem fileSystem);
}
